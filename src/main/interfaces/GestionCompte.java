package main.interfaces;

import java.io.FileNotFoundException;

import main.pojo.Compte;

public interface GestionCompte {
	
	int create(Compte c) throws Exception;
	void delete(int compteId);
	double crediter(int compteId, double credit) throws Exception;
	double debiter(int compteId, double debit) throws Exception;
	void consulter(int compteId) throws Exception;
	Compte loadCompte(int compteId) throws Exception;
}
