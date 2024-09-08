/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author david
 */
public class ArchivoAleatorio {

    public ArchivoAleatorio() {
    }
    
    public void AlmacenarMedicina(String nombre, Double precio, String sintomas, String descripcion, String indicaciones, String categoria, int cantidad, String path)
    {
        try {
        RandomAccessFile a = new RandomAccessFile("Medicinas.dat", "rw");
        a.seek(a.length());
        //Escribir el nombre del medicamento
        StringBuffer gn = new StringBuffer(nombre);
        gn.setLength(50);
        a.writeChars(gn.toString());
        //Escribir el precio
        a.writeDouble(precio);
        //Escribir sintomas del medicamento
        StringBuffer ga = new StringBuffer(sintomas);
        ga.setLength(50);
        a.writeChars(ga.toString());
        //Escribir descripción del medicamento
        StringBuffer gd = new StringBuffer(descripcion);
        gd.setLength(50);
        a.writeChars(gd.toString());
        //Escribir indicaciones del medicamento
        StringBuffer gh = new StringBuffer(indicaciones);
        gh.setLength(50);
        a.writeChars(gh.toString());   
        //Escribir categoría
        StringBuffer gc = new StringBuffer(categoria);
        gc.setLength(20);
        a.writeChars(gc.toString());
        //Escribir cantidad del medicamento
        a.writeInt(cantidad);
        //Escribir path de la imagen del destino
        StringBuffer gi = new StringBuffer(path);
        gi.setLength(61);
        a.writeChars(gi.toString());
        a.close();
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public void leerArchivo()
    {
        try (RandomAccessFile a = new RandomAccessFile("Medicinas.dat", "r")) {
            long tbytes = a.length();
            int j=Math.toIntExact(tbytes/574);
            a.seek(0);
            for (int i = 0; i <= j; i++) {
                // Imprimir nombre
                String aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir precio
                System.out.println(a.readDouble());
                
                // Imprimir sintomas
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());
                
                // Imprimir descripcion
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());
                
                // Imprimir indicaciones
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());
                
                // Imprimir categoria
                aux = "";
                for (int k = 0; k < 20; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir cantidad
                System.out.println(a.readInt());

                // Imprimir path
                aux = "";
                for (int k = 0; k < 61; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());
            }
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void AlmacenarUsuario(String usuario, String contraseña)
    {
        try {
        RandomAccessFile a = new RandomAccessFile("Usuarios.dat", "rw");
        a.seek(a.length());
        //Escribir el usuario
        StringBuffer gu = new StringBuffer(usuario);
        gu.setLength(20);
        a.writeChars(gu.toString());
        //Escribir contraseña del usuario
        StringBuffer gc = new StringBuffer(contraseña);
        gc.setLength(20);
        a.writeChars(gc.toString());
        a.close();
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public boolean verificarCredenciales(String usuario, String contraseña) {
        StringBuffer u = new StringBuffer(usuario);
        u.setLength(20);
        String usuario1 = u.toString();
        StringBuffer c = new StringBuffer(contraseña);
        c.setLength(20);
        String contraseña1 = c.toString();
        try (RandomAccessFile a = new RandomAccessFile("Usuarios.dat", "r")) {
            long tbytes = a.length();
            int j = Math.toIntExact(tbytes / 80);
            for (int i = 0; i <= j; i++) {
                // Obtener nombre
                String aux = "";
                for (int k = 0; k < 20; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux);
                if (usuario1.equals(aux)) {
                }
                else
                {
                    break;
                }
                // Obtener Contraseña
                aux = "";
                for (int k = 0; k < 20; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux);
                if (contraseña1.equals(aux)) {
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
