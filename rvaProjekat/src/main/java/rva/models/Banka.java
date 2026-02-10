package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Banka implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "BANKA_SEQ_GENERATOR", sequenceName = "BANKA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANKA_SEQ_GENERATOR")
	private int ID;
	private String naziv;
	private String kontakt;
	private int PIB;
	
	@OneToMany(mappedBy = "banka", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Filijala> filijala;
	
	public Banka() {
		
	}
	
	public Banka(int iD, String naziv, String kontakt, int pIB) {
		super();
		ID = iD;
		this.naziv = naziv;
		this.kontakt = kontakt;
		PIB = pIB;
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

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public int getPIB() {
		return PIB;
	}

	public void setPIB(int pIB) {
		PIB = pIB;
	}
	
}
