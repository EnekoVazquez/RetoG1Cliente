/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testFx;

/**
 *
 * @author egoitz
 */
import application.Main;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrincipalTest extends ApplicationTest {

    private Button btnLogin;
    private Button btnExit;
    private Label lblTexto;
    private Pane panePrincipal;
    private TextField txtEmail;
    private PasswordField pswfPasswd;

    private Alert alert;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);

    }

    @Before
    public void getFields() {
        txtEmail = lookup("#txtEmail").query();
        pswfPasswd = lookup("#pswfPasswd").query();
        btnExit = lookup("#btnExit").query();
        btnLogin = lookup("#btnLogin").query();
        lblTexto = lookup("#lblTexto").query();
    }

    @Test
    public void Test1_windowIsOpen() {
        clickOn(txtEmail);
        write("patata@gmail.com");

        clickOn(pswfPasswd);
        write("Abcd*1234");

        clickOn(btnLogin);
        verifyThat("#panePrincipal", isVisible());
    }

    @Test
    public void Test2_userIsCorrect() {
        String lblEsperado = "Has entrado correctamente  Mr Potato";

        assertEquals(lblTexto.getText().toString(), lblEsperado);
    }

    @Test
    public void Test3_exitButton() {
        clickOn(btnExit);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("¿Deseas Salir?", Node::isVisible);

        clickOn("Aceptar");

    }

}
