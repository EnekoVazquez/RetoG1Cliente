/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.CredentialErrorException;
import exception.EmptyTextFieldsException;
import exception.FormatErrorException;
import exception.LoginException;
import exception.ServerErrorException;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ControllerFactory;
import model.Sign;
import model.User;

/**
 * Controlador para la ventana de inicio de sesión (Sign In) de la aplicación.
 * Esta clase maneja la lógica de la interfaz de inicio de sesión, la validación
 * de credenciales y la navegación a otras ventanas relacionadas con el inicio
 * de sesión.
 *
 * @author Josu
 */
public class SignInController {

    /**
     * La ventana de la aplicación.
     */
    @FXML
    private Stage stage;

    /**
     * Interfaz utilizada para la comunicación con el servidor u otro sistema.
     */
    @FXML
    private Sign interf;

    /**
     * Campo de entrada de texto para el correo electrónico del usuario.
     */
    @FXML
    private TextField txtEmail;

    /**
     * Campo de entrada de contraseña del usuario.
     */
    @FXML
    private PasswordField pswfPasswd;

    /**
     * Botón para iniciar sesión.
     */
    @FXML
    private Button btnLogin;

    /**
     * Botón para registrarse.
     */
    @FXML
    private Button btnSignUp;

    /**
     * Imagen utilizada en la interfaz.
     */
    @FXML
    private Image pngImage;

    /**
     * Etiqueta que muestra el título "Sign In".
     */
    @FXML
    private Label lblSignIn;

    /**
     * Etiqueta utilizada para mostrar mensajes de error.
     */
    @FXML
    private Label lblError;

    /**
     * Expresión regular que define el patrón de un correo electrónico válido.
     */
    @FXML
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Patrón utilizado para validar direcciones de correo electrónico.
     */
    @FXML
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Expresión regular que define el patrón de una contraseña válida.
     */
    @FXML
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    /**
     * Patrón utilizado para validar contraseñas que deben contener mayúsculas,
     * minúsculas y al menos un número, con una longitud mínima de 8 caracteres.
     */
    @FXML
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    /**
     * Logger utilizado para el registro de eventos y errores.
     */
    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/SignInController");

    public void initStage(Parent root) {

        LOGGER.info("Initializing SignIn stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        //El textField E-mail (id= txtEmail) y el passwordField password (id =txtPasswd) están habilitados.
        txtEmail.setDisable(false);
        pswfPasswd.setDisable(false);

        //El título de la ventana se llama “Sign In”.
        stage.setTitle("SIGN IN");

        //La imagen Perfil (id= pngPerfil) siempre está habilitada 
        //El botón login (id =btnLogin) está habilitado. El botón SignUp (id = btnSignUp)está habilitado.
        btnLogin.setDisable(false);
        btnSignUp.setDisable(false);

        //La ventana no puede ser redimensionable.
        stage.setResizable(false);

        // En el campo del e-mail del usuario (id = txtEmail) estará puesto el Foco.        
        txtEmail.requestFocus();

        //El botón por defecto de la ventana es Login (id= btnLogin)
        btnLogin.setDefaultButton(true);

        //Metodos de Botones
        btnSignUp.setOnAction(this::handleSignUpButtonAction);
        btnLogin.setOnAction(this::handleLoginAction);

        stage.show();

        //invocamos a la factoria
        ControllerFactory fact = new ControllerFactory();
        interf = fact.getSocket();

    }

    /**
     * Maneja el evento de inicio de sesión cuando se presiona el botón "Login".
     *
     * @param event Evento de acción que desencadena el inicio de sesión.
     */
    @FXML
    private void handleLoginAction(ActionEvent event) {

        try {
            //Validar si los textFields están informados
            //Si no están informados se lanzará la excepción EmptyTextFieldsException
            if (this.txtEmail.getText().isEmpty() || this.pswfPasswd.getText().isEmpty()) {
                //Esta parte de aqui hemos estimado retirarla ya que hemos notado que es una mala practica y poco usable pese a que funcione y estuviera en el diseño
                //txtEmail.setText("");
                //pswfPasswd.setText("");
                throw new EmptyTextFieldsException("Campos no informados");
            }
            //Validar que el email tiene un patrón válido
            String email = this.txtEmail.getText();
            if (!(EMAIL_PATTERN.matcher(email).matches())) {
                //Esta parte de aqui hemos estimado retirarla ya que hemos notado que es una mala practica y poco usable pese a que funcione y estuviera en el diseño
                //txtEmail.setText("");
                //pswfPasswd.setText("");
                throw new FormatErrorException("Formato de email incorrecto ");
            }
            //Validar si la contraseña contiene mayúsculas, minúsculas y al menos un número,
            //la longitud tiene que ser de un mínimo de 8 dígitos.
            String password = this.pswfPasswd.getText();
            if (!(PASSWORD_PATTERN.matcher(password).matches())) {
                //Esta parte de aqui hemos estimado retirarla ya que hemos notado que es una mala practica y poco usable pese a que funcione y estuviera en el diseño
                //txtEmail.setText("");
                //pswfPasswd.setText("");
                throw new FormatErrorException("Formato de Contraseña incorrecta");

            }
            //llamar al método getExecuteSignIn () de la interfaz Sign pasándole el usuario User con el email y la contraseña.
            //Pasar los datos necesarios, en este caso, fullname a la ventana Principal

            User user = new User();
            user.setEmail(txtEmail.getText());
            user.setPassword(pswfPasswd.getText());

            user = interf.getExecuteSignIn(user);
            
            if(user.getNombre()!=null){
                Stage PrincipalStage = new Stage();
            //cargar el fxml de la ventana de sign up utilizando un cargador no estatico
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));

                Parent root = (Parent) loader.load();

                PrincipalController principalController = ((PrincipalController) loader.getController());
            
                principalController.initStage(root, user);
            }else{
                throw new ServerErrorException("La conexion a la base de datos no esta operativa.");
            }

        } catch (FormatErrorException | EmptyTextFieldsException le) {

            new Alert(Alert.AlertType.INFORMATION, le.getMessage()).showAndWait();

        } catch (UserNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();
        } catch (Exception e) {

            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();

        }

    }

    /**
     * Maneja el evento de registro cuando se presiona el botón "Sign Up".
     *
     * @param event Evento de acción que desencadena la navegación a la ventana
     * de registro.
     */
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {

        try {
            Stage SignUpStage = new Stage();
            //cargar el fxml de la ventana de sign up utilizando un cargador no estatico
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));

            Parent root = (Parent) loader.load();

            SignUpController signUpController = ((SignUpController) loader.getController());
            signUpController.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Establece la ventana principal para este controlador.
     *
     * @param stage La ventana de la aplicación.
     */
    public void setStage(Stage stage) {

        this.stage = stage;
    }
}
