package Ejercicio2;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override public void run() {
        try (
              DataInputStream input = new DataInputStream(clientSocket.getInputStream());
              DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        ) {
            String mensaje = "";
            while (!mensaje.equals("Over")) {
                mensaje = input.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);

                output.writeUTF("Eco: " + mensaje);
            }
        } catch (IOException i) {
            System.out.println("Error con el cliente: " + i.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException i) {
                System.out.println("Error al cerrar el socket: " + i.getMessage());
            }
        }
    }
}