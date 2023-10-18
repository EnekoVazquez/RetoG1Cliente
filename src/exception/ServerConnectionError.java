/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Eneko.
 */
public class ServerConnectionError extends Exception {

    public ServerConnectionError(String message) {
        super(message);
    }
}
