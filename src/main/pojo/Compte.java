package main.pojo;

public class Compte {

	private int compteId;
	private String proprietaire;
	private double solde;
	
	public Compte(int compteID, String proprietaire, double solde) {
		super();
		this.compteId = compteID;
		this.proprietaire = proprietaire;
		this.solde = solde;
	}

	public Compte() {
		super();
	}

	public int getCompteID() {
		return compteId;
	}

	public void setCompteID(int compteID) {
		this.compteId = compteID;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [compteId=" + compteId + ", proprietaire=" + proprietaire + ", solde=" + solde + "]";
	}	
	
	
}
