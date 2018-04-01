/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author ender
 */
public class ReadThread extends Thread{
    
    private Conexion conexion;
    private Connection con = null;
    private String status;
    private int ultimo;

    public ReadThread(Conexion conexion, Connection con) throws SQLException {
        this.conexion = conexion;
        this.con = con;
        this.status = "run";
        this.consultarUltimoBD();
    }

    private void consultarBD() throws SQLException {
        Statement sm = this.con.createStatement();
        ResultSet rs = sm.executeQuery("select * from mensajes where id >" + this.ultimo);
        while (rs.next()) {
            if (rs.getString(1) != null) {
                String hora=rs.getString(4).split(" ")[1].split(":")[0]+":"+rs.getString(4).split(" ")[1].split(":")[1]+" ";
                String mensaje= hora+rs.getString(2)+": "+rs.getString(3);
                System.out.println(mensaje);
            }
        }
        this.consultarUltimoBD();
    }

    private void consultarUltimoBD() throws SQLException {
        Statement sm = this.con.createStatement();
        ResultSet rs2 = sm.executeQuery("SELECT MAX(id) from mensajes");

        while (rs2.next()) {
            if (rs2.getString(1) != null) {
                this.ultimo = Integer.parseInt(rs2.getString(1));
            } else {
                this.ultimo = 0;
            }
        }
    }

    public void changeStatus(String status){
        this.status= status;
    }
    
    
    @Override
    public void run() {
        try {
            while (true) {
                if (this.status.equalsIgnoreCase("run")) {
                    this.consultarBD();
                    sleep(5000);
                } else if (this.status.equalsIgnoreCase("pause")) {
                    sleep(1000);
                } else if (this.status.equalsIgnoreCase("stop")) {
                    sleep(1000);
                    break;
                }
            }
        } catch (Exception ex) {
        }
    }
}
