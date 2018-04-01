/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author ender
 */
public class Main {

    /*Inicia el Chat*/
    public static void main(String[] args) throws SQLException {        
        new Negocio().startChat();
    }
}
