import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
	/*Programacion concurente y distribuitiva
	 * Actividad 1: Hilos y Sockets
	 * Nombre y apellido: Bogdana Hirlav Tifrea
	 * DNI: Y7437620D
	 * Nombre del profesor: Jose Delgado
	 */

/*Programa que esta utilizando sockets para que el server devuelve saludo al cliente.
 * Si el cliente saluda con hola se le responde con hola.
 * Si el cliente responde con adios se le responde adios y se cierra la programa.
 * si el cliente responde c distinto a los saludos acceptados se le responde que 
 * el server no reconoce su saludo y que intenta de nuevo. 
 */
	public class SaludoClient {
		    public static void main(String[] args) {
		        String serverAddress = "Servidor IP";
		        int portNumber = 5555;
	
		        try (
		            Socket socket = new Socket(serverAddress, portNumber);
		            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
		        ) {
		            String serverResponse;
		            while ((serverResponse = in.readLine()) != null) {
		                System.out.println("Server: " + serverResponse);
	
		                String userInput = stdIn.readLine();
		                out.println(userInput);
	
		                if (userInput.equalsIgnoreCase("adios")) {
		                    break;
		                }
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}