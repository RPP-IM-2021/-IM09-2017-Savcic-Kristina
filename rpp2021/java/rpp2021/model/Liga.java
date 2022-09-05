package rpp2021.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Liga implements Serializable {
	
	private static final long serialVersionUID = 1L;

	 @Id
	 @SequenceGenerator(name="LIGA_ID_GENERATOR", sequenceName="LIGA_SEQ", allocationSize=1)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIGA_ID_GENERATOR")
	 private Integer id;
	 
	 private String naziv;
	 
	 private String oznaka;

	 @JsonIgnore
	 @OneToMany(cascade = CascadeType.ALL)
	 private List<Tim> timovi;
}

