package projekt;

import javax.swing.JOptionPane;

public class BladCena extends Exception {
	private static final long serialVersionUID = 1L;

	public BladCena() {
		JOptionPane.showMessageDialog(null, "Wprowadź cenę w formacie Zł.GR", "Błąd formatu", JOptionPane.WARNING_MESSAGE);
	}
}
