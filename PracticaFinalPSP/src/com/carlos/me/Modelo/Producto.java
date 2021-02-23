package com.carlos.me.Modelo;

public class Producto {
	
	private Integer ID_Producto;
	private String Nombre_Producto;
	private Integer Precio_Venta;
	private Integer Precio_Proveedor;
	private Integer Cantidad_Stock;
	
	
	
	public Producto() {
		super();
	}
	
	public Producto(Integer iD_Producto, String nombre_Producto, Integer precio_Venta, Integer precio_Proveedor,
			Integer cantidad_Stock) {
		super();
		ID_Producto = iD_Producto;
		Nombre_Producto = nombre_Producto;
		Precio_Venta = precio_Venta;
		Precio_Proveedor = precio_Proveedor;
		Cantidad_Stock = cantidad_Stock;
	}
	
	public Integer getID_Producto() {
		return ID_Producto;
	}
	public void setID_Producto(Integer iD_Producto) {
		ID_Producto = iD_Producto;
	}
	public String getNombre_Producto() {
		return Nombre_Producto;
	}
	public void setNombre_Producto(String nombre_Producto) {
		Nombre_Producto = nombre_Producto;
	}
	public Integer getPrecio_Venta() {
		return Precio_Venta;
	}
	public void setPrecio_Venta(Integer precio_Venta) {
		Precio_Venta = precio_Venta;
	}
	public Integer getPrecio_Proveedor() {
		return Precio_Proveedor;
	}
	public void setPrecio_Proveedor(Integer precio_Proveedor) {
		Precio_Proveedor = precio_Proveedor;
	}
	public Integer getCantidad_Stock() {
		return Cantidad_Stock;
	}
	public void setCantidad_Stock(Integer cantidad_Stock) {
		Cantidad_Stock = cantidad_Stock;
	}

	
	
}
