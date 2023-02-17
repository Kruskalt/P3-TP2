package interfaz;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Inicio extends JPanel{

	private final int posX	= 10;
	private final int posY 	= 0;
	private final int ancho = 764;
	private final int alto	= 550;
	
	VentanaPrincipal ventana;
	
	private JLabel lblTituloJuego;
	
	private JButton btnCrearRedEspias, 
					btnExtras, 
					btnCerrar;
	
	private final int btnPosX 	= ancho/3;
	private final int btnPosY 	= alto/5;
	private final int btnAncho 	= ancho/3;
	private final int btnAlto 	= alto/6;
	
	public Inicio(VentanaPrincipal ventana) {
		//caracteristicas de this.Inicio
		setBounds(posX, posY, ancho, alto);
		setBorder(new LineBorder(new Color(255, 160, 122), 2));
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		iniciarLblTituloJuego();
		iniciarBtnCrearRedEspias();
		iniciarBtnOpciones();
		iniciarBtnCerrar();
		
		accionBotonExtras();
		
		this.ventana = ventana;
	}
	
	public void mostrarPanel() {
		setVisible(true);
	}
	
	public void ocultarPanel() {
		setVisible(false);
	}

	private void iniciarBtnCerrar() {
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setToolTipText("Cierra el juego");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarJuego();
			}
		});
		btnCerrar.setBackground(new Color(255, 160, 122));
		btnCerrar.setForeground(Color.DARK_GRAY);
		btnCerrar.setFont(new Font("Consolas", Font.BOLD, 20));
		btnCerrar.setBounds(btnPosX, btnPosY*3, btnAncho, btnAlto);
		add(btnCerrar);
	}

	private void iniciarBtnOpciones() {
		btnExtras = new JButton("Extras");
		btnExtras.setToolTipText("StressTest");
		btnExtras.setBackground(new Color(255, 160, 122));
		btnExtras.setForeground(Color.DARK_GRAY);
		btnExtras.setFont(new Font("Consolas", Font.BOLD, 20));
		btnExtras.setBounds(btnPosX, btnPosY*2, btnAncho, btnAlto);
		add(btnExtras);
	}

	private void iniciarBtnCrearRedEspias() {
		btnCrearRedEspias = new JButton("Crear red de espias");
		btnCrearRedEspias.setToolTipText("Crea tu red de espias del recontraespionaje");
		btnCrearRedEspias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultarPanel();
				ventana.mostrarOpciones();
				ventana.mostrarBotonVolver();
			}
		});
		btnCrearRedEspias.setBackground(new Color(255, 160, 122));
		btnCrearRedEspias.setForeground(Color.DARK_GRAY);
		btnCrearRedEspias.setFont(new Font("Consolas", Font.BOLD, 20));
		btnCrearRedEspias.setBounds(btnPosX, btnPosY, btnAncho, btnAlto);
		add(btnCrearRedEspias);
	}

	private void iniciarLblTituloJuego() {
		lblTituloJuego = new JLabel("Temible Operario Del Recontraespionaje");
		lblTituloJuego.setForeground(Color.DARK_GRAY);
		lblTituloJuego.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTituloJuego.setBackground(Color.BLACK);
		lblTituloJuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJuego.setBounds(posX, posY+10, ancho-20, alto/9);
		add(lblTituloJuego);
	}
	
	private void accionBotonExtras() {
		btnExtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.mostrarPanelExtras();
				ocultarPanel();
				ventana.mostrarBotonVolver();
			}
		});
	}
	
	private void cerrarJuego() {
		this.ventana.cerrarJuego();
	}
}
