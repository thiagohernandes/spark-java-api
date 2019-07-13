package com.spark.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.ALWAYS)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Funcionario {

	private int codigo;
	private String nome;
	private Double salario;
	
}
