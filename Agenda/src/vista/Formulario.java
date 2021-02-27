
// Bladimir Ramos Castilla
//Tarea Final 5a Semana
package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import modelo.Linea;
import control.ManejoRegistroDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Formulario extends JFrame {

	private JPanel contentPane;
	private static JTextField txtNombre;
	private static JTextField txtDireccion;
	private static JTextField txtTelefono;
	private static JTextField txtEmail;
	private JTable tabla;
	private Linea registro;
	private ArrayList <Linea> pagina;
	private static ManejoRegistroDAO manejaregistro;
	int j=0;
	private char tecla;
	private static JButton btnRegistrar;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Formulario() {
		
		pagina = new ArrayList<Linea>();
	
		manejaregistro = new ManejoRegistroDAO();
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 945);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(15, 92, 69, 20);
		contentPane.add(lblNombre);
		
		JLabel lblDirecion = new JLabel("Direcion");
		lblDirecion.setBounds(15, 128, 69, 20);
		contentPane.add(lblDirecion);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(15, 164, 69, 20);
		contentPane.add(lblTelfono);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(15, 200, 69, 20);
		contentPane.add(lblEmail);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe de Digitar un nombre");
						txtNombre.requestFocus();
					}
					else if (txtNombre.getText().length() <4) {
						JOptionPane.showMessageDialog(null, "Valor Ingresado es muy corto");
						txtNombre.requestFocus();
					}
					else {
						txtDireccion.setEditable(true);
						txtDireccion.requestFocus();
					}
				}				
			}
		});
		txtNombre.setBounds(99, 89, 486, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtDireccion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe de Digitar un Valor");
						txtDireccion.requestFocus();
					}
					else if (txtDireccion.getText().length() <4) {
						JOptionPane.showMessageDialog(null, "La dirección es muy corta");
						txtDireccion.requestFocus();
					}
					else {
						txtTelefono.setEditable(true);
						txtTelefono.requestFocus();
					}
				}				
			}
		});
		txtDireccion.setBounds(99, 125, 486, 26);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtTelefono.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe de Digitar un Valor");
						txtTelefono.requestFocus();
					}
					else if (txtTelefono.getText().length() <7) {
						JOptionPane.showMessageDialog(null, "El teléfono es Incorrecto, minimo 6 digitos");
						txtTelefono.requestFocus();
					}
					else {
						txtEmail.setEditable(true);
						txtEmail.requestFocus();
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
			     tecla = e.getKeyChar();
			     if (!Character.isDigit(tecla)) {
			    	 e.consume();
			     }
			}
		});
		txtTelefono.setBounds(99, 161, 486, 26);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe de Digitar un cuenta de correo");
						txtEmail.requestFocus();
					}
					else if (!txtEmail.getText().matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
						JOptionPane.showMessageDialog(null, "Error al digitar el correo, verifique");
						txtEmail.requestFocus();						
					}
					else {
						btnRegistrar.setEnabled(true);
					}
				}				
			}
		});
		txtEmail.setBounds(99, 197, 486, 26);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		 
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registro = new Linea();
			   	registro.setNombre(txtNombre.getText());
			   	registro.setDireccion(txtDireccion.getText());
			   	registro.setTelefono(txtTelefono.getText());
			   	registro.setEmail(txtEmail.getText());
			    
			   	ManejoRegistroDAO.adicionar(registro,tabla);
			   	
			   	txtNombre.setText(null);
			   	txtDireccion.setText(null);
			   	txtTelefono.setText(null);
			   	txtEmail.setText(null);
			   	
			   	deshabilitar();
			   	
			}
		});
		btnRegistrar.setBounds(277, 239, 115, 29);
		contentPane.add(btnRegistrar);
		
		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setFillsViewportHeight(true);
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Direccion", "Telefono", "email"
			}
		));
		tabla.setBounds(41, 303, 544, 502);
		contentPane.add(tabla);
		ManejoRegistroDAO.cargar(tabla);
		
		JLabel lblAgenda = new JLabel("Registro");
		lblAgenda.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgenda.setBounds(276, 31, 104, 29);
		contentPane.add(lblAgenda);
		
		deshabilitar();

	}
	private static void deshabilitar() {
		
		txtDireccion.setEditable(false);
		txtTelefono.setEditable(false);
		txtEmail.setEditable(false);
		btnRegistrar.setEnabled(false);
		txtNombre.requestFocus();
	}
}
