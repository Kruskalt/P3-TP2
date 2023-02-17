package interfaz;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MostrarRed extends JPanel{
	
	private final int posX	= 10;
	private final int posY 	= 0;
	private final int ancho = 764;
	private final int alto	= 550;
	
	private VentanaPrincipal VP;
	private JButton btnEnviarMensaje;
	private JLabel lblEspiasRedMinima;
	private JLabel lblEspiasBase;
	private JScrollPane spRedMinima;
	private JScrollPane spRedBase;
	private JTextPane txpRedBase;
	private JTextPane txpRedMinima;
	
	public MostrarRed(VentanaPrincipal VP) {
		setBounds(posX, posY, ancho, alto);
		setBorder(new LineBorder(new Color(255, 160, 122), 2));
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		this.VP = VP;
		
		txpRedMinima = new JTextPane();
		txpRedMinima.setEditable(false);
		txpRedMinima.setFont(new Font("Consolas", Font.BOLD, 12));
		txpRedMinima.setBackground(new Color(255, 218, 185));
		txpRedMinima.setBounds(425, 112, 299, 330);
		add(txpRedMinima);
		
		txpRedBase = new JTextPane();
		txpRedBase.setBackground(new Color(255, 218, 185));
		txpRedBase.setFont(new Font("Consolas", Font.BOLD, 12));
		txpRedBase.setEditable(false);
		txpRedBase.setBounds(42, 112, 299, 330);
		add(txpRedBase);
		
		spRedBase = new JScrollPane(txpRedBase);
		spRedBase.setBounds(40, 110, 300, 330);
		add(spRedBase);
		
		spRedMinima = new JScrollPane(txpRedMinima);
		spRedMinima.setBounds(440, 110, 300, 330);
		add(spRedMinima);
		
		lblEspiasBase = new JLabel("Red de espias principal:");
		lblEspiasBase.setForeground(new Color(0, 0, 0));
		lblEspiasBase.setBackground(new Color(233, 150, 122));
		lblEspiasBase.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspiasBase.setFont(new Font("Consolas", Font.BOLD, 13));
		lblEspiasBase.setBounds(40, 58, 300, 33);
		add(lblEspiasBase);
		
		lblEspiasRedMinima = new JLabel("Sucesi\u00F3n del mensaje con menor riesgo:");
		lblEspiasRedMinima.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspiasRedMinima.setBackground(new Color(233, 150, 122));
		lblEspiasRedMinima.setFont(new Font("Consolas", Font.BOLD, 13));
		lblEspiasRedMinima.setBounds(440, 58, 300, 33);
		add(lblEspiasRedMinima);
		
		btnEnviarMensaje = new JButton("Enviar mensaje!");
		btnEnviarMensaje.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		btnEnviarMensaje.setBackground(new Color(233, 150, 122));
		btnEnviarMensaje.setToolTipText("Clickee aqui para enviar el mensaje a sus espias!");
		btnEnviarMensaje.setBounds(295, 451, 200, 30);
		add(btnEnviarMensaje);
		
		btnEnviarMensajeAccion();
	}
	
	private void btnEnviarMensajeAccion() {
		btnEnviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VP.generarRedMinima();
				txpRedBase.setText(VP.redBaseToString());
				txpRedMinima.setText(VP.redMinimaToString());
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
