import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SaludoServer {
    public static void main(String[] args) {
        int portNumber = 5555;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor iniciado.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
      // Crear hilo de Client
                new Thread(() -> Client(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Client(Socket clientSocket) {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            out.println("Hola! (Saludos acceptados: hola; adios).");

            String clientResponse;
            while ((clientResponse = in.readLine()) != null) {
                if (clientResponse.equalsIgnoreCase("hola")) {
                    out.println("Server: Hola!");
                } else if (clientResponse.equalsIgnoreCase("adios")) {
                    out.println("Server: Adios!");
                    break;
                } else {
                    out.println("Server: No te estoy entendiendo. Escribe 'hola' o 'adios'.");
                }
            }

            System.out.println("Cliente deconectado: " + clientSocket.getInetAddress().getHostAddress());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}