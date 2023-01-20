package psp.ud03.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainFileClientApp {

	final static int PUERTO = 4321;
	static DataInputStream in;
	static DataOutputStream out;
	static byte[] message = null;
	static PrintWriter pw;
	static String nombreFichero = null;

	public static void main(String[] args) {

		try {
			
			String HOST = host();
			Socket cliente = new Socket(HOST, PUERTO);

			in = new DataInputStream(cliente.getInputStream());
			out = new DataOutputStream(cliente.getOutputStream());

			Scanner sc = new Scanner(System.in);

			System.out.println("ESCRIBE LA RUTA DEL FICHERO A BUSCAR");
			String ruta = sc.nextLine();

			out.writeUTF(ruta);

			mostrarResultado();

			ficheroCliente();

			sc.close();
			cliente.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void ficheroCliente() throws IOException {
		File salidaFichero = new File(nombreFichero);
		FileOutputStream output = new FileOutputStream(salidaFichero);
		output.write(message);
		output.close();

	}

	private static void mostrarResultado() throws IOException {
		String respuesta = new String(in.readUTF());
		nombreFichero = in.readUTF();
		int length = in.readInt();
		if (length > 0) {
			message = new byte[length];
			in.readFully(message, 0, message.length);
		}

		System.out.println(respuesta);
		System.out.println(new String(message));
	}

	private static String host() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		String host = address.getHostName();
		return host;
	}
}
