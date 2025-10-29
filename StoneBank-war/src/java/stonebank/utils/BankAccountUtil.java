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
public class BankAccountUtil {

    private static Random random = new Random();

    public static int generateAccountNumber(TusuarioFacade facade) {
        List<Tusuario> lista = facade.findAll();
        int i = 0;
        
        int rand = Math.abs(BankAccountUtil.random.nextInt());
        while (i < lista.size()) {
            if (lista.get(i).getNumCuenta() == rand) {
                rand = Math.abs(BankAccountUtil.random.nextInt());
                i = 0;
            }
            i++;
        }
        return rand;
    }

    public static boolean alreadyRegisteredDNI(TusuarioFacade facade, int dni) {
        List<Tusuario> lista = facade.findAll();
        int i = 0;
        while (i < lista.size() && lista.get(i).getDniUsuario() != dni) {
            i++;
        }
        return i != lista.size();
    }

    public static boolean correctDNIFormat(String dni) {
        return dni.matches("^\\d{8}$");
    }

    public static boolean correctMoneyFormat(String dinero) {

        return dinero.matches("^\\d+.\\d{0,2}");
    }

    public static boolean correctTelephoneFormat(String tel) {

        return tel.matches("^\\d{9}");

    }
}
