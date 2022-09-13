package rpp2021.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="igrac")
@NamedQuery(name="Igrac.findAll", query="SELECT s FROM Igrac s")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Igrac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IGRAC_ID_GENERATOR", sequenceName="IGRAC_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IGRAC_ID_GENERATOR")

	private Integer id;
	
	private String ime;
	
	private String prezime;
	
	
	@Column(name = "broj_reg")
	private Integer brojReg;
	
	@Column(name = "datum_rodjenja")
	private Date datumRodjenja;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tim")
	private Tim tim;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nacionalnost")
	private Nacionalnost nacionalnost;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Integer getBrojReg() {
		return brojReg;
	}

	public void setBrojReg(Integer integer) {
		this.brojReg = integer;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Tim getTim() {
		return tim;
	}

	public void setTim(Tim tim) {
		this.tim = tim;
	}

	public Nacionalnost getNacionalnost() {
		return nacionalnost;
	}

	public void setNacionalnost(Nacionalnost nacionalnost) {
		this.nacionalnost = nacionalnost;
	}

}