package Ejercicio1;

import java.io.*;
import java.net.*;

public class Server {
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Server (int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando al cliente");

            s = ss.accept();
            System.out.println("Cliente esta conectado");

            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            String mensaje = "";

            while (!mensaje.equals("Over")) {
                try {
                    mensaje = in.readUTF();
                    System.out.println(mensaje);

                    out.writeUTF(mensaje);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }

            System.out.println("Cerrando la conexión");

            s.close();
            in.close();
            out.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new Server(5000); // Ejecutamos el servidor en el puerto 5000
    }
}