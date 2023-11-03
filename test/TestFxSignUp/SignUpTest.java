package TestFxSignUp;

import application.Main;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
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
        paneSignUp = lookup("#paneSignUp").query();
    }

    @Test
    public void Test_windowIsOpen() {
        verifyThat(paneSignUp, isVisible());
    }

    @Test
    public void Test1_initialWindow() {
        clickOn("#btnSignUp");
    }

    @Test
    public void Test2_verifyEmptyFields() {
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
    public void Test3_emptyFields() {
        clickOn(btnSave);

        clickOn("Aceptar");
    }

    @Test
    public void Test4_emailFormatError() {

        clickOn(txtEmail);
        write("pepegmail@.c");

        clickOn(txtName);
        write("Pepe Garcia Fernandez");
        clickOn(txtPasswd);
        write("Garcia69");
        clickOn(txtConfirmPasswd);
        write("Garcia69");
        clickOn(btnSave);
        clickOn("Aceptar");
        txtEmail.clear();
        clickOn(txtEmail);
        write("pepe@gmail.com");
        txtPasswd.clear();
        txtConfirmPasswd.clear();
    }

    @Test
    public void Test5_passwordFormatError() {
        clickOn(txtPasswd);
        write("master1");
        clickOn(txtConfirmPasswd);
        write("master1");
        clickOn(btnSave);
        clickOn("Aceptar");
        txtPasswd.clear();
        txtConfirmPasswd.clear();
    }

    @Test
    public void Test6_passwordMatchError() {
        clickOn(txtPasswd);
        write("Garcia69");
        clickOn(txtConfirmPasswd);
        write("Garcia60");
        clickOn(btnSave);
        clickOn("Aceptar");
        txtPasswd.clear();
        txtConfirmPasswd.clear();
        clickOn(txtPasswd);
        write("Garcia69");
        clickOn(txtConfirmPasswd);
        write("Garcia69");
    }

    @Test
    public void Test7_ZipFormatError() {
        clickOn(txtCity);
        write("Madrid");
        clickOn(txtStreet);
        write("Calle la piedra");
        clickOn(txtZip);
        write("4789630");
        clickOn(phoneNumber);
        write("665912635");
        clickOn(btnSave);
        clickOn("Aceptar");
        txtZip.clear();
        clickOn(txtZip);
        write("47920");
        phoneNumber.clear();
    }

    @Test
    public void Test8_ZipFormatError() {
        clickOn(phoneNumber);
        write("6659126351021021002");
        clickOn(btnSave);

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
