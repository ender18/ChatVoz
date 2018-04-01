/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.Date;

/**
 *
 * @author ender
 */
public class Mensaje {
    
    private int id;
    private String emisor;
    private String mensaje;
    private Date hora;

    public Mensaje(String emisor, String mensaje) {
        this.emisor = emisor;
        this.mensaje = mensaje;
        this.hora = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
        
}
