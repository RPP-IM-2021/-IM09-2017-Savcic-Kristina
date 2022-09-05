package rpp2021.controller.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rpp2021.model.Igrac;
import rpp2021.model.Liga;
import lombok.Data;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String naziv;

	private Date osnovan;
	
	private String sediste;
	 
	private Liga liga;
	
}
