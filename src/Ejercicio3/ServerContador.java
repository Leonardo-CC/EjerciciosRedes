package Ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerContador {
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public ServerContador(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando al cliente");

            s = ss.accept();
            System.out.println("Cliente esta conectado");

            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            String mensaje = "";
            int c = 0;

            while (!mensaje.equals("Over")) {
                try {
                    mensaje = in.readUTF();
                    System.out.println(mensaje);
                    c = mensaje.replaceAll("\\s+", "").length();

                    out.writeUTF("Tu mensaje tiene " + c + " caracteres.");
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
        new ServerContador(5000); // Ejecutamos el servidor en el puerto 5000
    }
}