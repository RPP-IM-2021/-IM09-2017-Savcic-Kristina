package rpp2021.controller.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rpp2021.model.Nacionalnost;
import rpp2021.model.Tim;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IgracRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
    private String ime;
	
	private String prezime;

	private Tim tim;
	
	private Nacionalnost nacionalnost;
	
	private String brojReg;
	
	private Date datumRodjenja;

}
