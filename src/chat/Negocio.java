/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import Voz.Voz;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ender
 */
public class Negocio {
    
    private String nickname="";
    private Consultas consultas;
    
    /*Inicia el chat dandole la bienvenida al usuario y solicitando su nombre (Nickname)*/
    public void startChat() throws SQLException, IOException{
        
        this.doWelcome();
        System.out.println("Iniciando sesión...");
        this.consultas = new Consultas();
        this.showMenu();        
        
        //Metodo para crear conexión e inserta una fila que ha iniciado sesión.
        //El hilo de actualizar mensaje empieza de manera infinita

        while (true) {
            String mensaje = this.readLine();

            if (mensaje.isEmpty()) //Aqui va el metodo para enviar mensajes, deteniendo el hilo
            {   this.consultas.changeStatusRead("pause");
                System.out.print("Su mensaje: ");
                String msj = new Voz().reconocimiento();
                Mensaje msjToSend = new Mensaje(this.nickname, msj);
                this.consultas.insertarMensaje(msjToSend);
                this.consultas.changeStatusRead("run");
            } else if (mensaje.equalsIgnoreCase("nick")) {
                this.changeNickname();
            } else if (mensaje.equalsIgnoreCase("help")) {
                this.showMenu();
            } else if (mensaje.equalsIgnoreCase("exit")) {
                this.consultas.changeStatusRead("stop");
                System.out.println("*************************************");
                System.out.println("*******SU SESIÓN HA FINALIZADO*******");
                System.out.println("*************************************");
                break;
            }
        }
    }
    
    /*Imprime el mensaje de bienvenida, pide el nickname para el usuario*/
    private void doWelcome(){
        System.out.println("*************************************");
        System.out.println("*********BIENVENIDO AL CHAT**********");
        System.out.println("*************************************");
        System.out.println("");
        System.out.println("Inserte su Nickname:");
        this.nickname = this.readLine();
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("Usted se ha identificado como: \"" +this.nickname+"\"");
        System.out.println("*************************************");
        System.out.println("");        
        System.out.print("Presione Enter para continuar...");
        this.readLine();        
    }
    
    private void changeNickname(){
        System.out.println("");
        System.out.println("Inserte su nuevo Nickname:");
        this.nickname = this.readLine();
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("Su nuevo Nickname es: "+this.nickname);
        System.out.println("*************************************");
        System.out.println("Digite una opción, para repetir el menú escriba \"help\"");
        
    }
    
    private void showMenu(){
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("************** MENÚ *****************");
        System.out.println("- Presione Enter para escribir un mensaje");
        System.out.println("- Escriba \"nick\" para cambiar el nickname");
        System.out.println("- Escriba \"exit\" para salir");
        System.out.println("- Escriba \"help\" para repetir este menú");
        System.out.println("*************************************");        
        System.out.println("Digite una opción...");
    }
    
    
    /*Lee una linea de texto*/
    public String readLine() {
        String texto = "";
        try {
            Scanner scanner = new Scanner(System.in);
            texto = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }

    
}
