package main.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import main.exceptions.CompteIdNotFoundException;
import main.exceptions.NegativeCreditException;
import main.exceptions.NotEnoughMoneyException;
import main.interfaces.GestionCompte;
import main.pojo.Compte;

public class GestionCompteImpl implements GestionCompte{

	private static Properties prop;
	private FileInputStream propFile;
	
	@Override
	public int create(Compte c) throws Exception {
		prop = new Properties();
		propFile = new FileInputStream("src/compte.properties");
		prop.load(propFile);
		
		//Get compteId from file
		String[] ids = prop.getProperty("id").split(",");
		
		c.setCompteID(ids.length+1);
		
		String idsString = ids.toString() + "," + c.getCompteID();
		System.out.println(ids);
		
		prop.setProperty("id", ids.toString());
		prop.setProperty("proprietaire", String.valueOf(c.getProprietaire()));
		prop.setProperty("solde", String.valueOf(c.getSolde()));
		
		return 0;
	}

	@Override
	public void delete(int compteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double crediter(int compteId, double credit) throws NegativeCreditException, Exception {
		
		//Test if credit is negative
		if(credit < 0) {
			throw new NegativeCreditException(1, "Le credit doit etre positif");
		}		
		
		//Load the compte file (DB)
		prop = new Properties();
		propFile = new FileInputStream("src/compte.properties");
		prop.load(propFile);
		
		Compte c = new Compte();
		c = loadCompte(compteId);
		
		double nouveauSolde = c.getSolde() + credit;
		prop.setProperty("solde", String.valueOf(nouveauSolde));
		
		return nouveauSolde;
	}

	@Override
	public double debiter(int compteId, double debit) throws NegativeCreditException, Exception {
		
		//Test if credit is negative
		if(debit < 0) {
			throw new NegativeCreditException(2, "Le debit doit etre positif");
		}	
		Compte c = new Compte();
		
		c = loadCompte(compteId);
		
		if(c.getSolde() - debit < 0) {
			throw new NotEnoughMoneyException(1, "Debit : " + debit + " is more than solde : " + c.getSolde());
		}
		
		c.setSolde(c.getSolde() - debit);
		
		Compte updatedCompte = updateCompte(compteId, c);
		
		updatedCompte.toString();
		
		return c.getSolde();
	}

	@Override
	public void consulter(int compteId) throws Exception, CompteIdNotFoundException {
		Compte c = new Compte();
		c = loadCompte(compteId);
		
		System.out.println("ID : " + c.getCompteID());
		System.out.println("Proprio : " + c.getProprietaire());
		System.out.println("Solde : " + c.getSolde());		
	}
	
	public Compte loadCompte(int compteId) throws Exception {		
		//Load the compte file (DB)
		prop = new Properties();
		propFile = new FileInputStream("src/compte.properties");
		prop.load(propFile);
		
		//Get compteId from file
		String[] ids = prop.getProperty("id").split(",");
		if(compteId > ids.length) {
			throw new CompteIdNotFoundException(1,"compteId doesn't exists");
		}
		int cId = Integer.parseInt(ids[compteId-1]);
		
		//Test if compteId exists in the file
		if(cId != compteId) {
			throw new CompteIdNotFoundException(1, "Le compte d'ID : " + compteId + " n'existe pas !");
		}
		
		String[] proprios = prop.getProperty("proprietaire").split(",");
		String proprio = proprios[compteId-1];
		
		String[] soldes = prop.getProperty("solde").split(",");
		double solde = Double.parseDouble(soldes[compteId-1]);
		
		Compte c = new Compte(cId, proprio, solde);
		
		return c;
	}
	
	public Compte updateCompte(int compteId, Compte c) throws Exception{
		//Load the compte file (DB)
		prop = new Properties();
		propFile = new FileInputStream("src/compte.properties");
		prop.load(propFile);		
		
		//Write updated values
		prop.setProperty("id", String.valueOf(c.getCompteID()));
		prop.setProperty("proprietaire", String.valueOf(c.getProprietaire()));
		prop.setProperty("solde", String.valueOf(c.getSolde()));
		
		Compte updatedCompte = new Compte();
		
		//returns updated Compte
		updatedCompte.setCompteID(Integer.parseInt(prop.getProperty("id")));
		updatedCompte.setProprietaire(prop.getProperty("proprietaire"));
		updatedCompte.setSolde(Double.parseDouble(prop.getProperty("solde")));
		
		return updatedCompte;
	}
}
