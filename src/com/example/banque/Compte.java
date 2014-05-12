package com.example.banque;

public class Compte {
	
	private String name;
	private int ID=0;
	private double solde;
	
	public Compte(String name, double solde){
		ID = ID+1;
		this.name = name;
		this.solde = solde;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getId(){
		return this.ID;
	}
	
	private void setId(int ID){
		this.ID = ID;
	}
	
	public void setSolde(double solde){
		this.solde = solde;
	}
	
	public double getSolde(){
		return this.solde;
	}
	
	public void crediter(double montant){
		this.solde += montant;  
	}
	
	public void debiter(double montant){
		this.solde -= montant;
	}

}
