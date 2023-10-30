/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testFx_SignIn;

import application.Main;
import static application.Main.main;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Before;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

/**
 *
 * @author Josu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest {

    private TextField txtEmail;
    private TextField pswfPasswd;
    private Button btnLogin;
    private Button btnSignUp;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @Before
    public void getFields() {
        txtEmail = lookup("#txtEmail").query();
        pswfPasswd = lookup("#pswfPasswd").query();

        btnLogin = lookup("#btnLogin").query();
        btnSignUp = lookup("#btnSignUp").query();

    }

    @Test
    public void test1_InicioVentana() {

        //this.getFields();
        verifyThat(txtEmail, isVisible());
        verifyThat(pswfPasswd, isVisible());

        verifyThat(txtEmail, TextInputControlMatchers.hasText(""));
        verifyThat(pswfPasswd, TextInputControlMatchers.hasText(""));

    }

    @Test
    public void test2_ComprobarCamposVacios() {

        clickOn(btnLogin);
        verifyThat("Aceptar", isVisible());
        clickOn("Aceptar");
    }

    @Test
    public void test3_ComprobarCampoPassVacio() {

        clickOn(txtEmail);
        write("Javi@gmail.com");
        clickOn(btnLogin);
        clickOn("Aceptar");

    }

    @Test
    public void test4_ComprobarCampoEmailVacio() {

        clickOn(txtEmail);
        txtEmail.clear();
        clickOn(pswfPasswd);
        write("Abcd*1234");
        clickOn(btnLogin);
        clickOn("Aceptar");

    }

    @Test
    public void test5_ComprobarFormatoEmail() {
        clickOn(txtEmail);
        txtEmail.clear();
        write("javi");
        clickOn(btnLogin);
        clickOn("Aceptar");
    }

    @Test
    public void test6_ComprobarFormatoPassword() {
        clickOn(pswfPasswd);
        pswfPasswd.clear();
        write("javi");
        clickOn(txtEmail);
        txtEmail.clear();
        write("javi@gmail.com");
        clickOn(btnLogin);
        clickOn("Aceptar");
    }

    @Test
    public void test7_ComprobarTodoCorrecto() {

        clickOn(pswfPasswd);
        pswfPasswd.clear();
        write("Abcd*1234");

        clickOn(btnLogin);
        verifyThat("#idPane", isVisible());

    }

}
