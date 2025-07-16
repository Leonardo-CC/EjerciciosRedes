package Ejercicio2;

import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6666;

        try (
                Socket socket = new Socket(host, port);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataInputStream ms = new DataInputStream(System.in);
        ) {
            String mensaje = "";

            while (!mensaje.equalsIgnoreCase("Over")) {
                mensaje = ms.readLine();

                out.writeUTF(mensaje);

                String r = in.readUTF();
                System.out.println("Respuesta del servidor: " + r);
            }
        } catch (IOException i) {
            System.out.println("Error en el cliente: "+ i.getMessage());
        }
    }
}