package projekt;

import javax.swing.JOptionPane;

public class BladIndeks extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BladIndeks() {
		JOptionPane.showMessageDialog(null, "Indeks to liczba całkowita z zakresu od 1 do ilości elementów w liście", "Błąd danych", JOptionPane.WARNING_MESSAGE);
	}
}
