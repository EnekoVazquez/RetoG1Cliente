/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testFx;

import application.Main;
import static application.Main.main;
import java.util.concurrent.TimeoutException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Before;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
 * Esta clase contiene pruebas unitarias para la funcionalidad del controlador de inicio de sesión (SignInController).
 * Las pruebas se realizan utilizando el marco de pruebas TestFX.
 *
 * @author Josu.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest {

    private TextField txtEmail;
    private TextField pswfPasswd;
    private Button btnLogin;
    private Button btnSignUp;

    /**
     * Método estático que inicializa la ventana de la aplicación para las pruebas.
     *
     * @throws TimeoutException Si ocurre un tiempo de espera.
     */
    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    /**
     * Configura los campos necesarios antes de cada prueba.
     */
    @Before
    public void getFields() {
        txtEmail = lookup("#txtEmail").query();
        pswfPasswd = lookup("#pswfPasswd").query();
        btnLogin = lookup("#btnLogin").query();
        btnSignUp = lookup("#btnSignUp").query();
    }

    /**
     * Prueba la inicialización de la ventana de inicio de sesión.
     */
    @Test
    public void test1_InicioVentana() {
        verifyThat(txtEmail, isVisible());
        verifyThat(pswfPasswd, isVisible());
        verifyThat(txtEmail, TextInputControlMatchers.hasText(""));
        verifyThat(pswfPasswd, TextInputControlMatchers.hasText(""));
    }

    /**
     * Prueba el caso en el que se intenta iniciar sesión con campos vacíos.
     */
    @Test
    @Ignore
    public void test2_ComprobarCamposVacios() {
        clickOn(btnLogin);
        verifyThat("Aceptar", isVisible());
        clickOn("Aceptar");
    }

    /**
     * Prueba el caso en el que se intenta iniciar sesión con el campo de contraseña vacío.
     */
    @Test
    @Ignore
    public void test3_ComprobarCampoPassVacio() {
        clickOn(txtEmail);
        write("Javi@gmail.com");
        clickOn(btnLogin);
        clickOn("Aceptar");
    }

    /**
     * Prueba el caso en el que se intenta iniciar sesión con el campo de correo electrónico vacío.
     */
    @Test
    @Ignore
    public void test4_ComprobarCampoEmailVacio() {
        clickOn(txtEmail);
        txtEmail.clear();
        clickOn(pswfPasswd);
        write("Abcd*1234");
        clickOn(btnLogin);
        clickOn("Aceptar");
    }

    /**
     * Prueba el caso en el que se intenta iniciar sesión con un formato incorrecto de correo electrónico.
     */
    @Test
    @Ignore
    public void test5_ComprobarFormatoEmail() {
        clickOn(txtEmail);
        txtEmail.clear();
        write("javi");
        clickOn(btnLogin);
        clickOn("Aceptar");
    }

    /**
     * Prueba el caso en el que se intenta iniciar sesión con un formato incorrecto de contraseña.
     */
    @Test
    @Ignore
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

    /**
     * Prueba el caso en el que el usuario no se encuentra en la base de datos.
     */
    @Test
    @Ignore
    public void test7_ComprobarUsuarioNoEncontrado() {
        clickOn(pswfPasswd);
        pswfPasswd.clear();
        write("Abcd*1234");
        clickOn(btnLogin);
        verifyThat("El usuario no ha sido encontrado", Node::isVisible);
        clickOn("Aceptar");
    }

    /**
     * Prueba deshabilitada para comprobar la base de datos no operativa.
     * Esta prueba está deshabilitada con comentarios y no se ejecuta.
     */
    
    @Test
    @Ignore
    public void test8_ComprobarDatabaseNoOperativa() {

        clickOn(pswfPasswd);
        pswfPasswd.clear();
        write("Abcd*1234");

        clickOn(txtEmail);
        txtEmail.clear();
        write("patata@gmail.com");
        clickOn(btnLogin);

        verifyThat("La conexion a la base de datos no esta operativa.", Node::isVisible);
        clickOn("Aceptar");
    }
    

    /**
     * Prueba el caso en el que todo está correcto.
     */
    @Test
    @Ignore
    public void test9_ComprobarTodoCorrecto() {
        clickOn(pswfPasswd);
        pswfPasswd.clear();
        write("Abcd*1234");
        clickOn(txtEmail);
        txtEmail.clear();
        write("patata@gmail.com");
        clickOn(btnLogin);
        verifyThat("#panePrincipal", isVisible());
    }
}

