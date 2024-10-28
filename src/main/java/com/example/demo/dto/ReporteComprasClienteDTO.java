package com.example.demo.dto;

public class ReporteComprasClienteDTO {

	private String nombre;
	private double total;
	
	
	
	public ReporteComprasClienteDTO(String nombre, double total) {
		super();
		this.nombre = nombre;
		this.total = total;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
