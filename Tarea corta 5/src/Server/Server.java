package Server;

import java.net.*;
import java.io.*;
import Common.*;

public class Server implements Runnable{
    ServerSocket server;
    Socket client; 
    ObjectInputStream input; // Para recibir un paquete o mensaje (Leer objetos) (Necesario en todos aquellos dispositivos que queramos leer un mensaje)
    Dot dot;

    public Server(Dot d){
        dot = d;
        try {
            server = new ServerSocket(4444);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    public void run(){
        try {
            while(true){
                client = server.accept();
                input = new ObjectInputStream(client.getInputStream());
                dot.target = (Target)input.readObject();
                input.close();
                client.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
    
}
