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
    
    public void AlmacenarDestino(String nombre, String provincia, Double precio, String actividades, String descripcion, String horario, int calificacion, String promociones, String contacto, String path)
    {
        try {
        RandomAccessFile a = new RandomAccessFile("Destinos.dat", "rw");
        a.seek(a.length());
        //Escribir el nombre del destino
        StringBuffer gn = new StringBuffer(nombre);
        gn.setLength(50);
        a.writeChars(gn.toString());
        //Escribir provincia del destino
        StringBuffer gp = new StringBuffer(provincia);
        gp.setLength(16);
        a.writeChars(gp.toString());
        //Escribir el precio
        a.writeDouble(precio);
        //Escribir actividades del destino
        StringBuffer ga = new StringBuffer(actividades);
        ga.setLength(50);
        a.writeChars(ga.toString());
        //Escribir descripción del destino
        StringBuffer gd = new StringBuffer(descripcion);
        gd.setLength(50);
        a.writeChars(gd.toString());
        //Escribir horario del destino
        StringBuffer gh = new StringBuffer(horario);
        gh.setLength(50);
        a.writeChars(gh.toString());
        //Escribir calificación del destino
        a.writeInt(calificacion);
        //Escribir promociones del destino
        StringBuffer gpromo = new StringBuffer(promociones);
        gpromo.setLength(50);
        a.writeChars(gpromo.toString());    
        //Escribir contacto del destino
        StringBuffer gc = new StringBuffer(contacto);
        gc.setLength(13);
        a.writeChars(gc.toString());
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
    public void AlmacenarUsuario(String usuario, String contraseña)
    {
        try {
        RandomAccessFile a = new RandomAccessFile("Usuarios.dat", "rw");
        
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
    public void leerArchivo()
    {
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            long tbytes = a.length();
            int j=Math.toIntExact(tbytes/692);
            a.seek(0);
            for (int i = 0; i <= j; i++) {
                // Imprimir nombre
                String aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir provincia
                aux = "";
                for (int k = 0; k < 16; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir precio
                System.out.println(a.readDouble());

                // Imprimir actividades
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir descripción
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir horario
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir calificaciones
                System.out.println(a.readInt());

                // Imprimir promociones
                aux = "";
                for (int k = 0; k < 50; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

                // Imprimir contacto
                aux = "";
                for (int k = 0; k < 13; k++) {
                    aux += a.readChar();
                }
                System.out.println(aux.trim());

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
    public ArrayList<ArrayList<Object>> obtenerTodos()
    {
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            ArrayList<ArrayList<Object>> Registros = new ArrayList<>();
            long tbytes = a.length();
            int j=Math.toIntExact(tbytes/692);
            a.seek(0);
            for (int i = 0; i <= j; i++) {
                Registros.add(ObtenerRegistro(i));
            }
            System.out.println(Registros);
            return Registros;            
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
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
                if (!usuario1.equals(aux)) {
                    return false;
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

    //SEBAS
    //Leer el campo "Provincia" de cada registro e imprimir los registros que cumplen con la condicion(campo == provincia)
    /*public ArrayList<Object> ImprimirPorProvincia(String provincia)
    {//SEBAS
        //Leer el campo "Provincia" de cada registro e imprimir los registros que cumplen con la condicion(campo == provincia)
        ArrayList<Object> DestProv = new ArrayList<>();
        try (RandomAccessFile a = new RandomAccessFile("archivo.dat", "r")) {
            // Imprimir nombre
            int numRegs = (int) (a.length() / 690);
            int i, j;
            String aux = "";
            for(i = 0; i < numRegs; i++){            
                a.seek(100*(i+1));
                for(j = 0; j < 16; j++){
                    aux += a.readChar();
                }
                if(aux.equals(provincia)){
                    //Saber el numero de registro y leerlo completo, guardar en un string todo?
                    DestProv.add(leerRegistro(i));                    
                }
            }           
            return DestProv;
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }*/
    public ArrayList<ArrayList<Object>> ObtenerPorProvincia(String provincia) {//SEBAS
        //Leer el campo "Provincia" de cada registro e imprimir los registros que cumplen con la condicion(campo == provincia)
        ArrayList<ArrayList<Object>> DestProv = new ArrayList<>();
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            // Imprimir nombre
            int numRegs = (int) (a.length() / 692);
            int i, j;
            String aux = "";
            for (i = 0; i < numRegs; i++) {
                a.seek(100 + (692*i));
                for (j = 0; j < 16; j++) {
                    aux += a.readChar();                    
                }                   
                if (aux.trim().equals(provincia)) {
                    //Saber el numero de registro y leerlo completo, guardar en un string todo?
                    DestProv.add(ObtenerRegistro(i));                }
                aux="";
            }            
            return DestProv;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<ArrayList<Object>> ObtenerPorPrecio(Double precio) {//SEBAS
        //Leer el campo "Precio" de cada registro e imprimir los registros que cumplen con la condicion(campo <= precio)
        ArrayList<ArrayList<Object>> DestXPrecio = new ArrayList<>();
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            // Imprimir nombre
            int numRegs = (int) (a.length() / 692);
            int i, j;
            Double aux;
            for (i = 0; i < numRegs; i++) {
                a.seek(132 * (i + 1));
                aux = a.readDouble();
                if (aux <= precio) {
                    //Saber el numero de registro y leerlo completo, guardar en un string todo?
                    DestXPrecio.add(ObtenerRegistro(i));
                }
            }
            return DestXPrecio;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<ArrayList<Object>> ObtenerPorProvinciaYPrecio(String provincia, Double precio) {//SEBAS
        //Leer el campo "Provincia" y "Precio" de cada registro e imprimir los registros que cumplen con la condicion(campo == provincia && campo==precio)
        ArrayList<ArrayList<Object>> ProvYPrecio = new ArrayList<>();
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            //Busca la provincia y verifica el precio
            int numRegs = (int) (a.length() / 692);
            int i, j;
            String aux = "";
            Double aPrecio;
            for (i = 0; i < numRegs; i++) {
                a.seek(100 + (692*i));
                for (j = 0; j < 16; j++) {
                    aux += a.readChar();
                    
                }                
                aPrecio = a.readDouble();                
                if (aux.trim().equals(provincia)) {
                    if (aPrecio<=precio) {
                        ProvYPrecio.add(ObtenerRegistro(i));
                    }
                }
                aux = "";
            }
            return ProvYPrecio;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public int[] ObtenerDestinosPorCalificacionYProvincia(int calificacion) {
        int[] calificacionPorProvincia = new int[24];
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            long tbytes = a.length();
            int j = Math.toIntExact(tbytes / 692);
            String provincia;
            for (int i = 0; i < j; i++) {
                // Posición para leer la calificación
                long pos = i * 692 + 440;
                a.seek(pos);
                int aux = a.readInt();

                if (aux == calificacion) {
                    // Posición para leer la provincia
                    long provinciaPos = pos - 340;
                    a.seek(provinciaPos);
                    provincia = "";
                    for (int k = 0; k < 16; k++) {
                        provincia += a.readChar();
                    }
                    provincia = provincia.trim();
                    switch (provincia) {
                        case "Azuay":
                            calificacionPorProvincia[0]++;
                            break;
                        case "Bolivar":
                            calificacionPorProvincia[1]++;
                            break;
                        case "Cañar":
                            calificacionPorProvincia[2]++;
                            break;
                        case "Carchi":
                            calificacionPorProvincia[3]++;
                            break;
                        case "Chimborazo":
                            calificacionPorProvincia[4]++;
                            break;
                        case "Cotopaxi":
                            calificacionPorProvincia[5]++;
                            break;
                        case "El Oro":
                            calificacionPorProvincia[6]++;
                            break;
                        case "Esmeraldas":
                            calificacionPorProvincia[7]++;
                            break;
                        case "Galapagos":
                            calificacionPorProvincia[8]++;
                            break;
                        case "Guayas":
                            calificacionPorProvincia[9]++;
                            break;
                        case "Imbabura":
                            calificacionPorProvincia[10]++;
                            break;
                        case "Loja":
                            calificacionPorProvincia[11]++;
                            break;
                        case "Los Rios":
                            calificacionPorProvincia[12]++;
                            break;
                        case "Manabi":
                            calificacionPorProvincia[13]++;
                            break;
                        case "Morona Santiago":
                            calificacionPorProvincia[14]++;
                            break;
                        case "Napo":
                            calificacionPorProvincia[15]++;
                            break;
                        case "Orellana":
                            calificacionPorProvincia[16]++;
                            break;
                        case "Pastaza":
                            calificacionPorProvincia[17]++;
                            break;
                        case "Pichincha":
                            calificacionPorProvincia[18]++;
                            break;
                        case "Santa Elena":
                            calificacionPorProvincia[19]++;
                            break;
                        case "Santo Domingo":
                            calificacionPorProvincia[20]++;
                            break;
                        case "Sucumbios":
                            calificacionPorProvincia[21]++;
                            break;
                        case "Tungurahua":
                            calificacionPorProvincia[22]++;
                            break;
                        case "Zamora Chinchipe":
                            calificacionPorProvincia[23]++;
                            break;
                        default:
                            System.out.println("Provincia inválida: " + provincia);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return calificacionPorProvincia;
    }

    public ArrayList<Object> ObtenerRegistro(int numRegs)
    {
        ArrayList<Object> Registro = new ArrayList<>();
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            // Imprimir nombre
            String aux = "";
            int i ;
            a.seek(692*numRegs);
            for (i = 0; i < 50; i++) {
                aux += a.readChar();
            }
            Registro.add(aux);
            System.out.println(aux.trim());

            // Imprimir provincia
            aux = "";
            for (i = 0; i < 16; i++) {
                aux += a.readChar();
            }
            Registro.add(aux);
            System.out.println(aux.trim());

            // Imprimir precio
            Registro.add(a.readDouble());
            //System.out.println(a.readDouble());

            // Imprimir actividades
            aux = "";
            for (i = 0; i < 50; i++) {
                aux += a.readChar();
            }
            Registro.add(aux);
            System.out.println(aux.trim());

            // Imprimir descripción
            aux = "";
            for (i = 0; i < 50; i++) {
                aux += a.readChar();
            }
            Registro.add(aux);
            System.out.println(aux.trim());

            // Imprimir horario
            aux = "";
            for (i = 0; i < 50; i++) {
                aux += a.readChar();
            }
            Registro.add(aux);
            System.out.println(aux.trim());

            // Imprimir calificaciones
            
            Registro.add(a.readInt());
            // Imprimir promociones
            aux = "";
            for (i = 0; i < 50; i++) {
                aux += a.readChar();
            }
            System.out.println(aux.trim());
            Registro.add(aux);
            // Imprimir contacto
            aux = "";
            for (i = 0; i < 13; i++) {
                aux += a.readChar();
            }
            System.out.println(aux.trim());
            Registro.add(aux);
            // Imprimir path
            aux = "";
            for (i = 0; i < 61; i++) { // Cambié el tamaño de 60 a 50 para mantener la consistencia
                aux += a.readChar();
            }
            System.out.println(aux.trim());
            Registro.add(aux);
            return Registro;
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public ArrayList<String> obtenerLista(int calificacion) throws FileNotFoundException, IOException {
        try (RandomAccessFile a = new RandomAccessFile("Destinos.dat", "r")) {
            long tbytes = a.length();
            int j = Math.toIntExact(tbytes / 692), mov = 0;

            ArrayList<String> califica = new ArrayList<>();
            for (int i = 0; i < j; i++) {
                // Posición para leer la calificación
                long pos = i * 692 + 440;
                a.seek(pos);
                int aux = a.readInt();
                if (aux == calificacion) {
                    mov += 692;
                    a.seek(mov);
                    String auxi = "";
                    for (int k = 0; k < 50; k++) {
                        auxi += a.readChar();
                    }
                    califica.add(auxi);

                    // Imprimir provincia
                    auxi = "";
                    for (int k = 0; k < 16; k++) {
                        auxi += a.readChar();
                    }
                    // Imprimir precio
                    Double num = a.readDouble();
                    String numeroString = String.valueOf(num);
                    califica.add(numeroString);

                    califica.add(auxi);

                    // Imprimir actividades
                    auxi = "";
                    for (int k = 0; k < 50; k++) {
                        auxi += a.readChar();
                    }

                    califica.add(auxi);
                    // Imprimir descripción
                    auxi = "";
                    for (int k = 0; k < 50; k++) {
                        auxi += a.readChar();
                    }

                    califica.add(auxi);
                    // Imprimir horario
                    auxi = "";
                    for (int k = 0; k < 50; k++) {
                        auxi += a.readChar();
                    }
                    califica.add(auxi);

                    // Imprimir calificaciones
                    int numero = a.readInt();
                    numeroString = String.valueOf(numero);
                    califica.add(numeroString);

                    // Imprimir promociones
                    auxi = "";
                    for (int k = 0; k < 50; k++) {
                        auxi += a.readChar();
                    }
                    califica.add(auxi);

                    // Imprimir contacto
                    auxi = "";
                    for (int k = 0; k < 13; k++) {
                        auxi += a.readChar();
                    }
                    califica.add(auxi);
                }
            }
            return califica;
        }

    }
    public void ImprimirTabla(int calificacion) throws IOException {
        ArrayList<String> califica = obtenerLista(calificacion);
        String matriz[][] = new String[califica.size() / 9][9];
        int k = 0;
        for (int i = 0; i < califica.size(); i++) {
            matriz[k][0] = califica.get(i);
            i++;
            matriz[k][1] = califica.get(i);
            i++;
            matriz[k][2] = califica.get(i);
            i++;
            matriz[k][3] = califica.get(i);
            i++;
            matriz[k][4] = califica.get(i);
            i++;
            matriz[k][5] = califica.get(i);
            i++;
            matriz[k][6] = califica.get(i);
            i++;
            matriz[k][7] = califica.get(i);
            i++;
            matriz[k][8] = califica.get(i);
            k++;
        }
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        // Crear y mostrar la tabla con los datos leídos
        /*JTable jTable1 = new JTable();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String[]{"Nombre", "Provincia", "Precio", "Actividades", "Descripción", "Horario", "Calificación",
                    "Promoción", "Contacto"}
        ));

        // Crear el frame y ajustarlo al tamaño de la tabla
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(jTable1)); // Agregar la tabla dentro de un JScrollPane para habilitar el desplazamiento
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo la ventana actual al hacer clic en la X
        frame.pack(); // Ajustar el tamaño del frame según el contenido del JTable
        frame.setVisible(true);*/
        JTable jTable1 = new JTable();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{"Nombre", "Provincia", "Precio", "Actividades", "Descripción", "Horario", "Calificación",
                    "Promoción", "Contacto"}
        ));

        // Ajustar el tamaño de las columnas al contenido
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Crear el frame y ajustarlo al tamaño de la tabla
        JFrame frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane(jTable1);
        frame.add(scrollPane);

        // Ajustar el tamaño del JFrame al tamaño de la JTable
        frame.pack();
        frame.setVisible(true);

        // Ajustar el tamaño del JFrame exactamente al tamaño del JScrollPane
        frame.setSize(720, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLocation(0, 450);
    }
   
}
