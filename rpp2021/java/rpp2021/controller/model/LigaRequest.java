package rpp2021.controller.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rpp2021.model.Tim;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LigaRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
     private Integer id;
     
	 private String naziv;
	 
	 private String oznaka;

}
