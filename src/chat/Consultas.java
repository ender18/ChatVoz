/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ION Xtreme
 */
public class Consultas{

    Conexion conexion;
    Connection con = null;
    ReadThread reader;

    public Consultas() throws SQLException {
        this.obtenerConexion();
        this.doReadThread();
    }

    private void doReadThread() throws SQLException{
        this.reader = new ReadThread(conexion, con);      
        this.reader.start(); 
    }
    
    private void obtenerConexion() {
        this.conexion = new Conexion();
        this.con = conexion.connect();
    }

    public void changeStatusRead(String status) throws SQLException {
            this.reader.changeStatus(status);
    }
        
    public void insertarMensaje(Mensaje msj) throws SQLException {
        WriteThread escribe = new WriteThread(this.conexion, this.con, msj);
        escribe.start();
    }
    
    
}
