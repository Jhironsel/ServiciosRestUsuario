package com.practicas.puesto;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.practica.puesto.modelos.UsuariosEntity;

import static io.restassured.RestAssured.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest  {

	@Before
	public void endPoini() {
		baseURI = "http://localhost";
		port = 8080;
	}
	
	/**
	 * Prueba que nos permite agregar un usuario 
	 * desde nuestro endPoint.
	 */
	@Test
	public void test_1postUsuario(){
		UsuariosEntity ue = 
				new UsuariosEntity("Silvia Sofia", "Almonte Perez", true);
		
		String string = given().
			contentType("application/json").
			body(ue).
		when().
			post("/postUsuario").
		then().
			statusCode(equalTo(200)).
			extract().asString();
		assertTrue(string.contains("Silvia Sofia"));
	}
	
	/**
	 * Prueba que nos permite agregar una lista de usuarios
	 * a nuestro endPoint.
	 * 
	 */
	@Test
	public void test_2postUsuarios(){
		List<UsuariosEntity> list = new ArrayList<UsuariosEntity>();
		list.add(new UsuariosEntity("Eduardo Ernesto", "Diaz Perez", false));
		list.add(new UsuariosEntity("Rodailsa", "Diaz Almonte", true));
		
		String string = given().
			contentType("application/json").
			body(list).
		when().
			post("/postUsuarios").
		then().
			statusCode(equalTo(200)).
			extract().asString();
			
		
		assertTrue(string.contains("Silvia Sofia"));
		assertTrue(string.contains("Diaz Perez"));
		assertTrue(string.contains("Diaz Almonte"));
	}
	
	/**
	 * Prueba que nos permite saber sobre los atributos y valores de los usuarios
	 * de nuestro endPoint.
	 */
	@Test
	public void test_3getUsuarios() {
		given().
			when().
				get("/getUsuarios").
			then().
				statusCode(equalTo(200)).
				body(containsString("Silvia Sofia")).
				body(containsString("Diaz Perez")).
				body(containsString("Diaz Almonte"));
	}
	
	/**
	 * Prueba que nos permite consultar un identificador de un usuario por su 
	 * id en especifico. 
	 */
	@Test
	public void test_4getUsuarioById() {
		given().
			pathParam("id", 1).
		when().
			get("/getUsuariosById/{id}").
		then().
			statusCode(equalTo(200)).
			body("[0].nombres", equalTo("Silvia Sofia"));
	}
	
	
	/**
	 * Prueba que nos permite obtener una lista de usuario 
	 * por su estado activo, utilizando el metodo pathParam para
	 * obtener el valor atraves de la URI. 
	 */
	@Test
	public void test_5getUsuarioByEstado() {
		given().
			pathParam("estado", true).
		when().
			get("/getUsuariosByEstado/{estado}").
		then().
			statusCode(equalTo(200)).
			body("[0].nombres",equalTo("Silvia Sofia"));
	}
	
	
	/**
	 * Prueba que nos permite obtener un usuario 
	 * por su identificador, utilizando el metodo pathParam para
	 * obtener el valor atraves de la URI. 
	 */
	@Test
	public void test_6getUsuarioByIdParam() {
		given().
			param("id", 1).
		when().
			get("/getUsuariosByIdParam/").
		then().
			statusCode(equalTo(200)).
			body("[0].nombres",equalTo("Silvia Sofia"));
	}
	
	
	/**
	 * Prueba que nos permite obtener una lista de usuarios 
	 * por su estado en false, utilizando el metodo pathParam
	 * para obtener los valores atravez de la URI. 
	 */
	@Test
	public void test_7getUsuarioByEstadoParam() {
		String string = given().
			param("estado", false).
		when().
			post("/getUsuariosByEstadoParam/").
		then().
			statusCode(equalTo(200)).
			extract().asString();
		assertTrue(string.contains("Eduardo Ernesto"));
	}
	
	/**
	 * Esta prueba nos permite asegurar que un usuario ha sido eliminado 
	 * de nuestro endPoint. 
	 */
	@Test
	public void test_8deleteUsuarioById() {
		String string = given().
			pathParam("id", 1).
		when().
			delete("/deleteUsuarioById/{id}").
		then().
			statusCode(equalTo(200)).
			extract().asString();
		assertFalse(string.contains("Generico"));
	}
	
	
	/**
	 * Esta prueba nos permite modificar un usuario por su ID de nuestro 
	 * servicio Rest. 
	 */
	@Test
	public void test_9updateUsuariosById() {
		UsuariosEntity ue = 
				new UsuariosEntity("Generico", "Generico", false);
		
		String string = given().
			pathParam("id", 3).
			contentType("application/json").
			body(ue).
		when().
			put("updateUsuarioById/{id}").
		then().
			statusCode(equalTo(200)).
			extract().asString();
		
		assertFalse(string.contains("Silvia Sofia"));
		assertTrue(string.contains("Generico"));
			
	}
}

























