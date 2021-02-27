
// Bladimir Ramos Castilla
//Tarea Final 5a Semana


package control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modelo.Linea;

public class ManejoRegistroDAO {
	
	public static ArrayList <Linea> pagina;
	
    public ManejoRegistroDAO() {
    	pagina = new ArrayList<Linea>();
    }
    
	public static void adicionar (Linea reg, JTable tabla) {
		DefaultTableModel  model = (DefaultTableModel) tabla.getModel();

		pagina.add(reg);
		
		model.addRow(new Object[] {reg.getNombre(),reg.getDireccion(),reg.getTelefono(),reg.getEmail()});
		
		grabar();
		
	}
	
	public static void grabar() {
		try {
			PrintWriter printWriter = new PrintWriter (new FileWriter ("Agenda.txt"));
			for (int i=0;i< pagina.size();i++) {
				printWriter.println(pagina.get(i).getNombre() +"|"+ pagina.get(i).getDireccion() +"|"+ pagina.get(i).getTelefono() +"|"+ pagina.get(i).getEmail());
			}
			printWriter.close();
			
		}
		catch (Exception e) {
			System.out.print(""+e);
		}
		
	}
	
	public static void limpiar_cabecera() {
		
	}
	
	
	public static boolean abrirArchivo() {
		boolean sw=false;
		try {
			File file = new File ("Agenda.txt");
			if (file.exists()) {
				sw=true;
			}
		    else {
			    file.createNewFile();
			    sw=true;
		    }
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"No se pudeo crear el archivo");
			sw=false;
		}
		
		return sw;
	}
	
	public static void cargar(JTable tabla) {
		try {
			if (abrirArchivo()) {
				BufferedReader bufferedReader = new BufferedReader (new FileReader ("Agenda.txt"));
				String registro;
				
				while ((registro = bufferedReader.readLine()) != null) {
					Linea linea = new Linea();
					StringTokenizer stringTokenizer = new StringTokenizer (registro, "|");
					linea.setNombre(stringTokenizer.nextToken().trim());
					linea.setDireccion(stringTokenizer.nextToken().trim());
					linea.setTelefono(stringTokenizer.nextToken().trim());
					linea.setEmail(stringTokenizer.nextToken().trim());
					adicionar(linea,tabla);
				}
				bufferedReader.close();
			}
		}
		catch (Exception e) {
			System.out.print("cargando:"+e);
		}
	}
}
