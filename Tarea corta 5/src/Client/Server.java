package Client;

import java.net.*;
import java.io.*;
import Common.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{
    ServerSocket server;
    Socket client;
    ObjectInputStream input; 
    Dot dot;
    Dot dotAux; // Para copia luego los datos al dot
    
    public Server(Dot d){
        dot = d;
        try {
            System.out.println("Entre a la conexion");
            server = new ServerSocket(7777);
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run(){
      try {
            while(true){
                client = server.accept();
                input = new ObjectInputStream(client.getInputStream());
                dotAux = (Dot)input.readObject();
                dot.currentPosition = dotAux.currentPosition;
                dot.lastPosition = dotAux.lastPosition;
                dot.target = dotAux.target;
                input.close(); 
                client.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
}

