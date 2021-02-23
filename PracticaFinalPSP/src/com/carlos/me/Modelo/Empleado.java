package com.carlos.me.Modelo;

public class Empleado {

	
	private String id;
	private String nombre;
	private String fecha_contratacion;
	private String ultima_sesion;
	
	
	
	public Empleado() {
		super();
	}

	public Empleado(String id, String nombre, String fecha_contratacion, String ultima_Sesion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha_contratacion = fecha_contratacion;
		this.ultima_sesion = ultima_Sesion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_contratacion() {
		return fecha_contratacion;
	}

	public void setFecha_contratacion(String fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}

	public String getUltima_Sesion() {
		return ultima_sesion;
	}

	public void setUltima_Sesion(String ultima_Sesion) {
		this.ultima_sesion = ultima_Sesion;
	}

	@Override
	public String toString() {
		return id+";"+nombre+";"+"fecha_contratacion"+";"+ultima_sesion;
	}
	
	

	
}
