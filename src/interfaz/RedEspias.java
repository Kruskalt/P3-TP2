package interfaz;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RedEspias extends JPanel{

	private final int posX = 10;
	private final int posY = 0;
	private final int ancho = 764;
	private final int alto = 550;
	
	//agregar informacion de los espias
	private JLabel 		lblCaracteristicas;
	private JLabel 		lblNombre,
						lblID,
						lblRiesgo;
	private JTextField	tfNombre,
						tfID,
						tfRiesgo;
	private JButton 	btnAgregarEspia;
	private JTextPane 	textoFaltan,
						textoTodosUnidos;
	
	
	//agregar conexiones entre espias
	private JLabel 		lblConexionEntreDosEspias;
	private JLabel 		lblEspiaOrigen,
						lblEspiaDestino;
	private JTextField 	tfEspiaOrigen,
						tfEspiaDestino;
	private JButton 	btnAgregarConexion;
	
	
	//boton enviar mensaje (mostrar grafo)
	private JButton 	btnGenerarRed;
	
	//boton ayuda 
	private JTextPane 	textoAyuda;
	private JButton 	tbtnAyuda;
	
	private VentanaPrincipal vp;
	private JLabel lblFaltaInformacion;
	private JLabel lblExepcionesNombres;
	private JLabel lblExepcionesConexiones;
	
	public RedEspias(VentanaPrincipal vp) {
		//inicializar este panel:
		setBounds(posX, posY, ancho, alto);
		setBorder(new LineBorder(new Color(255, 160, 122), 2, true));
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		//inicializar los distintos elementos de este panel:
		paqueteCaracteristicasEspias();
		paqueteConexionesEspias();
		paqueteAyuda();
		iniciarBTNEnviarMensaje();
		
		this.vp = vp;
		
		ignorarCaracteres(tfEspiaDestino);
		ignorarCaracteres(tfEspiaOrigen);
		ignorarCaracteres(tfID);
		{
			lblExepcionesNombres = new JLabel("");
			lblExepcionesNombres.setHorizontalAlignment(SwingConstants.CENTER);
			lblExepcionesNombres.setBounds(50, 212, 330, 40);
			add(lblExepcionesNombres);
		}
		{
			lblExepcionesConexiones = new JLabel("");
			lblExepcionesConexiones.setHorizontalAlignment(SwingConstants.CENTER);
			lblExepcionesConexiones.setBounds(50, 444, 445, 40);
			add(lblExepcionesConexiones);
		}
		
		
		
		
		
		ignorarCaracteresParaRiesgo();
		
		agregarNombreAlEspia();
		agregarCaminoEntreEspias();
		btnEnviarMensaje();
		
		
	}

	private void paqueteCaracteristicasEspias() {
		iniciarLBLCaracteristicas();
		iniciarLBLEspias();
		iniciarTFEspias();
		iniciarBTNAgregarEspia();
		iniciarTPFaltan();
	}

	private void paqueteConexionesEspias() {
		iniciarLBLConexionEntreDosEspias();
		iniciarEspiaOrigen();
		iniciarEspiaDestino();
		iniciarRiesgoConexion();
		iniciarBTNAgregarConexion();
	}

	private void paqueteAyuda() {
		iniciarTBAyuda();
		iniciarTPAyuda();
		iniciarTXFaltanConexiones();
	}

	private void iniciarLBLEspias() {
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Consolas", Font.ITALIC, 15));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 100, 100, 30);
		add(lblNombre);
		
		lblID = new JLabel ("ID:");
		lblID.setFont(new Font("Consolas", Font.ITALIC, 15));
		lblID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblID.setBounds(10, 130, 100, 30);
		add(lblID);
	}
	
	private void iniciarLBLFaltaInformacion() {
		
		lblFaltaInformacion = new JLabel("Todavia falta informacion!");
		lblFaltaInformacion.setFont(new Font("Consolas", Font.BOLD, 12));
		lblFaltaInformacion.setBounds(554, 484, 200, 14);
		add(lblFaltaInformacion);
		
		lblFaltaInformacion.setVisible(false);
	}
	
	private void iniciarTXFaltanConexiones() {
		textoTodosUnidos = new JTextPane();
		textoTodosUnidos.setToolTipText("Continue a\u00F1adiendo conexiones entre espias!");
		textoTodosUnidos.setEditable(false);
		textoTodosUnidos.setFocusable(false);
		textoTodosUnidos.setFont(new Font("Consolas", Font.BOLD, 12));
		textoTodosUnidos.setText(" Los espias \r\n  no estan \r\n   unidos!\r\n\r\n");
		textoTodosUnidos.setBackground(new Color(222, 184, 135));
		textoTodosUnidos.setBounds(280, 306, 100, 88);
		add(textoTodosUnidos);
	}

	private void iniciarTFEspias() {
		tfNombre = new JTextField();
		tfNombre.setBackground(new Color(245, 222, 179));
		tfNombre.setHorizontalAlignment(SwingConstants.CENTER);
		tfNombre.setFont(new Font("Consolas", Font.ITALIC, 12));
		tfNombre.setBounds(120, 100, 150, 30);
		add(tfNombre);
		
		tfID = new JTextField();
		tfID.setBackground(new Color(245, 222, 179));
		tfID.setHorizontalAlignment(SwingConstants.CENTER);
		tfID.setFont(new Font("Consolas", Font.ITALIC, 12));
		tfID.setBounds(120, 130, 150, 30);
		add(tfID);
	}
	
	private void iniciarBTNAgregarEspia() {
		btnAgregarEspia = new JButton("Agregar espia");
		btnAgregarEspia.setToolTipText("A\u00F1ada al espia a su red con su nombre!");
		btnAgregarEspia.setBackground(new Color(233, 150, 122));
		btnAgregarEspia.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		btnAgregarEspia.setBounds(50,171,200,30);
		btnAgregarEspia.requestFocus();
		add(btnAgregarEspia);
	}

	private void iniciarBTNEnviarMensaje() {
		btnGenerarRed = new JButton("Generar Red");
		btnGenerarRed.setToolTipText("Genere la red de espias");
		btnGenerarRed.setBackground(new Color(233, 150, 122));
		btnGenerarRed.setFont(new Font("Consolas", Font.BOLD, 15));
		btnGenerarRed.setBounds(554,509,200,30);
		add(btnGenerarRed);
		
		iniciarLBLFaltaInformacion();
	}

	private void iniciarTPAyuda() {
		textoAyuda = new JTextPane();
		textoAyuda.setFont(new Font("Consolas", Font.ITALIC, 11));
		textoAyuda.setText("Recomendaciones:\r\n\r\n  - Describir todos los espias antes de proponer\r\n      quien se comunica con quien.\r\n  - Los nombres pueden repetirse.\r\n  - Los ID de los espias son positivos \r\n      y menores a el tama\u00F1o de tu red!\r\n      (Ej: 00004)\r\n  - El riego sea entre 0.0 y 1.0, contando\r\n      decimales. \r\n      (Ej: 0.3)\r\n\r\n\r\n\r\n\r\n\r\n  - Pueden haber espias sin conocerse.\r\n  - No puedes agregar dos veces el mismo espia!\r\n  - No puedes agregar un espia que no hayas\r\n       registrado previamente!\r\n\r\n\r\n\r\n\r\n\r\n\r\n  -Cuando estes listo, puedes enviar el mensaje.");
		textoAyuda.setEditable(false);
		textoAyuda.setBackground(new Color(222, 184, 135));
		textoAyuda.setBounds(454, 52, 300, 390);
		textoAyuda.setVisible(false);
		add(textoAyuda);
	}

	private void iniciarTBAyuda() {
		tbtnAyuda = new JButton("Ayuda!");
		tbtnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textoAyuda.isVisible())
					textoAyuda.setVisible(false);
				else
					textoAyuda.setVisible(true);
			}
		});
		tbtnAyuda.setBackground(new Color(233, 150, 122));
		tbtnAyuda.setFont(new Font("Consolas", Font.BOLD, 15));
		tbtnAyuda.setBounds(554, 11, 200, 30);
		add(tbtnAyuda);
	}

	private void iniciarEspiaOrigen() {
		lblEspiaOrigen = new JLabel("ID espia 1:");
		lblEspiaOrigen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspiaOrigen.setFont(new Font("Consolas", Font.ITALIC, 15));
		lblEspiaOrigen.setBounds(10, 304, 100, 30);
		add(lblEspiaOrigen);
		
		tfEspiaOrigen = new JTextField();
		tfEspiaOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		tfEspiaOrigen.setFont(new Font("Consolas", Font.ITALIC, 12));
		tfEspiaOrigen.setBackground(new Color(245, 222, 179));
		tfEspiaOrigen.setBounds(120, 304, 150, 30);
		add(tfEspiaOrigen);
	}

	private void iniciarRiesgoConexion() {
		tfRiesgo = new JTextField();
		tfRiesgo.setFont(new Font("Consolas", Font.ITALIC, 12));
		tfRiesgo.setBackground(new Color(245, 222, 179));
		tfRiesgo.setHorizontalAlignment(SwingConstants.CENTER);
		tfRiesgo.setBounds(120, 364, 150, 30);
		add(tfRiesgo);
		
		lblRiesgo = new JLabel("Riesgo:");
		lblRiesgo.setFont(new Font("Consolas", Font.ITALIC, 15));
		lblRiesgo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRiesgo.setBounds(10, 362, 100, 30);
		add(lblRiesgo);
	}

	private void iniciarEspiaDestino() {
		lblEspiaDestino = new JLabel("ID espia 2:");
		lblEspiaDestino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspiaDestino.setFont(new Font("Consolas", Font.ITALIC, 15));
		lblEspiaDestino.setBounds(10, 334, 100, 30);
		add(lblEspiaDestino);
		
		tfEspiaDestino = new JTextField();
		tfEspiaDestino.setHorizontalAlignment(SwingConstants.CENTER);
		tfEspiaDestino.setFont(new Font("Consolas", Font.ITALIC, 12));
		tfEspiaDestino.setBackground(new Color(245, 222, 179));
		tfEspiaDestino.setBounds(120, 334, 150, 30);
		add(tfEspiaDestino);
	}

	private void iniciarBTNAgregarConexion() {
		btnAgregarConexion = new JButton("Agregar conexion\r\n");
		btnAgregarConexion.setToolTipText("Agregue una conexion entre dos espias para enviar el mensaje!");
		
		btnAgregarConexion.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		btnAgregarConexion.setBackground(new Color(233, 150, 122));
		btnAgregarConexion.setBounds(50, 403, 200, 30);
		add(btnAgregarConexion);
	}

	private void iniciarLBLConexionEntreDosEspias() {
		lblConexionEntreDosEspias = new JLabel("Conexion entre dos espias:\r\n");
		lblConexionEntreDosEspias.setHorizontalAlignment(SwingConstants.CENTER);
		lblConexionEntreDosEspias.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 18));
		lblConexionEntreDosEspias.setBounds(10, 263, 300, 30);
		add(lblConexionEntreDosEspias);
	}

	private void iniciarTPFaltan() {
		textoFaltan = new JTextPane();
		textoFaltan.setToolTipText("Continue a\u00F1adiendo nombre a sus espias!");
		textoFaltan.setText("   Todavia hay\r\n    espias sin\r\n     nombre!");
		textoFaltan.setFont(new Font("Consolas", Font.BOLD, 12));
		textoFaltan.setEditable(false);
		textoFaltan.setBackground(new Color(222, 184, 135));
		textoFaltan.setBounds(280, 100, 150, 88);
		add(textoFaltan);
	}

	private void iniciarLBLCaracteristicas() {
		lblCaracteristicas = new JLabel("Caracteristicas del espia:");
		lblCaracteristicas.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 18));
		lblCaracteristicas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaracteristicas.setBounds(10, 70, 300, 30);
		add(lblCaracteristicas);
	}
	
	private void agregarNombreAlEspia() {
		btnAgregarEspia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = tfNombre.getText();
					int idEspia = Integer.parseInt(tfID.getText());
					vp.agregarNombreAlEspia(idEspia, nombre);
					if (vp.todosTienenNombre()) {
						textoFaltan.setVisible(false);
					}
					tfNombre.setText("");
					tfID.setText("");
					
				}
				catch (Exception ex) {
					if (ex.getClass().equals(NumberFormatException.class)) {
						lblExepcionesNombres.setText("Tenes que ingresar una cantidad de espias!");
					}
					else {
						lblExepcionesNombres.setText(ex.getMessage());
					}
				}
				
			}
		});
	}
	
	private void agregarCaminoEntreEspias() {
		btnAgregarConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idOrigen = Integer.parseInt(tfEspiaOrigen.getText());
					int idDestino = Integer.parseInt(tfEspiaDestino.getText());
					double riesgo = Double.parseDouble(tfRiesgo.getText());
					
					vp.agregarCamino(idOrigen, idDestino, riesgo);
					
					tfEspiaDestino.setText("");
					tfEspiaOrigen.setText("");
					tfRiesgo.setText("");
					
					if (vp.esConexo()) {
						textoTodosUnidos.setVisible(false);
					}
				}
				catch (Exception ex) {
					if (ex.getClass().equals(NumberFormatException.class)) {
						lblExepcionesConexiones.setText("Parece que te olvidaste de algun dato");
					}
					else {
						lblExepcionesConexiones.setText(ex.getMessage());
					}
				}
			}
		});
	}
	
	private void ignorarCaracteres(JTextField texto) {
		texto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			       e.consume();
			      }
			  }
		});
	}
	
	private void ignorarCaracteresParaRiesgo() {
		tfRiesgo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
			       e.consume();
			      }
			  }
		});
	}
	
	private void btnEnviarMensaje() {
		btnGenerarRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textoFaltan.isVisible() || textoTodosUnidos.isVisible()) {
					lblFaltaInformacion.setVisible(true);
				}
				else {
					ocultarPanel();
					vp.mostrarRed();
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
}
