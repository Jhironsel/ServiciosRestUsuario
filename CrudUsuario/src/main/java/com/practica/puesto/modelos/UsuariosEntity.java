package com.practica.puesto.modelos;

import java.util.Objects;

public class UsuariosEntity implements Comparable<UsuariosEntity>{
	private static final long serialVersionUID = 1L;
	
	private static Integer id_sta=0;
	private Integer id;
	private String nombres;
	private String apellidos;
	private boolean estado;
	
	public UsuariosEntity(String nombres, String apellidos, boolean estado) {
		super();
		id_sta++;
		id=id_sta;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.estado = estado;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof UsuariosEntity)) {
			return false;
		}
		
		UsuariosEntity other = (UsuariosEntity) obj;
		return Objects.equals(apellidos, other.apellidos) && 
				estado == other.estado && 
				id == other.id && 
				Objects.equals(nombres, other.nombres);
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, estado, id, nombres);
	}

	public int getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	@Override
	public String toString() {
		return "UsuariosEntity [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", estado=" + estado
				+ "]";
	}


	@Override
	public int compareTo(UsuariosEntity o) {
		
		return this.id.compareTo(o.id);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
