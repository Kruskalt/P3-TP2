package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import juego.TemibleOperarioDelRecontraespionaje;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class VentanaPrincipal {

	private final int POSX	= 275;
	private final int POSY	= 50;
	private final int ANCHO = 800;
	private final int ALTO 	= 600;
	
	public JFrame ventana;
	
	private Inicio inicio;
	private Opciones opciones;
	private RedEspias redEspias;
	private MostrarRed mostrarRed;
	private Extras extrasStressTest;
	
	private TemibleOperarioDelRecontraespionaje juego;
	private JButton btnVolver;
	

	// lanzar la aplicacion
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal vp = new VentanaPrincipal();
					vp.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// crear la aplicacion
	public VentanaPrincipal() {
		inicializarVentanaPrincipal();
		inicializarBotonVolver();
		iniciarPaneles();
		
	}

	private void inicializarBotonVolver() {
		btnVolver = new JButton("Volver a inicio");
		btnVolver.setBounds(20, 509, 200, 30);
		ventana.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			}
		});
		btnVolver.setForeground(Color.DARK_GRAY);
		btnVolver.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		btnVolver.setBackground(new Color(255, 160, 122));
		ocultarBotonVolver();
	}

	private void iniciarPaneles() {
		inicio = new Inicio(this);
		ventana.getContentPane().add(inicio);
		//ocultarPanelInicio();
		
		opciones = new Opciones(this);
		ventana.getContentPane().add(opciones);
		ocultarPanelOpciones();
		
		redEspias = new RedEspias(this);
		ventana.getContentPane().add(redEspias);
		ocultarPanelRedEspias();
		
		mostrarRed = new MostrarRed(this);
		ventana.getContentPane().add(mostrarRed);
		ocultarPanelMostrarRed();
		
		extrasStressTest = new Extras();
		ventana.getContentPane().add(extrasStressTest);
		ocultarPanelExtras();
	}

	// inicializar la ventana principal
	private void inicializarVentanaPrincipal() {
		ventana = new JFrame();
		ventana.setTitle("Red de Espias");
		ventana.setBackground(Color.DARK_GRAY);
		ventana.getContentPane().setBackground(Color.DARK_GRAY);
		ventana.setResizable(false);
		ventana.setBounds(POSX, POSY, ANCHO, ALTO);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
	}
	
	public void cerrarJuego() {
		ventana.setVisible(false);;
	}
	
	private void ocultarPanelMostrarRed() {
		mostrarRed.ocultarPanel();
	}
	
	public void ocultarPanelRedEspias() {
		redEspias.ocultarPanel();
	}
	
	public void ocultarPanelOpciones() {
		opciones.ocultarPanel();
	}
	
	public void mostrarRed() {
		mostrarRed.mostrarPanel();
	}

	private void ocultarPanelExtras() {
		extrasStressTest.ocultarPanel();
	}

	public void mostrarPanelExtras() {
		extrasStressTest.mostrarPanel();
	}

	public void mostrarOpciones() {
		opciones.mostrarPanel();
	}
	
	public void mostrarBotonVolver() {
		btnVolver.setVisible(true);
	}
	
	public void mostrarInicio() {
		inicio.mostrarPanel();
	}
	
	public void ocultarBotonVolver() {
		btnVolver.setVisible(false);
	}
	
	public void mostrarRedEspias() {
		redEspias.mostrarPanel();
	}
	
	public void ocultarTodo() {
		ocultarPanelOpciones();
		ocultarPanelRedEspias();
		ocultarBotonVolver();
		ocultarPanelExtras();
	}
	
	public void reiniciar() {
		ventana.remove(inicio);
		ventana.remove(opciones);
		ventana.remove(redEspias);
		ventana.remove(mostrarRed);
		ventana.remove(extrasStressTest);
		iniciarPaneles();
		ocultarBotonVolver();
	}
	
	public void crearRed(int cantEspias) {
		juego = new TemibleOperarioDelRecontraespionaje(cantEspias);
	}
	
	public void seleccionarKruskal() {
		juego.prepararKruskal();
	}
	
	public void seleccionarPrim() {
		juego.prepararPrim();
	}
	
	public void agregarNombreAlEspia(int id, String nombre) {
		juego.agregarNombreAlEspia(id, nombre);
	}
	
	public boolean todosTienenNombre() {
		return juego.tienenTodosNombre();
	}
	
	public boolean esConexo() {
		return juego.esConexo();
	}
	
	public void agregarCamino(int origen, int destino, double riesgo) {
		juego.agregarDistanciasEntreEspias(origen, destino, riesgo);
	}
	
	public boolean seCreoJuego() {
		return juego != null;
	}
	
	public int tamanioDeRed() {
		return juego.tamanioDeRed();
	}
	
	public String redBaseToString() {
		return juego.redBaseToString();
	}
	
	public void generarRedMinima() {
		juego.generarArbolMinimo();
	}
	
	
	public String redMinimaToString() {
		return juego.arbolMinimoToString();
	}
}
