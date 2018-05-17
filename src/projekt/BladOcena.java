package projekt;

import javax.swing.JOptionPane;

public class BladOcena extends Exception {
	private static final long serialVersionUID = 1L;

	public BladOcena() {
		JOptionPane.showMessageDialog(null, "Ocena to liczba całkowita z zakresu 1-6", "Błąd formatu", JOptionPane.WARNING_MESSAGE);
	}
}
