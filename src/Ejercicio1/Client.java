package Ejercicio1;

import java.io.*;
import java.net.*;

public class Client {
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private DataInputStream ms = null;

    public Client(String addr, int port) {
        try {
            s = new Socket(addr, port);
            System.out.println("Conectado al servidor");

            in = new DataInputStream(System.in);

            ms = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println("Error de host: " + u);
            return;
        } catch (IOException i) {
            System.out.println("Error de E/S: " + i);
            return;
        }

        String mensaje = "";

        while (!mensaje.equals("Over")) {
            try {
                mensaje = in.readLine();
                out.writeUTF(mensaje);

                String r = ms.readUTF();
                System.out.println("Mensaje servidor: "+ r);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            in.close();
            out.close();
            s.close();
            ms.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }
}