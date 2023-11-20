/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testFx;

/**
 *
 * @author 2dam.
 */
import application.Main;
import application.MainTest;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTest extends ApplicationTest {

    private Button btnSignUp;
    private Pane paneSignUp;
    private TextField txtName;
    private TextField txtEmail;
    private TextField txtZip;
    private TextField txtCity;
    private TextField txtStreet;
    private TextField phoneNumber;
    private PasswordField txtPasswd;
    private PasswordField txtConfirmPasswd;
    private Button btnSave;
    private Button btnCancel;
    private Alert alert;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainTest.class);

    }

    @Before
    public void getFields() {
        txtEmail = lookup("#txtEmail").query();
        txtName = lookup("#txtName").query();
        txtCity = lookup("#txtCity").query();
        txtStreet = lookup("#txtStreet").query();
        txtZip = lookup("#txtZip").query();
        phoneNumber = lookup("#phoneNumber").query();
        txtPasswd = lookup("#txtPasswd").query();
        txtConfirmPasswd = lookup("#txtConfirmPasswd").query();
        btnSave = lookup("#btnSave").query();
        btnCancel = lookup("#btnCancel").query();
        btnSignUp = lookup("#btnSignUp").query();

    }

    @Test
    public void Test1_windowIsOpen() {
        //clickOn(btnSignUp);
        verifyThat("#paneSignUp", isVisible());
    }

    @Test
    @Ignore
    public void Test4_verifyEmptyFields() {
        verifyThat(txtEmail, hasText(""));
        verifyThat(txtName, hasText(""));
        verifyThat(txtCity, hasText(""));
        verifyThat(txtStreet, hasText(""));
        verifyThat(txtZip, hasText(""));
        verifyThat(phoneNumber, hasText(""));
        verifyThat(txtPasswd, hasText(""));
        verifyThat(txtConfirmPasswd, hasText(""));
    }

    @Test
    @Ignore
    public void Test5_emptyFields() {
        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("Campos no informados", Node::isVisible);

        clickOn("Aceptar");

    }

    @Test
    @Ignore
    public void Test6_emailFormatError() {

        clickOn(txtEmail);
        write("pepegmail@.c");

        clickOn(txtName);
        write("Pepe Garcia Fernandez");

        clickOn(txtPasswd);
        write("Garcia69");

        clickOn(txtConfirmPasswd);
        write("Garcia69");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("Formato de E-mail no valido", Node::isVisible);

        clickOn("Aceptar");

        txtEmail.clear();

        clickOn(txtEmail);
        write("pepe@gmail.com");

        txtPasswd.clear();

        txtConfirmPasswd.clear();
    }

    @Test
    @Ignore
    public void Test7_passwordFormatError() {
        clickOn(txtPasswd);
        write("master1");

        clickOn(txtConfirmPasswd);
        write("master1");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("Formato de contrasenia no valida", Node::isVisible);

        clickOn("Aceptar");

        txtPasswd.clear();

        txtConfirmPasswd.clear();
    }

    @Test
    @Ignore
    public void Test8_passwordMatchError() {
        clickOn(txtPasswd);
        write("Garcia69");

        clickOn(txtConfirmPasswd);
        write("Garcia60");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("Las contrasenias no son iguales", Node::isVisible);

        clickOn("Aceptar");

        txtPasswd.clear();

        txtConfirmPasswd.clear();

        clickOn(txtPasswd);
        write("Garcia69");

        clickOn(txtConfirmPasswd);
        write("Garcia69");
    }

    @Test
    @Ignore
    public void Test9_ZipFormatError() {
        clickOn(txtCity);
        write("Madrid");

        clickOn(txtStreet);
        write("Calle la piedra");

        clickOn(txtZip);
        write("4789630");

        clickOn(phoneNumber);
        write("665912635");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("El zip no contiene el formato correcto", Node::isVisible);

        clickOn("Aceptar");

        txtZip.clear();

        clickOn(txtZip);
        write("47920");

        phoneNumber.clear();
    }

    @Test
    @Ignore
    public void TestA_phoneFormatError() {

        clickOn(phoneNumber);
        write("6659126351021021002");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("El numero de telefono no tiene el formato correcto", Node::isVisible);

        clickOn("Aceptar");

        txtEmail.clear();
        txtName.clear();
        txtPasswd.clear();
        txtConfirmPasswd.clear();
        txtCity.clear();
        txtStreet.clear();
        txtZip.clear();
        phoneNumber.clear();
    }

    @Test
    @Ignore
    public void Test3_EmailExistsUpCorrect() {

        clickOn(txtEmail);
        write("patata@gmail.com");

        clickOn(txtName);
        write("mr potato");

        clickOn(txtPasswd);
        write("Abcd*1234");

        clickOn(txtConfirmPasswd);
        write("Abcd*1234");

        clickOn(txtCity);
        write("Madrid");

        clickOn(txtStreet);
        write("Calle la piedra");

        clickOn(txtZip);
        write("47896");

        clickOn(phoneNumber);
        write("665912635");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("El usuario ya existe", Node::isVisible);

        clickOn("Aceptar");

        txtEmail.clear();
        txtName.clear();
        txtPasswd.clear();
        txtConfirmPasswd.clear();
        txtCity.clear();
        txtStreet.clear();
        txtZip.clear();
        phoneNumber.clear();
    }

    @Test
    @Ignore
    public void Test2_SignUpCorrect() {

        String login = "pepe" + new Random().nextInt() + "@gmail.com";
        clickOn(txtEmail);
        write(login);

        clickOn(txtName);
        write("Pepe Garcia Fernandez");

        clickOn(txtPasswd);
        write("Garcia69");

        clickOn(txtConfirmPasswd);
        write("Garcia69");

        clickOn(txtCity);
        write("Madrid");

        clickOn(txtStreet);
        write("Calle la piedra");

        clickOn(txtZip);
        write("47896");

        clickOn(phoneNumber);
        write("665912635");

        clickOn(btnSave);

        // Utiliza la función verifyThat para verificar el contenido de la Alert
        verifyThat("Usuario registrado", Node::isVisible);

        clickOn("Aceptar");

        txtEmail.clear();
        txtName.clear();
        txtPasswd.clear();
        txtConfirmPasswd.clear();
        txtCity.clear();
        txtStreet.clear();
        txtZip.clear();
        phoneNumber.clear();
    }
}
