package projekt;

import javax.swing.JOptionPane;

public class PustaLista extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PustaLista() {
		JOptionPane.showMessageDialog(null, "Brak elementów w liście", "Pusta lista", JOptionPane.WARNING_MESSAGE);
	}

}
