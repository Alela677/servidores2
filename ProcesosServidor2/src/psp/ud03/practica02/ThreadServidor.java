package psp.ud03.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ThreadServidor extends Thread {

	private String ruta;
	private DataInputStream in;
	private DataOutputStream out;
	private String nombreFichero;
	private byte[] ficheroByte;
	static FileInputStream leerFichero;
	
	public ThreadServidor(String ruta, DataInputStream in, DataOutputStream out) {
		super();
		this.ruta = ruta;
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {

		File fichero = new File(ruta);
		nombreFichero = fichero.getName();

		if (fichero.exists()) {
			ficheroExiste(fichero);
		} else {
			ficheroNoExiste();
		}
	}

	private void ficheroNoExiste() {
		try {
			out.writeUTF("ERR");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ficheroExiste(File fichero) {
		try {
			ficheroByte = Files.readAllBytes(fichero.toPath());
			out.writeUTF("OK");
			out.writeUTF(nombreFichero);
			out.writeInt(ficheroByte.length);
			out.write(ficheroByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

}
