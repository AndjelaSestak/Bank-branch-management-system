package rva.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Usluga implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "USLUGA_SEQ_GENERATOR", sequenceName = "USLUGA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USLUGA_SEQ_GENERATOR")
	private int ID;
	
	private String naziv;
	private String opisUsluge;
	private Date datumUgovora;
	private double provizija;
	
	@ManyToOne
	@JoinColumn(name= "filijala")
	private Filijala filijala;
	
	@ManyToOne
	@JoinColumn(name= "korisnik")
	private KorisnikUsluge korisnik;
	
	public Usluga() {
		
	}
	public Usluga(int iD, String naziv, String opisUsluge, Date datumUgovora, double provizija) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.opisUsluge = opisUsluge;
		this.datumUgovora = datumUgovora;
		this.provizija = provizija;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpisUsluge() {
		return opisUsluge;
	}
	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}
	public Date getDatumUgovora() {
		return datumUgovora;
	}
	public void setDatumUgovora(Date datumUgovora) {
		this.datumUgovora = datumUgovora;
	}
	public double getProvizija() {
		return provizija;
	}
	public void setProvizija(int provizija) {
		this.provizija = provizija;
	}
	public Filijala getFilijala() {
		return filijala;
	}
	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}
	public KorisnikUsluge getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikUsluge korisnik) {
		this.korisnik = korisnik;
	}
	
}
