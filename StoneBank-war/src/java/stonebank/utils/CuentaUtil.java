/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.utils;

import java.util.List;
import java.util.Random;
import stonebank.ejb.TusuarioFacade;
import stonebank.entity.Tusuario;

/**
 *
 * @author rafaelpernil
 */
public class CuentaUtil {

    /**Genera un número de cuenta válido
     * 
     * @param facade Fachada (Necesaria)
     * @return Número de cuenta
     */
    public static int generarNumeroDeCuenta(TusuarioFacade facade) {
        List<Tusuario> lista = facade.findAll();
        int i = 0;
        Random random = new Random();
        int rand = Math.abs(random.nextInt());
        while (i < lista.size()) {
            if (lista.get(i).getNumCuenta() == rand) {
                rand = Math.abs(random.nextInt());
                i = 0;
            }
            i++;
        }
        return rand;
    }

    /**Comprueba si el DNI ya está dado de alta
     * 
     * @param facade Fachada (Necesaria)
     * @param dni
     * @return 
     */
    public static boolean DNIyaRegistrado(TusuarioFacade facade, int dni) {
        List<Tusuario> lista = facade.findAll();
        int i = 0;
        while (i < lista.size() && lista.get(i).getDniUsuario() != dni) {
            i++;
        }
        return i != lista.size();
    }

    /**Comprueba que el formato del DNI que se pasa como parámetro sea correcto 
     * (que tenga 8 dígitos)
     * 
     * @param dni
     * @return
     */
    public static boolean esCorrectoFormatoDNI(String dni) {
        return dni.matches("^\\d{8}$");
    }

    /**Comprueba que el formato de la cantidad monetaria que se pasa como parámetro sea
     * correcto (que tenga 2 decimales)
     * 
     * @param dinero Cantidad a comprobar, pasada como String
     * @return 
     */
    public static boolean esCorrectoFormatoDinero(String dinero) {

        return dinero.matches("^\\d+.\\d{0,2}");
    }

    /**Comprueba que el formato del teléfono que se pasa como parámetro sea
     * correcto (que tenga 9 dígitos)
     * 
     * @param tel Teléfono a comprobar
     * @return 
     */
    public static boolean esCorrectoFormatoTelefono(String tel) {

        return tel.matches("^\\d{9}");

    }
}