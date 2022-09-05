package rpp2021.controller.model;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rpp2021.model.Igrac;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NacionalnostRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private Integer id;
	 
	 private String naziv;
	 
	 private String skracenica;
	 
	 private List<Igrac> igraciId;
	
}
