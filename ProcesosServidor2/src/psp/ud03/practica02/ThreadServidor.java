package psp.ud03.practica02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class ThreadServidor extends Thread {

	private String ruta;
	private DataInputStream in;
	private DataOutputStream out;

	public ThreadServidor(String ruta, DataInputStream in, DataOutputStream out) {
		super();
		this.ruta = ruta;
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {

		File fichero = new File(ruta);

		if (fichero.exists()) {

			try {
				out.writeUTF("OK");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				out.writeUTF("ERR");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}

}
