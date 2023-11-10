package application;

import controller.SignInController;
import controller.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación que inicia la interfaz de inicio de sesión.
 * Esta clase extiende `Application`, que es la clase base de JavaFX para
 * aplicaciones gráficas. La aplicación inicia la interfaz de inicio de sesión
 * al cargar el archivo FXML y crear una instancia del controlador
 * `SignInController`.
 *
 * @author Josu.
 */
public class MainTest extends Application {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws Exception Se lanza una excepción si ocurre algún error durante la
     * inicialización.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el archivo FXML de la interfaz de inicio de sesión.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));
        Parent root = (Parent) loader.load();

        // Obtener una referencia al controlador de inicio de sesión.
        SignUpController signUp = ((SignUpController) loader.getController());

        // Establecer el escenario principal en el controlador y inicializar la ventana de registro.
        signUp.setStage(stage);
        signUp.initStage(root);
    }

    /**
     * Método principal de ejecución de la aplicación.
     *
     * @param args Los argumentos de línea de comandos pasados a la aplicación.
     */
    public static void main(String[] args) {
        // Iniciar la aplicación JavaFX.
        launch(args);
    }

}
