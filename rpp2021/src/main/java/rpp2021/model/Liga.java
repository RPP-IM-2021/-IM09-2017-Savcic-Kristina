package rpp2021.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name="Liga.findAll", query="SELECT d FROM Liga d")
public class Liga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LIGA_ID_GENERATOR", sequenceName="LIGA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIGA_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String oznaka;
	
	public Liga() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<Tim> getTims() {
		return tims;
	}

	public void setTims(List<Tim> tims) {
		this.tims = tims;
	}

	//bi-directional many-to-one association to Tim
	@OneToMany(mappedBy="liga")
	@JsonIgnore
	private List<Tim> tims;

}
