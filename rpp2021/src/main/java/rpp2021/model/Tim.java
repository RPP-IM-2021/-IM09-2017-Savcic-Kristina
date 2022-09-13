package rpp2021.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tim")
@NamedQuery(name="Tim.findAll", query="SELECT p FROM Tim p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIM_ID_GENERATOR", sequenceName="TIM_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIM_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private Date osnovan;

	private String sediste;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "liga")
	private Liga liga;

	@OneToMany(mappedBy="tim", cascade = {CascadeType.ALL})
	@JsonIgnore
	private List<Igrac> igracs;

	public Tim() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Date getOsnovan() {
		return this.osnovan;
	}

	public void setOsnovan(Date osnovan) {
		this.osnovan = osnovan;
	}

	public String getSediste() {
		return this.sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}


	public Liga getLiga() {
		return this.liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}

	public List<Igrac> getIgracs() {
		return this.igracs;
	}

	public void setIgracs(List<Igrac> igracs) {
		this.igracs = igracs;
	}

	public Igrac addIgrac(Igrac igrac) {
		getIgracs().add(igrac);
		igrac.setTim(this);

		return igrac;
	}

	public Igrac removeIgrac(Igrac igrac) {
		getIgracs().remove(igrac);
		igrac.setTim(null);

		return igrac;
	}

}
