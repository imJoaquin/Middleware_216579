/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obj;

/**
 *
 * @author joaquin
 */
public class ProductoInterpreter {

    public static String toString(Producto producto) {
        return producto.getIdProducto() + "," + producto.getNombre() + "," + producto.getDescripcion();
    }

    public static Producto fromString(String string) {
        String[] parts = string.split(",");
        return new Producto(parts[0], parts[1]);
    }
}
