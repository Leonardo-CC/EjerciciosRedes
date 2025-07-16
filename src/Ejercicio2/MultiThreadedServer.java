package Ejercicio2;

import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    public static void main(String[] args) {
        int port = 6666;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while(true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());

                ClientHandler handler = new ClientHandler(clientSocket);
                new Thread(handler).start(); //iniciamos el Hilo
            }

        } catch (IOException i) {
            System.out.println("Error en ele Servidor" + i.getMessage());
        }
    }
}