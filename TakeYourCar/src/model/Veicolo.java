package model;

import java.io.Serializable;

public class Veicolo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String targa;
	private String modello;
	private String colore;
	private String deposito;
	
	public Veicolo() {
		targa = "";
		modello = "";
		colore = "";
		deposito = "";
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}	
}