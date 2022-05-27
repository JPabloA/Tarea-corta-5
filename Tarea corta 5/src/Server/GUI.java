package Server;
import java.awt.event.*;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import Common.Casilla;
import Common.Constantes;
import static Common.Constantes.X;
import static Common.Constantes.Y;
import Common.Dot;
import Common.Mapa;
import Common.Target;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GUI implements ActionListener, Constantes{

    JFrame ventana;
    JButton next;
    Mapa mapa;
    Dot dot;
    Socket client;
    ObjectOutputStream output;
    
    public GUI(){

        ventana = new JFrame("Server");
        mapa = new Mapa(this);


        ventana.add(mapa.panelTablero);

        next = new JButton("continuar");
        next.addActionListener(this);
        next.setActionCommand("next");

        ventana.add(next, BorderLayout.SOUTH);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);

        dot = new Dot();

        Server server = new Server(dot);
        Thread hilo = new Thread(server);
        hilo.start();
        
        moveDot();
        run();

    }

    @Override
    public void actionPerformed(ActionEvent e) {  
    }

    public void moveDot(){
        mapa.tablero[dot.lastPosition[X]][dot.lastPosition[Y]].clearDot();
        mapa.tablero[dot.currentPosition[X]][dot.currentPosition[Y]].setAsDot();
    }
    
    public void cleanTarget(){
        mapa.tablero[dot.target.oldCoords[X]][dot.target.oldCoords[Y]].clearTarget();
    }
    
    public void setTarget(){
        mapa.tablero[dot.target.coords[X]][dot.target.coords[Y]].setAsTarget();
    }
    

    public void run(){
        while (true){
            dot.move();
            cleanTarget();
            setTarget();
            try {
            client = new Socket("127.0.0.1", 7777); 
            output = new ObjectOutputStream(client.getOutputStream()); 
            output.writeObject(dot); 
            output.flush(); 
            output.close();
            client.close();
            } catch (IOException ex) {
            //TODO: handle exception
            }
            moveDot();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

}