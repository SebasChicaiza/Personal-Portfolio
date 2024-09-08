
package com.mycompany.ecuacionesgrado1y2;

import java.io.*;


public class Archivo {
    private File archivo;

    public Archivo(File archivo) {
        this.archivo = archivo;
    }
    public Archivo()
    {
        
    }

    public void crearArchivo(String nombre)
    {
        archivo= new File("D:\\PUCE IS\\Semestre IV\\POO\\EcuacionesGrado1y2\\EcuacionesGrado1y2\\src\\archivos", nombre);
        try{
            if (archivo.createNewFile()){
                System.out.println("Archivo creado con éxito");
            }
        } 
        catch(IOException exepcion){
            exepcion.printStackTrace(System.out);
        }
    }
    public void borrarArchivo()
    {
        if(archivo.delete())
        {
            System.out.println("Archivo borrado con éxito");
        }
        else
        {
            System.out.println("El archivo no se pudo borrar");
        }
    }
    public void escribirArchivo(String txt)
    {
        try{
            FileWriter escritura= new FileWriter(archivo,true);
            BufferedWriter filtrado= new BufferedWriter(escritura);
            filtrado.write(txt, 0, txt.length());
            filtrado.flush();
            filtrado.close();
            escritura.close();
        } 
        catch(IOException exepcion){
            exepcion.printStackTrace(System.out);
        }
    }
    public String desplegarArchivo() {
        FileReader arc;
        BufferedReader filtrado;
        String texto = "";
        try {
            arc = new FileReader(archivo);
            filtrado = new BufferedReader(arc);
            String linea = " ";
            int cont = ContarSaltos();
            while (linea != null) {
                texto += filtrado.readLine() + "\n";
                cont--;
                if (cont == 0) {
                    break;
                }
            }
            arc.close();
            filtrado.close();
        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
        return texto;
    }

    public String leerArchivo()
    {    
        String contenido;
        String datosContenido = "";
        try {
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            contenido = lectura.readLine();
            while (contenido != null) {
                contenido = lectura.readLine();
                datosContenido += contenido;                
        }
        lectura.close();
        lector.close();
        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
        return datosContenido;
    } 
    public int ContarSaltos()
    {
        int cont = 0;
        try {
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            while (lectura.readLine() != null) {
            cont++;
        }
        lectura.close();
        lector.close();
        } catch (IOException exepcion) {
            exepcion.printStackTrace(System.out);
        }
            return cont;
    } 
}

        
        
        
        
        
        
