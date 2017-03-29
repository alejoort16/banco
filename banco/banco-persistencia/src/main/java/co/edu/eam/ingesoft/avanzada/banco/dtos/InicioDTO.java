package co.edu.eam.ingesoft.avanzada.banco.dtos;

import java.io.Serializable;

import entidades.Franquicia;

public class InicioDTO implements Serializable{

	
	private String numTarjeta;
	
	private String nomFranquicia;
	
	private double deudaAlaFecha;
	
	private double diponible;

	public InicioDTO(String numTarjeta, String nomFranquicia, double deudaAlaFecha, double diponible) {
		super();
		this.numTarjeta = numTarjeta;
		this.nomFranquicia = nomFranquicia;
		this.deudaAlaFecha = deudaAlaFecha;
		this.diponible = diponible;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getNomFranquicia() {
		return nomFranquicia;
	}

	public void setNomFranquicia(String nomFranquicia) {
		this.nomFranquicia = nomFranquicia;
	}

	public double getDeudaAlaFecha() {
		return deudaAlaFecha;
	}

	public void setDeudaAlaFecha(double deudaAlaFecha) {
		this.deudaAlaFecha = deudaAlaFecha;
	}

	public double getDiponible() {
		return diponible;
	}

	public void setDiponible(double diponible) {
		this.diponible = diponible;
	}
	
	
	
}
