package projekt;

import java.sql.*;
import java.util.Date;

public class Baza
{
	int lp, ocena;
	String nazwa, gatunek;
	double cena;
	Date data;
	Logika log=new Logika();
	
	private Connection con;
	private Statement stat;
	
	public Baza() {
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection("jdbc:sqlite:plyty.db");
			stat=con.createStatement();
		}
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		stworzTabele();
	}
	
	public boolean stworzTabele() {
		String createPlyty="CREATE TABLE IF NOT EXISTS plyty (lp int, nazwa varchar(50), cena float, data varchar(50), gatunek varchar(50), ocena int)";
		try {
			stat.execute(createPlyty);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean insert(int lp, String nazwa, double cena, Date data, String gatunek, int ocena) {
		try {
			PreparedStatement ps=con.prepareStatement("insert into plyty values(?, ?, ?, ?, ?, ?);");
			ps.setInt(1, lp);
			ps.setString(2, nazwa);
			ps.setDouble(3, cena);
			java.sql.Date sqldata=new java.sql.Date(data.getTime());
			ps.setDate(4, sqldata);
			ps.setString(5, gatunek);
			ps.setInt(6, ocena);
			ps.execute();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean delete() {
		try {
			PreparedStatement ps=con.prepareStatement("DELETE FROM plyty;");
			ps.execute();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public void select() {
		try {
			ResultSet rs=stat.executeQuery("SELECT * FROM plyty");
			Plyty.lista.clear();
			while(rs.next()) {
				log.lp=rs.getInt("lp");
				nazwa=rs.getString("nazwa");
				cena=rs.getDouble("cena");
				data=rs.getDate("data");
				gatunek=rs.getString("gatunek");
				ocena=rs.getInt("ocena");
				Cecha c=new Cecha(gatunek, ocena);
				Plyty.lista.add(new Plyty(log.lp, nazwa, cena, data, c));
			}
			log.lp++;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			con.close();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
