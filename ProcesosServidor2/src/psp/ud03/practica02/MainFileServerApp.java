package psp.ud03.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFileServerApp {

	static ServerSocket servidor;
	static Socket cliente = null;
	final static int PUERTO = 4321;
	static DataInputStream in;
	static DataOutputStream out;

	public static void main(String[] args) {

		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("SERVIDOR INICIADO");

			while (true) {

				cliente = servidor.accept();

				in = new DataInputStream(cliente.getInputStream());
				out = new DataOutputStream(cliente.getOutputStream());

				System.out.println("SERVIDOR LEYENDO EL MENSAJE");

				String ruta = in.readUTF();

				System.out.println("MENSAJE RECIBIDO" + " " + ruta);

				ThreadServidor nuevoHilo = new ThreadServidor(ruta, in, out);
				nuevoHilo.start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
