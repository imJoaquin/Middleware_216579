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
import java.io.IOException;
import java.net.Socket;
import obj.Producto;
import obj.ProductoInterpreter;
import obj.Reporte;
import pantallas.ReportesFrm;

public class HiloClienteReportes extends Thread {

    Socket socket = null;
    DataInputStream in = null;
    Producto recibidoPro = null;
    Reporte recibidoRep = null;
    ReportesFrm rFrm = null;

    public HiloClienteReportes(Socket socket, DataInputStream in, ReportesFrm rFrm) {
        this.socket = socket;
        this.in = in;
        this.rFrm = rFrm;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Gson gson = new Gson();
                String mensaje = in.readUTF();
                System.out.println(mensaje);
                recibidoPro = ProductoInterpreter.fromString(mensaje);
                rFrm.agregarReporte(recibidoPro);
            } catch (IOException ex) {

            }
        }
    }

}
