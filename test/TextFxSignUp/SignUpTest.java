/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextFxSignUp;

import application.Main;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.testfx.matcher.control.TextInputControlMatchers.*;

/**
 * Clase para testear la ventana de SignUp y su comportamiento.
 *
 * @author Eneko.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTest extends ApplicationTest {

    private Button btnSignUp;
    private Pane panSignUp;
    private TextField txtName;
    private TextField txtNombreComp;
    private TextField txtEmail;
    private PasswordField txtPasswd;
    private PasswordField txtConfirmPasswd;
    private Button btnSave;
    private Button btnCancel;


    /*
    @Override
    public void start(Stage stage) throws Exception {
        new SignUpStage().start(stage);
    }
     */
    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @Before
    public void getFields() {

        txtEmail = lookup("#txtEmail").query();
        txtName = lookup("#txtName").query();
        txtPasswd = lookup("#txtPasswd").query();
        txtConfirmPasswd = lookup("#txtConfirmPasswd").query();
        btnSave = lookup("#btnSave").query();
        btnCancel = lookup("#btnCancel").query();

        btnSignUp = lookup("#btnSignUp").query();
    }

    @Test
    public void Test1_initialWindow() {
        clickOn("#btnSignUp");
    }

    /*
    @Test
    public void Test2_verifyEmptyFields() {
        verifyThat("#txtEmail", hasText(""));
        verifyThat("#txtName", hasText(""));
        verifyThat("#txtCity", hasText(""));
        verifyThat("#txtStreet", hasText(""));
        verifyThat("#txtZip", hasText(""));
        verifyThat("#phoneNumber", hasText(""));
        verifyThat("#txtPasswd", hasText(""));
        verifyThat("#txtConfirmPasswd", hasText(""));
    }
     */
    @Test
    public void Test3_emptyFields() {
        clickOn("#btnSave");
        clickOn("Aceptar");
    }

    @Test
    public void Test4_passwordFormatError() {

        clickOn("#txtEmail");
        write("pepe@gmail.com");
        clickOn("#txtName");
        write("Pepe Garcia Fernandez");
        clickOn("#txtPasswd");
        write("master1");
        clickOn("#txtConfirmPasswd");
        write("master1");
        clickOn("#btnSave");

        clickOn("Aceptar");
    }

    @Test
    public void Test5_passwordMatchError() {

        clickOn("#txtEmail");
        write("pepe@gmail.com");
        clickOn("#txtName");
        write("Pepe Garcia Fernandez");
        clickOn("#txtPasswd");
        write("Garcia69");
        clickOn("#txtConfirmPasswd");
        write("Garcia69");
        clickOn("#btnSave");

        clickOn("Aceptar");
    }
}
