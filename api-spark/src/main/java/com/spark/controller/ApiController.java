package com.spark.controller;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.threadPool;


import org.springframework.http.MediaType;

import com.spark.data.FakeData;
import com.spark.model.Funcionario;
import com.spark.util.JsonTransformer;
import com.spark.util.Util;

public class ApiController {

	private FakeData dataFake = new FakeData();
	private Util<Funcionario> util = new Util<>();
	
	public ApiController() {
		initController();
	}
	
	public void initController() {
		
		int maxThreads = 8;
		int minThreads = 2;
		int timeOutMillis = 30000;
		
		// ****************** Conf API ******************
		threadPool(maxThreads, minThreads, timeOutMillis);
		
		// ****************** Filter API ******************
		before((request, response) -> {
			System.err.println(" ******** API Spark intercepting before! ******** ");
			System.err.println(request.headers("x-teste"));
		});
		
		// ****************** API Root ******************
		path("/api", () -> {
			
		    before("/*", (q, a) -> System.out.println(" ******** API Spark started! ******** "));
		// ****************** API Funcionários ******************
		    path("/funcionarios", () -> {
				get("/all", MediaType.APPLICATION_JSON_UTF8_VALUE, (request, response) -> {
				    return dataFake.getAllFuncionarios();
				}, new JsonTransformer());
				post("/new", (request, response) -> {
				   Funcionario funcionario = util.objFromJson(request, Funcionario.class);
				   return util.objToJson(dataFake.newFuncionario(funcionario));
				});
				put("/update", (request, response) -> {
				   Funcionario funcionario = util.objFromJson(request, Funcionario.class);
				   return util.objToJson(dataFake.updateFuncionario(funcionario));
				});
				delete("/delete/:codigo", (request, response) -> {
					   Integer codigo = Integer.parseInt(request.params(":codigo").toString());
					   dataFake.deleteFuncionario(codigo);
					   return "Apagado com sucesso";
					});
			});
		 // ****************** API Salários ******************
		    path("/salarios", () -> {
				get("/all", MediaType.APPLICATION_JSON_UTF8_VALUE, (request, response) -> {
					try {
				    return dataFake.getAllSalarios();
					} catch(Exception e) {
						response.status(300);
						response.body("Custom erro !!");
						return response.body();
					}
				}, new JsonTransformer());
			});
		});
	}
}
