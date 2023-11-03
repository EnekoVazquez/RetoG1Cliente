/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Optional;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.User;


/**
 *
 * @author Eneko y Egoitz.
 */
public class PrincipalController {
    
    /**
     * Etiqueta para mostrar el texto.
     */
    @FXML
    private Label lblTexto;
    /**
     * Botón para salir de la app.
     */
    @FXML
    private Button btnExit;
    
    /**
     * Imagen que se muestra por pantalla.
     */
    @FXML
    private ImageView pngDedo;
    
     /**
     * registrar mensajes en la clase SignUpController.
     */
    @FXML
    protected static final Logger LOGGER = Logger.getLogger("SignUpController");
    
    private User user;

    /**
     * Method to start the window
     *
     * @param root the root of the window
     * @param user object of User type
     */
    @FXML
    public void initStage(Parent root, User user) {

        LOGGER.info("Initializing Principal stage.");
        Scene scene = new Scene(root);
        
        //El campo de texto está deshabilitado
        lblTexto.setDisable(true);
        //El botón está habilitado
        btnExit.setDisable(false);
        lblTexto.setVisible(true);
        btnExit.setVisible(true);

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("PRINCIPAL");

        btnExit.setOnAction(this::handleExitButtonAction);
        
        stage.show();
      
        lblTexto.setText(lblTexto.getText() + " " + (user.getNombre()));
    }

    /**
     * "exit" button method with confirmation
     */
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        try {

         
            //Con esto vamos a crear una ventana de confirmación al pulsar el botón de salir
            Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Advertencia");
            ventanita.setContentText("¿Deseas Salir?");
            //Con este Optional<ButtonType> creamos botones de Ok y cancelar
            Optional<ButtonType> action = ventanita.showAndWait();
            //Si le da a OK el programa cesará de existir, se cierra por completo
            if (action.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                //Si le da a cancelar la ventana emergente se cerrará pero la ventana principal se mantiene
                ventanita.close();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();
        }
    }

    /**
     * 
     * @param user object of User type
     */
    public void getUser(User user) {
        this.user = user;
        
    }

}
