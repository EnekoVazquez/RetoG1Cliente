/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package model;

import static model.MessageType.ERROR_RESPONSE;
import static model.MessageType.OK_RESPONSE;
import static model.MessageType.USER_ALREADY_EXISTS_RESPONSE;
import static model.MessageType.USER_NOT_FOUND_RESPONSE;
import exception.CredentialErrorException;
import exception.ServerErrorException;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase SignInClient implementa la interfaz code Sign y proporciona métodos
 * para manejar las operaciones de registro e inicio de sesión conectándose a un
 * servidor.
 *
 * @author Egoitz.
 */
public class SignInClient implements Sign {

    /**
     * ResourceBundle para parámetros de configuración.
     */
    private static final ResourceBundle RETO1 = ResourceBundle.getBundle("model.Config");

    /**
     * Puerto utilizado para la comunicación con el servidor.
     */
    private static final int PUERTO = Integer.parseInt(RETO1.getString("PORT"));

    /**
     * Dirección IP del host para el servidor.
     */
    private static final String HOST = ResourceBundle.getBundle("model.Config").getString("ip");

    /**
     * Enumeración que representa diferentes tipos de mensajes.
     */
    MessageType mt;

    /**
     * Instancia de Encapsulator para envolver la información del usuario y los
     * mensajes.
     */
    private Encapsulator encapsu = null;

    /**
     * Realiza el proceso de registro del usuario conectándose al servidor
     * 'SignerServer'.
     *
     * @param user El objeto de tipo User que se va a registrar.
     * @return El usuario registrado.
     * @throws ServerErrorException Se lanza si hay un error en el servidor.
     * @throws UserAlreadyExistsException Se lanza si el usuario ya existe en la
     * base de datos.
     */
    @Override
    public User getExecuteSignUp(User user) throws ServerErrorException, UserAlreadyExistsException {

        MessageType mst;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        // ois = new ObjectInputStream(ois);

        try {
            //Enviamos el objecto encapsulado al servidor
            Socket sokCliente = new Socket(HOST, PUERTO);
            oos = new ObjectOutputStream(sokCliente.getOutputStream());
            encapsu = new Encapsulator();
            encapsu.setUser(user);
            encapsu.setMessage(MessageType.SIGNUP_REQUEST);
            oos.writeObject(encapsu);

            //Recibimos el objeto encapsulado del servidor
            ois = new ObjectInputStream(sokCliente.getInputStream());
            encapsu = (Encapsulator) ois.readObject();
            user = encapsu.getUser();
            //Declaramos una variable int, pues las enumeraciones devuelven valores int
            //int decision = encapsu.getMessage().ordinal();
            oos.close();
            ois.close();
            sokCliente.close();

            //Dependiendo de el mensaje que reciva lanza o escribe un mensaje nuevo
            switch (encapsu.getMessage()) {
                case OK_RESPONSE:
                    return user;
                case USER_ALREADY_EXISTS_RESPONSE:
                    throw new UserAlreadyExistsException("El usuario ya existe");
                case USER_NOT_FOUND_RESPONSE:
                    throw new UserNotFoundException("Ese usuario no existe");
                case ERROR_RESPONSE:
                    throw new ServerErrorException("Ha ocurrido un error en el servidor");
            }

        } catch (UserNotFoundException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Realiza el proceso de inicio de sesión del usuario conectándose al
     * servidor 'SignerServer'.
     *
     * @param user El objeto de tipo User para el inicio de sesión.
     * @return El usuario que ha iniciado sesión.
     * @throws ServerErrorException Se lanza si hay un error en el servidor.
     * @throws CredentialErrorException Se lanza si la contraseña es incorrecta.
     * @throws UserNotFoundException Se lanza si el usuario no se encuentra en
     * la base de datos.
     */
    @Override
    public User getExecuteSignIn(User user) throws ServerErrorException, CredentialErrorException, UserNotFoundException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket sokCliente = null;

        try {
            // Establecer la conexión con el servidor
            sokCliente = new Socket(HOST, PUERTO);
            oos = new ObjectOutputStream(sokCliente.getOutputStream());

            // Enviamos el objeto encapsulado al servidor
            encapsu = new Encapsulator();
            encapsu.setUser(user);
            encapsu.setMessage(MessageType.SIGNIN_REQUEST);
            oos.writeObject(encapsu);

            // Recibimos el objeto encapsulado del servidor
            ois = new ObjectInputStream(sokCliente.getInputStream());
            encapsu = (Encapsulator) ois.readObject();
            user = encapsu.getUser();

            //int decision = encapsu.getMessage().ordinal();
            // Cerrar los flujos y la conexión
            oos.close();
            ois.close();
            sokCliente.close();

            // Dependiendo del mensaje que reciba, lanza o escribe un mensaje nuevo
            switch (encapsu.getMessage()) {
                case OK_RESPONSE:
                    return user;
                case USER_NOT_FOUND_RESPONSE:
                    throw new UserNotFoundException("El usuario " + user.getEmail() + " no ha sido encontrado " + "con la contraseña de " + user.getPassword());
                case ERROR_RESPONSE:
                    throw new ServerErrorException("Ha ocurrido un error en el servidor");
            }

        } catch (ClassNotFoundException ex) {
            // Manejar la excepción de clase no encontrada al deserializar
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerErrorException("Error al recibir datos del servidor");
        } catch (IOException ex) {
            // Manejar la excepción de E/S (por ejemplo, conexión fallida)
            Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerErrorException("Error de E/S al comunicarse con el servidor");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (sokCliente != null && !sokCliente.isClosed()) {
                    sokCliente.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SignInClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

}
