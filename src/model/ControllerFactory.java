/**
 * Clase que representa una fábrica de controladores en el sistema de firma electrónica.
 * Esta fábrica proporciona instancias de controladores para la gestión de la comunicación con el cliente.
 * Actualmente, devuelve una instancia de SignInClient como controlador para las solicitudes de conexión con el cliente.
 *
 * @author Eneko, Egoitz y Josu
 * @version 1.0
 */
package model;

/**
 * Fábrica de controladores para la gestión de la comunicación con el cliente.
 */
public class ControllerFactory {

    /**
     * Método que devuelve una instancia del controlador para la gestión de la
     * comunicación con el cliente.
     *
     * @return Instancia del controlador de tipo Sign.
     */
    public Sign getSocket() {
        Sign sign;
        sign = new SignInClient();
        return sign;
    }
}
