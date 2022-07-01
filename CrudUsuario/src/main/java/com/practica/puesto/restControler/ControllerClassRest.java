package com.practica.puesto.restControler;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.puesto.modelos.UsuariosEntity;

/**
 * La clase ControllerClassRest es utilizada para implementar los metodos que
 * permitiran hacer las operaciones necesaria en la lista de UsuariosEntity.
 * 
 * @author jhironsel
 *
 */
@RestController
@RequestMapping("/")
@Service
public class ControllerClassRest {
	/**
	 * Objecto lista para guardar los registros de los usuarios.
	 * 
	 */
	HashSet<UsuariosEntity> miLista = new HashSet<UsuariosEntity>();

	/**
	 * Metodo utilizado para registrar a un usuario.
	 * 
	 * @param u Objecto del modelo de los registro de los usuarios.
	 * 
	 * @return Retorna una lista o JSON con los usuarios registrados.
	 * 
	 * @Test Localizado en el ControllerTest con el metodo postUsuario()
	 * 
	 */
	@PostMapping("/postUsuario")
	public TreeSet<UsuariosEntity> postUsuario(@RequestBody UsuariosEntity u) {
		miLista.add(u);
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		return list;
	}

	/**
	 * Metodo que nos permite guardar una lista de usuario de tipo JSON
	 * 
	 * @param u Recibe una lista de List<UsuariosEntity> para ser agregado varios
	 *          usuario a la vez.
	 * 
	 * @return Retorna una List<UsuariosEntity> de todo el sistema.
	 * 
	 * @Test Localizado en el ControllerTest con el metodo postUsuarios()
	 */
	@PostMapping("/postUsuarios")
	public TreeSet<UsuariosEntity> postUsuarios(@RequestBody HashSet<UsuariosEntity> u) {
		u.stream().forEach(p -> {
			miLista.add(p);
		});
		
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		
		return list;
	}

	/**
	 * Metodo que nos permite recuperar todos los usuarios.
	 * 
	 * @return Devuelve el objeto miLista de los registros.
	 * 
	 * @Test Localizado en el ControllerTest con el método getUsuarios()
	 */
	@GetMapping("/getUsuarios")
	public TreeSet<UsuariosEntity> getUsuarios() {
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
        
		return list;
	}

	/**
	 * Metodo que nos permite recuperar un usuario por su identificar.
	 * 
	 * @param Identificador del usuario en el endPoint.
	 * 
	 * @return Retorna un objecto de tipo List<UsuariosEntity> con los usuarios que
	 *         tenga como atributo un id.
	 * 
	 * @Test Localizado en el ControllerTest con el método getUsuarioById()
	 */
	@GetMapping("/getUsuariosById/{id}")
	public List<UsuariosEntity> getUsuariosById(@PathVariable("id") int id) {
		return miLista.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
	}

	/**
	 * Metodo que nos devuelve una lista de usuario por el estado del los usuario lo
	 * cual pueden ser TRUE o FALSE
	 * 
	 * @param Solicitar un valor booleano para obtener una lista de los usuario del
	 *                  sistema con un estado posible de activo o inactivo de los
	 *                  usuarios.
	 * 
	 * @return Retorna un objecto de tipo List<UsuariosEntity> con los usuarios que
	 *         tenga como atributo un estado.
	 * 
	 * @Test Localizado en el ControllerTest con el método getUsuarioByEstado()
	 */
	@GetMapping("/getUsuariosByEstado/{estado}")
	public List<UsuariosEntity> getUsuariosByEstado(
			@PathVariable("estado") boolean estado) {
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		return list.stream().filter(x -> x.isEstado() == estado).collect(Collectors.toList());
	}

	/**
	 * Obtenemos una lista de usuarios por su ID por rutas de parametros de la URI.
	 * 
	 * @param Identificador del usuario en el sistema que se quiere consultar.
	 * 
	 * @return Retorna un objeto List<UsuariosEntity> con el usuario que se quiere
	 *         consultar.
	 * 
	 * @Test Localizado en el ControllerTest con el metodo getUsuarioById().
	 */
	@GetMapping("/getUsuariosByIdParam/")
	public List<UsuariosEntity> getUsuariosByIdParam(@PathParam("id") int id) {
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		return list.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
	}

	/**
	 * Obtenemos un usuario por su ID por la ruta de parametros de la URI.
	 * 
	 * @param Identificador del usuario en el sistema que se quiere consultar.
	 * 
	 * @return Retorna un objeto List<UsuariosEntity> con el usuario que se quiere
	 *         consultar.
	 * 
	 * @Test Localizado en el ControllerTest con el metodo getUsuarioByEstadoParam().
	 */
	@PostMapping("/getUsuariosByEstadoParam/")
	public List<UsuariosEntity> getUsuariosByEstadoParam(@PathParam("estado") boolean estado) {
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		return list.stream().filter(x -> x.isEstado() == estado).collect(Collectors.toList());
	}

	/**
	 * Metodo utilizando para borrar un usuario de la lista.
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * 
	 */
	@DeleteMapping("/deleteUsuarioById/{id}")
	public TreeSet<UsuariosEntity> deleteUsuarioById(
			@PathVariable("id") int id) {
		
		miLista.removeIf(x -> x.getId() == id);
		TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		return list;
	}

	
	@PutMapping("updateUsuarioById/{id}")
	public TreeSet<UsuariosEntity> updateUsuariosById(
			@PathVariable("id") int id, @RequestBody UsuariosEntity u) {
		//if (miLista.contains(u)) {
			deleteUsuarioById(id);
			UsuariosEntity nuevo = u;
			nuevo.setId(id);
			postUsuario(nuevo);
		//}
			TreeSet<UsuariosEntity> list = new TreeSet<>(miLista);
		return list;
	}//
}














