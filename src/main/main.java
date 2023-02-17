package main;

import java.awt.EventQueue;


import interfaz.VentanaPrincipal;

public class main {
	

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

}
