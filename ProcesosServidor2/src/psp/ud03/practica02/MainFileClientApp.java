package psp.ud03.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainFileClientApp {

	final static String HOST = "192.168.22.19";
	final static int PUERTO = 4321;
	static DataInputStream in;
	static DataOutputStream out;

	public static void main(String[] args) {

		try {

			Socket cliente = new Socket(HOST, PUERTO);

			in = new DataInputStream(cliente.getInputStream());
			out = new DataOutputStream(cliente.getOutputStream());

			Scanner sc = new Scanner(System.in);

			System.out.println("ESCRIBE LA RUTA DEL FICHERO A BUSCAR");
			String ruta = sc.nextLine();

			out.writeUTF(ruta);

			String respuesta = new String(in.readUTF());
			System.out.println(respuesta);

			sc.close();
			cliente.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
