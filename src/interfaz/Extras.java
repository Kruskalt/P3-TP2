package interfaz;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import primVSkruskal.StressTest;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Extras extends JPanel {

	private final int posX	= 10;
	private final int posY 	= 0;
	private final int ancho = 764;
	private final int alto	= 550;
	
	private JTextField txCantVertices;
	private JTextField tpResultadoKruskalUF;
	private JButton btnStressTest;
	private JLabel lblExcepciones;
	private JTextField tpResultadoKruskalBFS;
	private JTextField tpResultadoPrim;
	
	public Extras() {
		setBounds(posX, posY, ancho, alto);
		setBorder(new LineBorder(new Color(255, 160, 122), 2));
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		iniciarBotonStressTest();
		
		iniciarCantVertices();
		
		iniciarResultado();
		
		ignorarCaracteres();
		
		accionBotonStressTest();
	}
	
	
	private void iniciarResultado() {
		tpResultadoKruskalUF = new JTextField();
		tpResultadoKruskalUF.setHorizontalAlignment(SwingConstants.CENTER);
		tpResultadoKruskalUF.setFont(new Font("Consolas", Font.BOLD, 12));
		tpResultadoKruskalUF.setEditable(false);
		tpResultadoKruskalUF.setBackground(new Color(255, 218, 185));
		tpResultadoKruskalUF.setBounds(180, 100, 400, 70);
		add(tpResultadoKruskalUF);
		
		JLabel lblTitulo = new JLabel("Compare la velocidad de distintos algoritmos!");
		lblTitulo.setFont(new Font("Consolas", Font.BOLD, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 59, 744, 30);
		add(lblTitulo);
		{
			lblExcepciones = new JLabel("Con valores mayores a 50 puede tildar un ratito la aplicaci\u00F3n...");
			lblExcepciones.setFont(new Font("Consolas", Font.BOLD, 13));
			lblExcepciones.setHorizontalAlignment(SwingConstants.CENTER);
			lblExcepciones.setBounds(10, 341, 744, 30);
			add(lblExcepciones);
		}
		{
			tpResultadoKruskalBFS = new JTextField();
			tpResultadoKruskalBFS.setHorizontalAlignment(SwingConstants.CENTER);
			tpResultadoKruskalBFS.setFont(new Font("Consolas", Font.BOLD, 12));
			tpResultadoKruskalBFS.setEditable(false);
			tpResultadoKruskalBFS.setBackground(new Color(255, 218, 185));
			tpResultadoKruskalBFS.setBounds(180, 180, 400, 70);
			add(tpResultadoKruskalBFS);
		}
		{
			tpResultadoPrim = new JTextField();
			tpResultadoPrim.setHorizontalAlignment(SwingConstants.CENTER);
			tpResultadoPrim.setFont(new Font("Consolas", Font.BOLD, 12));
			tpResultadoPrim.setEditable(false);
			tpResultadoPrim.setBackground(new Color(255, 218, 185));
			tpResultadoPrim.setBounds(180, 260, 400, 70);
			add(tpResultadoPrim);
		}
	}
	
	private void iniciarCantVertices() {
		txCantVertices = new JTextField();
		txCantVertices.setBackground(new Color(255, 218, 185));
		txCantVertices.setFont(new Font("Consolas", Font.ITALIC, 12));
		txCantVertices.setToolTipText("Indique la cantidad de vertices");
		txCantVertices.setHorizontalAlignment(SwingConstants.CENTER);
		txCantVertices.setBounds(434, 402, 100, 30);
		add(txCantVertices);
		txCantVertices.setColumns(10);
	}
	
	private void iniciarBotonStressTest() {
		btnStressTest = new JButton("Iniciar StressTest");
		btnStressTest.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		btnStressTest.setBackground(new Color(233, 150, 122));
		btnStressTest.setToolTipText("Tener en cuenta que este proceso puede llevar bastante tiempo dependiendo de tu dispositivo!  ");
		btnStressTest.setBounds(215, 400, 200, 30);
		add(btnStressTest);
	}
	
	private void accionBotonStressTest() {
		btnStressTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cantVertices = Integer.parseInt(txCantVertices.getText());
					lblExcepciones.setText("Completado!");
					tpResultadoKruskalUF.setText("");
					tpResultadoKruskalBFS.setText("");
					tpResultadoPrim.setText("");
					
					tpResultadoKruskalUF.setText(StressTest.stressTestKruskalUF(cantVertices));
					tpResultadoKruskalBFS.setText(StressTest.stressTestKruskalBFS(cantVertices));
					tpResultadoPrim.setText(StressTest.stressTestPrim(cantVertices));
				}
				catch(Exception ex) {
					if (ex.getClass().equals(NumberFormatException.class)) {
						lblExcepciones.setText("Tenes que ingresar una cantidad de espias!");
					}
				}
			}
		});
	}
	
	private void ignorarCaracteres() {
		txCantVertices.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
			    if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
			       e.consume();
			      }
			  }
		});
	}
	
	public void ocultarPanel() {
		setVisible(false);
	}
	
	public void mostrarPanel() {
		setVisible(true);
	}
}
