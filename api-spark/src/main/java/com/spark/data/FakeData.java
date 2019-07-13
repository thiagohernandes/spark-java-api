package com.spark.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.spark.model.Funcionario;
import com.spark.model.Salario;

public class FakeData {

	private static List<Funcionario> listaFuncionarios = new ArrayList<>();
	private static List<Salario> listaSalarios = new ArrayList<>();
	
	public FakeData() {
		listaFuncionarios.addAll(Arrays
				.asList(new Funcionario(ThreadLocalRandom.current().nextInt(1, 1000), "Fulano", 12000.0),
						new Funcionario(ThreadLocalRandom.current().nextInt(1, 1000), "Beltrano", 9000.0),
						new Funcionario(ThreadLocalRandom.current().nextInt(1, 1000), "Filisbgino", 15000.0),
						new Funcionario(ThreadLocalRandom.current().nextInt(1, 1000), "Chicrute", 5000.0)));
		listaSalarios.addAll(Arrays
				.asList(new Salario(ThreadLocalRandom.current().nextInt(1, 1000), 12000.0),
						new Salario(ThreadLocalRandom.current().nextInt(1, 1000),9000.0),
						new Salario(ThreadLocalRandom.current().nextInt(1, 1000), 15000.0)));
	}
	
	public List<Salario> getAllSalarios() throws Exception {
		throw new Exception();
	}
	
	public List<Funcionario> getAllFuncionarios() {
		return listaFuncionarios;
	}
	
	public Funcionario newFuncionario(Funcionario funcionario) {
		Funcionario novo = new Funcionario(ThreadLocalRandom.current().nextInt(1001, 2000),
				funcionario.getNome(), funcionario.getSalario());
		listaFuncionarios.add(novo);
		return novo; 
	}
	
	public Funcionario updateFuncionario(Funcionario funcionario) {
		Funcionario funcRetorno = new Funcionario();
		listaFuncionarios.stream().forEach(func -> {
			if (func.getCodigo() == funcionario.getCodigo()) {
				func.setSalario(funcionario.getSalario());
				func.setNome(funcionario.getNome());
				funcRetorno.setCodigo(func.getCodigo());
				funcRetorno.setNome(funcionario.getNome());
				funcRetorno.setSalario(funcionario.getSalario());
			}
		});
		return funcRetorno;
	}
	
	public void deleteFuncionario(int codigo) {
		listaFuncionarios.removeIf(funcionario -> funcionario.getCodigo() == codigo);
	}
	
}
