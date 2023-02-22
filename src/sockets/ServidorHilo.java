/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

/**
 *
 * @author joaquin
 */
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import javax.swing.JFrame;
import obj.Producto;
import obj.ProductoInterpreter;
import obj.Reporte;
import obj.ReporteInterpreter;

public class ServidorHilo extends Thread {

    Socket socket = null;
    DataInputStream in = null;
    DataOutputStream out = null;
    JFrame frame = null;

    public ServidorHilo(Socket socket, DataInputStream in, DataOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Gson gson = new Gson();

                String mensaje = in.readUTF();

                System.out.println(mensaje);

                if (mensaje.contains("{")) {
                    Producto producto = gson.fromJson(mensaje, Producto.class);

                    mensaje = ProductoInterpreter.toString(producto);

                    out.writeUTF(mensaje);
                } else {

                    Reporte reporte = ReporteInterpreter.fromString(mensaje);

                    String reporteJson = gson.toJson(reporte);

                    out.writeUTF(reporteJson);
                }

            } catch (IOException ex) {

            }
        }
    }

}
