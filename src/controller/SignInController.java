/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Josu.
 */
public class SignInController {

    @FXML
    private Stage stage;

    @FXML
    private TextField txtEmail, txtPasswd;

    @FXML
    private Button btnLogin, btnSignUp;

    @FXML
    private Label lblSignIn, lblError;

    @FXML
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @FXML
    //con esta sentencia en orden le estamos diciendo que tiene que tener minimo un numero una letra minuscula una mayuscula y que no puede tener espacios en blanco
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    @FXML
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/SignInController");

    public void initStage(Parent root) {

        LOGGER.info("Initializing SignIn stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);

        //El textField E-mail (id= txtEmail) y el passwordField password (id =txtPasswd) están habilitados.
        txtEmail.setDisable(false);
        txtPasswd.setDisable(false);

        //El título de la ventana se llama “Sign In”.
        stage.setTitle("SIGN IN");

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

    }

    @FXML
    private void handleLoginAction(ActionEvent event) {

        try {
            //Validar si los textFields están informados
            //Si no están informados se lanzará la excepción EmptyTextFieldsException
            if (this.txtEmail.getText().isEmpty() || this.txtPasswd.getText().isEmpty()) {
                throw new Exception("Campos no informados");
            }
            //Validar que el email tiene un patrón válido
            String email = this.txtEmail.getText();
            if (!(EMAIL_PATTERN.matcher(email).matches())) {
                throw new Exception("formato email incorrecto ");
            }
            //Validar si la contraseña contiene mayúsculas, minúsculas y al menos un número,
            //la longitud tiene que ser de un mínimo de 8 dígitos.
            String password = this.txtPasswd.getText();
            if (!(PASSWORD_PATTERN.matcher(password).matches())) {
                throw new Exception("Contraseña incorrecta");
            }else{
                System.out.println("patata");
            }

        } catch (Exception e) {

            lblError.setVisible(true);
            lblError.setText(e.getMessage());

        }

    }

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

    public void setStage(Stage stage) {

        this.stage = stage;
    }
}
