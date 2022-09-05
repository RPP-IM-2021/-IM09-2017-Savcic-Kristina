package rpp2021.model;

import java.sql.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tim implements Serializable {
	
	private static final long serialVersionUID = 1L;

	 @Id
	 @SequenceGenerator(name="TIM_ID_GENERATOR", sequenceName="TIM_SEQ", allocationSize=1)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIM_ID_GENERATOR")
	 private Integer id;
	
	 private String naziv;

	 private Date osnovan;
	
	 private String sediste;
	
	 @ManyToOne(cascade = CascadeType.PERSIST)
	 private Liga liga;
	 
	 @JsonIgnore
	 @OneToMany(cascade = CascadeType.ALL)
	 private List<Igrac> igraci;
	
}
