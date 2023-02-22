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
import obj.Reporte;
import obj.ReporteInterpreter;
import pantallas.ProductosFrm;

public class HiloClienteProductos extends Thread {

    Socket socket = null;
    DataInputStream in = null;
    Producto recibidoPro = null;
    Reporte recibidoRep = null;
    ProductosFrm pFrm = null;

    public HiloClienteProductos(Socket socket, DataInputStream in, ProductosFrm pFrm) {
        this.socket = socket;
        this.in = in;
        this.pFrm = pFrm;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Gson gson = new Gson();
                String mensaje = in.readUTF();
                System.out.println(mensaje);
                recibidoRep = ReporteInterpreter.fromString(mensaje);
                pFrm.agregarReporte(recibidoRep);
            } catch (IOException ex) {

            }
        }
    }

}
