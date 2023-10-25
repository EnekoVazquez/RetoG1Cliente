/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.CredentialErrorException;
import exception.ServerErrorException;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;

/**
 *
 * @author .
 */
public class SignInClient implements Sign{

    @Override
    public User getExecuteSignUp(User user) throws ServerErrorException, UserAlreadyExistsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getExecuteSignIn(User user) throws ServerErrorException, CredentialErrorException, UserNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
