/**
 * Este es el paquete del controlador de las ventanas.
 */
package controller;

import exception.EmptyTextFieldsException;
import exception.FormatErrorException;
import exception.LoginException;
import exception.ServerErrorException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ControllerFactory;
import model.Sign;
import exception.UserAlreadyExistsException;
import model.User;

/**
 *
 * @author Eneko y Egoitz.josu como colaborador.
 */
public class SignUpController {
    //Declaramos los campos que utilizaremos en esta ventana

    @FXML
    private Stage stage;
    /**
     * Campo de texto para la entrada del correo electrónico del usuario.
     */
    @FXML
    public TextField txtEmail;

    /**
     * Campo de texto para la entrada del nombre del usuario.
     */
    @FXML
    private TextField txtName;

    /**
     * Campo de texto para la entrada de la ciudad del usuario.
     */
    @FXML
    private TextField txtCity;

    /**
     * Campo de texto para la entrada de la calle del usuario.
     */
    @FXML
    private TextField txtStreet;

    /**
     * Campo de texto para la entrada del código ZIP del usuario.
     */
    @FXML
    private TextField txtZip;

    /**
     * Campo de texto para la entrada del número de teléfono del usuario.
     */
    @FXML
    private TextField phoneNumber;

    /**
     * Campo de contraseña para la entrada de la contraseña del usuario.
     */
    @FXML
    private PasswordField txtPasswd;

    /**
     * Expresión regular para validar el formato de la contraseña.
     */
    @FXML
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    /**
     * Patrón compilado para la validación de la contraseña.
     */
    @FXML
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    /**
     * Campo de contraseña para confirmar la entrada de la contraseña del
     * usuario.
     */
    @FXML
    private PasswordField txtConfirmPasswd;

    /**
     * Botón para guardar la información del usuario.
     */
    @FXML
    private Button btnSave;

    /**
     * Botón para cancelar el proceso de registro.
     */
    @FXML
    private Button btnCancel;

    /**
     * Expresión regular para validar el formato del correo electrónico.
     */
    @FXML
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Patrón compilado para la validación del correo electrónico.
     */
    @FXML
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * registrar mensajes en la clase SignUpController.
     */
    @FXML
    protected static final Logger LOGGER = Logger.getLogger("SignUpController");
    /**
     * Declaramos la interfaz
     */
    private Sign interf;

    /**
     * Inicializa la ventana de registro con el contenido proporcionado.
     *
     * @param root El nodo raíz que contiene la estructura de la ventana.
     */
    @FXML
    public void initStage(Parent root) {
        LOGGER.info("Initializing Sign Up stage.");
        Scene scene = new Scene(root);

        //La ventana no es redimensionable.
        stage = new Stage();
        stage.setResizable(false);

        //La ventana es una ventana modal.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        //El título de la ventana es “Sign Up”.
        stage.setTitle("SIGN UP");

        //Los TextField E-mail (id= txtEmail), name (id= txtName), city (id = txtCity),
        //Street (id=txtStreet), ZIP (id= txtZip), phone number (id= phoneNumber) están habilitados
        txtEmail.setDisable(false);
        txtName.setDisable(false);
        txtStreet.setDisable(false);
        txtCity.setDisable(false);
        txtZip.setDisable(false);
        phoneNumber.setDisable(false);

        //El botón save está habilitado.
        btnSave.setDisable(false);

        //El botón cancel está habilitado.
        btnCancel.setDisable(false);

        //Los PasswordField password (id = txtPasswd),
        //confirmPassword (id= txtConfirmPasswd) están habilitados.
        txtPasswd.setDisable(false);
        txtConfirmPasswd.setDisable(false);

        //El foco inicialmente estará en el campo de E-mail.
        txtEmail.requestFocus();

        //Boton por defecto
        btnSave.setDefaultButton(true);

        //Metodo de los botones
        btnSave.setOnAction(this::handleSaveButtonAction);

        btnCancel.setOnAction(this::handleButtonCancel);

        stage.show();

        //Invocamos a la factoria
        ControllerFactory fact = new ControllerFactory();
        interf = fact.getSocket();

    }

    /**
     * Maneja la acción del botón Guardar en el formulario de registro.
     *
     * @param event El evento de acción del botón.
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        // Validar si los textFields están informados
        try {

            //Si no están informados alguno de los campos saldrá un mensaje de error.
            if (this.txtEmail.getText().trim().isEmpty() || this.txtName.getText().trim().isEmpty()
                    || this.txtPasswd.getText().trim().isEmpty() || this.txtConfirmPasswd.getText().trim().isEmpty()) {
                throw new EmptyTextFieldsException("Campos no informados");
            }

            // Validar si el email contiene un dominio como (….@gmail.com).
            String email = this.txtEmail.getText();

            if (!(EMAIL_PATTERN.matcher(email).matches())) {
                throw new FormatErrorException("Formato de E-mail no valido");
            }

            // Validar si la contraseña contiene mayúsculas, minúsculas y al menos un número,
            // la longitud de un mínimo de 8 dígitos.
            String passwd = this.txtPasswd.getText();
            String confPasswd = this.txtConfirmPasswd.getText();
            if (!(PASSWORD_PATTERN.matcher(passwd).matches()) || !(PASSWORD_PATTERN.matcher(confPasswd).matches())) {
                throw new FormatErrorException("Formato de contrasenia no valida");
            }

            if (!confPasswd.equals(passwd)) {
                throw new FormatErrorException("Las contrasenias no son iguales");
            }

            User user = new User();
            user.setEmail(this.txtEmail.getText());
            user.setPassword(this.txtPasswd.getText());
            user.setNombre(this.txtName.getText());
            if (!txtCity.getText().isEmpty()) {
                user.setCiudad(txtCity.getText());
            }
            if (!txtStreet.getText().isEmpty()) {
                user.setDireccion(txtStreet.getText());
            }
            if (!txtZip.getText().isEmpty() && txtZip.getText().length() != 5) {
                throw new FormatErrorException("El zip no contiene el formato correcto");
            }

            if (!phoneNumber.getText().isEmpty() && phoneNumber.getText().length() != 9) {
                throw new FormatErrorException("El numero de telefono no tiene el formato correcto");
            }
            
            user = interf.getExecuteSignUp(user);
           
            new Alert(Alert.AlertType.INFORMATION, "Usuario registrado").showAndWait();
            //si quieres cerrar la ventana cuando se registre alguien 
            //stage.close

        } catch (EmptyTextFieldsException | FormatErrorException | UserAlreadyExistsException e) {
            new Alert(Alert.AlertType.INFORMATION,
                    e.getMessage()).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION,
                    e.getMessage()).showAndWait();
        }

    }

    /**
     * Maneja la acción del botón Cancelar en el formulario de registro.
     *
     * @param event El evento de acción del botón.
     */
    @FXML
    private void handleButtonCancel(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Estas seguro de que quieres cancelar el registro?\n"
                    + "No se almacenara nada de lo escrito",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                stage = (Stage) this.btnCancel.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            new Exception("Error al cerrar la pestaña " + e.getMessage());
        }

    }

    public void setStage(Stage stage) {

        this.stage = stage;
    }
}
