/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ender
 */
public class WriteThread extends Thread{
    
    private Conexion conexion;
    private Connection con = null;
    private Mensaje msj;
    
    public WriteThread(Conexion conexion, Connection con, Mensaje msj){
        this.conexion = conexion;
        this.con= con;
        this.msj=msj;        
    }
    
    private void insertarMensaje() throws SQLException {
        Timestamp timestamp = new Timestamp(this.msj.getHora().getTime());
        PreparedStatement pp = con.prepareStatement("Insert into mensajes(emisor,mensaje,hora) VALUES ('" + this.msj.getEmisor() + "','" + this.msj.getMensaje() + "','" + timestamp + "')");
        pp.executeUpdate();
    }
    
    @Override
    public void run() {
        try {
            this.insertarMensaje();
        } catch (Exception ex) {
        }
    }
    
    
    
    
    
}
