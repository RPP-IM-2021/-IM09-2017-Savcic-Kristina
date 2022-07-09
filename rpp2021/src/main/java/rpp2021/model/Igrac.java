package rpp2021.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Igrac {

	@Id
	@SequenceGenerator(name="IGRAC_ID_GENERATOR", sequenceName="IGRAC_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IGRAC_ID_GENERATOR")
	private Integer id;
	
	private String ime;
	
	private String prezime;
	
	@Column(name = "broj_reg")
	private String brojReg;
	
	@Column(name = "datum_rodjenja")
	private Date datumRodjenja;
	
	@ManyToOne
	private Tim tim;
	
	@ManyToOne
	private Nacionalnost nacionalnost;

}
