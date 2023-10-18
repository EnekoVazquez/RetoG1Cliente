/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Eneko.
 */
public class ControllerFactory {

    public Sign getSocket() {
        Sign sign;
        sign = (Sign) new SignInClient();
        return sign;
    }
}
