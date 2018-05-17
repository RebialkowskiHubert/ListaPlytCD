package projekt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InterfejsGraf extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * Ustala wysokosc i szerokosc okna aplikacji.
	 */
	int width=700, height=530;
	/**
	 * Ustala rozmiar czcionki.
	 */
	int	rcz=12;
	/**
	 * Ustalaja czy czcionka ma byc pogrubiona lub pochylona.
	 */
	boolean bb=false, bi=false;
	/**
	 * Zmienne tekstowe przekazujace dane wpisane przez uzytkownika
	 */
	String snazwa, scena, sgatunek, skod, sindex, txtstatus;
	String srcz=String.valueOf(rcz);
	Date data;
	
	Akcja ak;
	Suwak suw;
	Pomoc pm;
	Oprog op;
	Logika log=new Logika();
	
	static DefaultTableModel dm = new DefaultTableModel();
	
	JToolTip llp, ltytul, lcena, ldata, lrozmiar, lgatunek, lkod;
	JTabbedPane jtp;
	JSeparator sep, sep2, sep3; 
	JSlider slider;
	Color czcionka, tlo;
	JFileChooser jfc=new JFileChooser();
	FileNameExtensionFilter fnef=new FileNameExtensionFilter("Dokument XML", "xml");
	JMenuBar menu;
	JMenu plik, sortuj, pomoc;
	JMenuItem motworzxml, motworzbaza, mzapiszxml, mzapiszbaza, mwyjscie, mlp, mtytul, mcena, mdata, moprog, mpomoc;
	JToolBar jtb;
	JPanel panel, panel2;
	JScrollPane jsp;
	JTable tab;
	JTextField tlp, ttytul, tcena, tgatunek, tkod, przyklad;
	JSpinner tdata;
	SpinnerDateModel sdm;
	SimpleDateFormat sdf;
	JLabel status, lrcz;
	JButton dod, us, mod;
	JButton kc, kt;
	JCheckBox b, i;
	Font f;
	
	public InterfejsGraf() {
		setTitle("Płyty CD");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(null);
		
		ak=new Akcja();
		suw=new Suwak();
		
		jtp=new JTabbedPane(JTabbedPane.TOP);
		jtp.setBounds(0, 25, 695, 475);
		add(jtp);
		
		panel=new JPanel();
		panel.setBounds(0, 25, 540, 530);
		ImageIcon imgcd=new ImageIcon("cd.jpg");
		jtp.addTab("Lista płyt CD", imgcd, panel);
		panel.setLayout(null);
		
		panel2=new JPanel();
		ImageIcon imgust=new ImageIcon("ustawienia.png");
		jtp.addTab("Ustawienia", imgust, panel2);
		panel2.setLayout(null);
		
		ltytul=new JToolTip();
		ltytul.setBounds(525, 10, 110, 15);
		ltytul.setTipText("Nazwa albumu:");
		panel.add(ltytul);
		
		lcena=new JToolTip();
		lcena.setBounds(525, 60, 45, 15);
		lcena.setTipText("Cena:");
		panel.add(lcena);
		
		ldata=new JToolTip();
		ldata.setBounds(525, 110, 110, 15);
		ldata.setTipText("Data wydania:");
		panel.add(ldata);
		
		lgatunek=new JToolTip();
		lgatunek.setTipText("Gatunek:");
		lgatunek.setBounds(525, 160, 70, 15);
		panel.add(lgatunek);
		
		lkod=new JToolTip();
		lkod.setTipText("Ocena:");
		lkod.setBounds(525, 210, 48, 15);
		panel.add(lkod);
		
		lrozmiar=new JToolTip();
		lrozmiar.setTipText("Rozmiar czcionki");
		lrozmiar.setBounds(20, 95, 110, 20);
		panel2.add(lrozmiar);
		
		sep=new JSeparator();
		sep.setBounds(0, 50, 515, 15);
		panel.add(sep);
		
		sep2=new JSeparator();
		sep2.setOrientation(SwingConstants.VERTICAL);
		sep2.setBounds(515, 0, 15, 425);
		panel.add(sep2);
		
		sep3=new JSeparator();
		sep3.setBounds(0, 425, 695, 15);
		panel.add(sep3);
		
		slider=new JSlider(0, 100, 12);
		slider.setBounds(10, 120, 320, 50);
		slider.setToolTipText("Zmienia rozmiar czcionki");
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(suw);
		panel2.add(slider);
		
		menu=new JMenuBar();
		menu.setBounds(0, 0, 700, 25);
		add(menu);
		
		plik=new JMenu("Plik");
		menu.add(plik);
		
		motworzbaza=new JMenuItem("Wczytaj z bazy");
		motworzbaza.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
		motworzbaza.addActionListener(ak);
		plik.add(motworzbaza);
		
		mzapiszbaza=new JMenuItem("Zapisz do bazy danych");
		mzapiszbaza.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
		mzapiszbaza.addActionListener(ak);
		plik.add(mzapiszbaza);
		
		motworzxml=new JMenuItem("Otwórz");
		motworzxml.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		motworzxml.addActionListener(ak);
		plik.add(motworzxml);
		
		mzapiszxml=new JMenuItem("Zapisz jako XML");
		mzapiszxml.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		mzapiszxml.addActionListener(ak);
		plik.add(mzapiszxml);
		
		plik.addSeparator();
		
		mwyjscie=new JMenuItem("Wyjście");
		mwyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		mwyjscie.addActionListener(ak);
		plik.add(mwyjscie);
		
		sortuj=new JMenu("Sortuj");
		menu.add(sortuj);
		
		mlp=new JMenuItem("Lp");
		mlp.setAccelerator(KeyStroke.getKeyStroke("alt L"));
		mlp.addActionListener(ak);
		sortuj.add(mlp);
		
		mtytul=new JMenuItem("Nazwa");
		mtytul.setAccelerator(KeyStroke.getKeyStroke("alt N"));
		mtytul.addActionListener(ak);
		sortuj.add(mtytul);
		
		mcena=new JMenuItem("Cena");
		mcena.setAccelerator(KeyStroke.getKeyStroke("alt C"));
		mcena.addActionListener(ak);
		sortuj.add(mcena);
		
		mdata=new JMenuItem("Data");
		mdata.setAccelerator(KeyStroke.getKeyStroke("alt D"));
		mdata.addActionListener(ak);
		sortuj.add(mdata);
		
		pomoc=new JMenu("Pomoc");
		menu.add(pomoc);
		
		mpomoc=new JMenuItem("Pomoc");
		mpomoc.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mpomoc.addActionListener(ak);
		pomoc.add(mpomoc);
		
		moprog=new JMenuItem("O programie");
		moprog.setAccelerator(KeyStroke.getKeyStroke("F10"));
		moprog.addActionListener(ak);
		pomoc.add(moprog);
		
		jtb=new JToolBar();
		jtb.setBounds(0, 0, 517, 50);
		panel.add(jtb);
		
		ttytul=new JTextField();
		ttytul.setBounds(525, 30, 150, 20);
		panel.add(ttytul);
		
		tcena=new JTextField();
		tcena.setBounds(525, 80, 70, 20);
		panel.add(tcena);
		
		tgatunek=new JTextField();
		tgatunek.setBounds(525, 180, 115, 19);
		panel.add(tgatunek);
		
		tkod=new JTextField();
		tkod.setBounds(525, 230, 115, 20);
		panel.add(tkod);
		
		sdm=new SpinnerDateModel();
		
		tdata=new JSpinner(sdm);
		tdata.setBounds(525, 130, 130, 20);
		panel.add(tdata);
		
		sdf=((JSpinner.DateEditor) tdata.getEditor()).getFormat();
		sdf.applyPattern("yyyy-MM-dd");
		
		Date d=new Date();
		tdata.setValue(d);
		
		przyklad=new JTextField();
		przyklad.setText("Przykładowy tekst");
		przyklad.setBounds(20, 180, 325, 85);
		panel2.add(przyklad);
		
		lrcz=new JLabel(srcz);
		lrcz.setBounds(330, 125, 70, 15);
		panel2.add(lrcz);
		
		status=new JLabel("Stwórz lub otwórz istniejącą listę ulubionych płyt CD.");
		status.setBounds(10, 425, 625, 25);
		panel.add(status);
		
		dod=new JButton("Dodaj");
		dod.addActionListener(ak);
		ImageIcon imgdod=new ImageIcon("plus.png");
		dod.setIcon(imgdod);
		dod.setToolTipText("Po wpisaniu danych w pola obok kliknij, by dodać płytę");
		jtb.add(dod);
		
		us=new JButton("Usuń");
		ImageIcon imgus=new ImageIcon("minus.png");
		us.setIcon(imgus);
		us.setToolTipText("Kliknij, aby usunąć płytę");
		us.addActionListener(ak);
		jtb.add(us);
		
		mod=new JButton("Modyfikuj");
		ImageIcon imgmod=new ImageIcon("mod.png");
		mod.setIcon(imgmod);
		mod.setToolTipText("Po wpisaniu danych w pola obok kliknij, by zmodyfikować rekord, następnie podaj liczbę rekordu.");
		mod.addActionListener(ak);
		jtb.add(mod);
		
		tab=new JTable();
	    String tytul[] = new String[] { "Lp", "Nazwa", "Cena", "Data wydania", "Gatunek", "Ocena" };
	    dm.setColumnIdentifiers(tytul);
	    tab.setModel(dm);
	    
	    jsp=new JScrollPane(tab);
		jsp.setBounds(10, 60, 500, 360);
		panel.add(jsp);
		
		kc=new JButton("Kolor czcionki");
		kc.setBounds(10, 10, 135, 25);
		kc.setToolTipText("Zmienia kolor czcionki");
		kc.addActionListener(ak);
		panel2.add(kc);
		
		kt=new JButton("Kolor tła");
		kt.setBounds(10, 50, 100, 25);
		kt.setToolTipText("Zmienia kolor tła");
		kt.addActionListener(ak);
		panel2.add(kt);
		
		addMouseListener(new Mysz());
		addWindowListener(new Okno());
		
		jfc.setFileFilter(fnef);
		
		b=new JCheckBox("Pogrubiona");
		b.setBounds(195, 10, 125, 25);
		b.setToolTipText("Zmienia wygląd czcionki na pogrubiony");
		b.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object zr=e.getItemSelectable();
				
				if(zr==b)
					bb=!bb;
				
				if(bb==false && bi==false) {
					f=new Font("Arial", Font.PLAIN, rcz);
					tab.setFont(f);
					przyklad.setFont(f);
					status.setText("Zmieniono wygląd czcionki.");
				}
				
				if(bb==true) {
					f=new Font("Arial", Font.BOLD, rcz);
					tab.setFont(f);
					przyklad.setFont(f);
					status.setText("Zmieniono wygląd czcionki");
				}
				
				if(bb==true && bi==true) {
					f=new Font("Arial", Font.BOLD + Font.ITALIC, rcz);
					tab.setFont(f);
					przyklad.setFont(f);
					status.setText("Zmieniono wygląd czcionki.");
				}
			}
		});
		panel2.add(b);
		
		i=new JCheckBox("Kursywa");
		i.setBounds(195, 40, 105, 25);
		i.setToolTipText("Zmienia wygląd czcionki na pochylony");
		i.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object zr=e.getItemSelectable();
				
				if(zr==i)
					bi=!bi;
				
				if(bb==false && bi==false) {
					f=new Font("Arial", Font.PLAIN, rcz);
					tab.setFont(f);
					przyklad.setFont(f);
					status.setText("Zmieniono wygląd czcionki.");
				}
				
				if(bi==true) {
					f=new Font("Arial", Font.ITALIC, rcz);
					tab.setFont(f);
					przyklad.setFont(f);
					status.setText("Zmieniono wygląd czcionki.");
				}
			}
		});
		panel2.add(i);
	}
	
	public class Akcja extends JFrame implements ActionListener {	 
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Object zr=e.getSource();
			
			if(zr==motworzbaza) {
				try {
					log.odczytaj();
					status.setText("Wczytano dane z bazy");
				}
				catch (ParseException e1) {
					System.out.println(e1.getMessage());
				}
			}
			
			if(zr==mzapiszbaza) {
				log.zapisbaza();
				status.setText("Pomyślnie zapisano dane w bazie");
			}
		
			if(zr==motworzxml) {
				if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					File plikotw=jfc.getSelectedFile();
					log.otworz(plikotw.toString());
					status.setText("Plik otwarto pomyślnie.");
				}
			}
		
			if(zr==mzapiszxml) {
				if(jfc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
					File plikzap=jfc.getSelectedFile();
					log.zapisz(plikzap.toString()+".xml");
					status.setText("Zapisano pomyślnie.");
				}	
			}
		
			if(zr==mwyjscie) {
				int odp=JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?", "Wyjście", JOptionPane.YES_NO_OPTION);
				if(odp==JOptionPane.YES_OPTION)
					System.exit(0);
			}
		
			if(zr==mlp) {
				log.lpsort();
				status.setText("Posortowano według lp.");
			}
		
			if(zr==mtytul) {
				log.tytulsort();
				status.setText("Posortowano według nazwy.");
			}
			
			if(zr==mcena) {
				log.cenasort();
				status.setText("Posortowano według ceny.");
			}
		
			if(zr==mdata) {
				log.datasort();
				status.setText("Posortowano według daty wydania.");
			}
			
			if(zr==mpomoc) {
				pm=new Pomoc();
				pm.help();
			}
		
			if(zr==moprog) {
				op=new Oprog();
				op.oprog();
			}
		
			if(zr==dod) {
				if(log.blokadadane==true)
					JOptionPane.showMessageDialog(null, "Dane nie są posortowane wg lp.");
				else {
					snazwa=ttytul.getText();
					scena=tcena.getText();
					data=(Date) tdata.getValue();
					sgatunek=tgatunek.getText();
					skod=tkod.getText();
					try {
						log.dodaj(snazwa, scena, data, sgatunek, skod);
						JOptionPane.showMessageDialog(null, "Dodano rekord");
					}	
					catch (ParseException | BladCena | BladOcena e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		
			if(zr==us) {
				if(Plyty.lista.isEmpty()) {
					try {
						throw new PustaLista();
					}
					catch (PustaLista e2) {
						System.out.println(e2.getMessage());
					}
				}
				else {
					if(log.blokadadane==true)
						JOptionPane.showMessageDialog(null, "Dane nie są posortowane wg lp.");
					else {
						sindex=JOptionPane.showInputDialog("Wprowadź numer indeksu, który chcesz usunąć:");
						try {
							log.usun(sindex);
							JOptionPane.showMessageDialog(null, "Usunięto rekord");
						} 
						catch (BladIndeks e1) {
							System.out.println(e1.getMessage());
						}
					}
				}
			}
			
			if(zr==mod) {
				if(Plyty.lista.isEmpty()) {
					try {
						throw new PustaLista();
					}
					catch (PustaLista e2) {
						System.out.println(e2.getMessage());
					}
				}
				else {
					if(log.blokadadane==true)
						JOptionPane.showMessageDialog(null, "Dane nie są posortowane wg lp.");
					else {
						snazwa=ttytul.getText();
						scena=tcena.getText();
						data=(Date) tdata.getValue();
						sgatunek=tgatunek.getText();
						skod=tkod.getText();
						sindex=JOptionPane.showInputDialog("Wprowadź numer indeksu, który chcesz zmodyfikować:");
						try {
							log.modyfikuj(snazwa, scena, data, sgatunek, skod, sindex);
							JOptionPane.showMessageDialog(null, "Zmodyfikowano rekord");
						} 	
						catch (ParseException | BladCena | BladOcena | BladIndeks e1) {
							System.out.println(e1.getMessage());
						}
					}
				}
			}
		
			if(zr==kc) {
				czcionka=JColorChooser.showDialog(null, "Wybierz kolor czcionki", Color.BLACK);
				tab.setForeground(czcionka);
				przyklad.setForeground(czcionka);
				status.setText("Zmieniono kolor czcionki.");
			}
			
			if(zr==kt) {
				tlo=JColorChooser.showDialog(null, "Wybierz kolor tła", Color.WHITE);
				tab.setBackground(tlo);
				przyklad.setBackground(tlo);
				status.setText("Zmieniono kolor tła.");
			}
		}
	}
	
	private class Suwak implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			rcz=slider.getValue();
			srcz=String.valueOf(rcz);
			lrcz.setText(srcz);
			if(bb==false && bi==false)
				f=new Font("Arial", Font.PLAIN, rcz);

			if(bb==true)
				f=new Font("Arial", Font.BOLD, rcz);

			if(bi==true)
				f=new Font("Arial", Font.ITALIC, rcz);

			if(bb==true && bi==true)
				f=new Font("Arial", Font.BOLD + Font.ITALIC, rcz);
			tab.setFont(f);
			przyklad.setFont(f);
			status.setText("Zmieniono wygląd czcionki.");
		}
	}
	/**
	 * Klasa adaptacyjna
	 */
	protected class Okno extends WindowAdapter implements WindowListener {		
		public void windowClosing(WindowEvent e) {
			int odp=JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?", "Wyjście", JOptionPane.YES_NO_OPTION);
			if(odp==JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
	protected class Mysz extends MouseAdapter implements MouseListener {
		public void mouseEntered(MouseEvent e) {
			txtstatus=status.getText();
			status.setText("Nie zapomnij zapisać danych");
		}
		public void mouseExited(MouseEvent e) {
			status.setText(txtstatus);
		}
	}
}

class Oprog extends JFrame {
	private static final long serialVersionUID = 1L;

	public void oprog() {
		JOptionPane.showMessageDialog(this, "Lista płyt CD. Autor: Hubert Rębiałkowski. Rok: 2016", "O programie", JOptionPane.PLAIN_MESSAGE);
	}
}

class Pomoc extends JFrame {
	private static final long serialVersionUID = 1L;

	JTextArea text;
	JScrollPane jsp;
	
	String help="Instrukcja obsługi aplikacji.\n\n"
			+ "Dodawanie danych: \n"
			+ "Aby dodać element należy wpisać w odpowiednie pola: \n"
			+ "nazwę albumu, cenę, datę wydania (rok-miesiąc-dzień), gatunek muzyczny \n"
			+ "oraz ocenę (1-6). Następnie kliknąć przycisk Dodaj. \n"
			+ "O zakończonej operacji poinformuje okienko. \n"
			+ "\n"
			+ "Usuwanie danych: \n"
			+ "Aby usunąć element z listy należy kliknąć przycisk Usuń \n"
			+ "a następnie wpisać numer indeksu elementu, \n"
			+ "który ma zostać usunięty. \n"
			+ "Wybór potwierdzamy przyciskiem Ok. \n"
			+ "\n"
			+ "Modyfikacja danych: \n"
			+ "Aby zmodyfikować dany elemnt listy należy: \n"
			+ "wprowadzić dane odpowiednio do pól, \n"
			+ "po czym kliknąć przycisk Modyfikuj. \n"
			+ "Po tym należy wprowadzić numer indeksu elementu, \n"
			+ "który ma zostać zastąpiony innym. \n"
			+ "\n"
			+ "Sortowanie danych: \n"
			+ "Po dodaniu i modyfikacji danych można posortować listę \n"
			+ "według danego kryterium. W tym celu należy z menu \n"
			+ "pozycję Sortowanie, a z niej kryterium, \n"
			+ "według którego lista ma się posortować. \n"
			+ "\n"
			+ "Zapisywanie danych do bazy danych: \n"
			+ "W celu zapisania listy należy \n"
			+ "z menu Plik wybrać opcję Zapisz do bazy danych \n"
			+ "bądź wcisnąć kombinację klawiszy \n"
			+ "Ctrl-S."
			+ "\n"
			+ "Odczyt z bazy danych: \n"
			+ "W celu odczytania listy z bazy danych należy \n"
			+ "z menu Plik wybrać opcję Wczytaj z bazy lub wcisnąć klawisze Ctrl-L. \n"
			+ "\n"
			+ "Zapisywanie danych do pliku XML: \n"
			+ "W celu zapisania listy należy \n"
			+ "z menu Plik wybrać opcję Zapisz jako XML \n"
			+ "bądź wcisnąć kombinację klawiszy \n"
			+ "Ctrl-Z. Następnie podać ścieżkę zapisu i nazwę pliku, \n"
			+ "\n"
			+ "Odczyt danych z pliku XML: \n"
			+ "W celu odczytania listy z pliku XML należy \n"
			+ "z menu Plik wybrać opcję Otwórz lub wcisnąć klawisze Ctrl-O. \n"
			+ "Następnie podać ścieżkę pliku i potwierdzić operację.";
	
	public void help() {
		JFrame fr=new JFrame();
		fr.setVisible(true);
		fr.setSize(350, 500);
		fr.setTitle("Pomoc");
		
		text=new JTextArea(help);
		text.setEditable(false);
		
		jsp=new JScrollPane(text);
		jsp.setBounds(5, 5, 340, 490);
		fr.add(jsp);
	}
}