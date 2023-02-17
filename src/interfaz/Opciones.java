package interfaz;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Opciones extends JPanel{

	private final int posX	= 10;
	private final int posY	= 0;
	private final int ancho = 764;
	private final int alto 	= 550;
	
	private JLabel lblTituloOpciones;
	private JLabel lblCantDeEspias;
	private JTextField tfCantDeEspias;
	
	private final ButtonGroup algoritmos = new ButtonGroup();
	private JLabel lblElegirAlgoritmo;
	private JRadioButton rdbtnPRIM;
	private JRadioButton rdbtnKRUSKAL;
	
	private JButton btnAceptarCambios;
	private JLabel excepciones;
	
	private int cantVertices;
	
	private VentanaPrincipal vp;
	
	public Opciones(VentanaPrincipal ventana) {
		//caracteristicas de this.Opciones
		setBounds(posX, posY, ancho, alto);
		setBorder(new LineBorder(new Color(255, 160, 122), 2));
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		vp = ventana;
		
		iniciarLblTituloOpciones();
		iniciarCantidadEspias();
		iniciarAlgoritmos();
		iniciarBtnAceptarCambios();
		
		ignorarCaracteres();
		accionBotonAceptarCambios();
	}
	
	public void ignorarCaracteres() {
		tfCantDeEspias.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			       e.consume();
			      }
			  }
		});
	}

	public void mostrarPanel() {
		setVisible(true);
	}
	
	public void ocultarPanel() {
		setVisible(false);
	}
	
	private void iniciarLblTituloOpciones() {
		lblTituloOpciones = new JLabel(" Opciones:   ");
		lblTituloOpciones.setFont(new Font("Consolas", Font.BOLD, 40));
		lblTituloOpciones.setBounds(posX, posY+10, 744, 60);
		add(lblTituloOpciones);
	}

	private void iniciarCantidadEspias() {
		iniciarTFCantEspias();
		iniciarLBLCantEspias();
		iniciarExcepciones();
	}

	private void iniciarLBLCantEspias() {
		lblCantDeEspias = new JLabel("Cantidad de espias:");
		lblCantDeEspias.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCantDeEspias.setBounds(30, 145, 200, 30);
		add(lblCantDeEspias);
	}

	private void iniciarTFCantEspias() {
		tfCantDeEspias = new JTextField();
		tfCantDeEspias.setToolTipText("Cantidad de espias que contendra su red");
		tfCantDeEspias.setFont(new Font("Consolas", Font.PLAIN, 15));
		tfCantDeEspias.setBackground(new Color(245, 222, 179));
		tfCantDeEspias.setHorizontalAlignment(SwingConstants.CENTER);
		tfCantDeEspias.setBounds(200, 145, 100, 30);
		tfCantDeEspias.setColumns(3);
		add(tfCantDeEspias);
	}
	
	private void iniciarExcepciones() {
		excepciones = new JLabel();
		excepciones.setBounds(10, 185, 744, 30);
		excepciones.setFont(new Font("Consolas", Font.PLAIN, 15));
		excepciones.setBackground(new Color(245, 222, 179));
		excepciones.setHorizontalAlignment(SwingConstants.CENTER);
		add(excepciones);
	}

	private void iniciarAlgoritmos() {
		iniciarLBLAlgoritmos();
		iniciarBtnKruskal();
		iniciarBtnPrim();
	}

	private void iniciarLBLAlgoritmos() {
		lblElegirAlgoritmo = new JLabel("Elegir algoritmo:");
		lblElegirAlgoritmo.setFont(new Font("Consolas", Font.BOLD, 15));
		lblElegirAlgoritmo.setBounds(30, 250, 200, 30);
		add(lblElegirAlgoritmo);
	}

	private void iniciarBtnKruskal() {
		rdbtnKRUSKAL = new JRadioButton("       Kruskal");
		rdbtnKRUSKAL.setToolTipText("Kruskal generara un camino minimo entre los espias de su red.\r\n Tamben es el algoritmo mas rapido!");
		algoritmos.add(rdbtnKRUSKAL);
		rdbtnKRUSKAL.setBackground(new Color(255, 160, 122));
		rdbtnKRUSKAL.setFont(new Font("Consolas", Font.BOLD, 18));
		rdbtnKRUSKAL.setBounds(150, 335, 250, 30);
		add(rdbtnKRUSKAL);
	}

	private void iniciarBtnPrim() {
		rdbtnPRIM = new JRadioButton("        Prim");
		rdbtnPRIM.setToolTipText("Prim generara un camino minimo entre los espias de su red.");
		algoritmos.add(rdbtnPRIM);
		rdbtnPRIM.setBackground(new Color(255, 160, 122));
		rdbtnPRIM.setFont(new Font("Consolas", Font.BOLD, 18));
		rdbtnPRIM.setBounds(150, 290, 250, 30);
		add(rdbtnPRIM);
	}
	
	private void iniciarBtnAceptarCambios() {
		btnAceptarCambios = new JButton("Aceptar Cambios");
		btnAceptarCambios.setBackground(new Color(255, 160, 122));
		btnAceptarCambios.setForeground(Color.DARK_GRAY);
		btnAceptarCambios.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		btnAceptarCambios.setBounds(554, 509, 200, 30);
		add(btnAceptarCambios);
	}

	private void accionBotonAceptarCambios() {
		btnAceptarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cantVertices = Integer.parseInt(tfCantDeEspias.getText());
					vp.crearRed(cantVertices);
					if (rdbtnKRUSKAL.isSelected()) {
						vp.seleccionarKruskal();
						ocultarPanel();
						vp.mostrarRedEspias();
						tfCantDeEspias.setText("");
					}
					else if (rdbtnPRIM.isSelected()) {
						vp.seleccionarPrim();
						ocultarPanel();
						vp.mostrarRedEspias();
						tfCantDeEspias.setText("");
					}
					else {
						excepciones.setText("Tenes que elegir un algoritmo para representar la red de espias");
						excepciones.setVisible(true);
					}
				}
				catch (Exception ex) {
					if (ex.getClass().equals(NumberFormatException.class)) {
						excepciones.setText("Tenes que ingresar una cantidad de espias!");
					}
					else {
						excepciones.setText(ex.getMessage());
						excepciones.setVisible(true);
					}
				}
				algoritmos.clearSelection();
			}
		});
	}
}
