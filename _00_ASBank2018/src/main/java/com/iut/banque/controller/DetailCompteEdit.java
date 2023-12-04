package com.iut.banque.controller;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.modele.CompteAvecDecouvert;

public class DetailCompteEdit extends DetailCompte {

	private static final long serialVersionUID = 1L;
	private String decouvertAutorise;

	/**
	 * Constructeur sans argument de DetailCompteEdit
	 */
	public DetailCompteEdit() {
		super();
		System.out.println("======================================");
		System.out.println("Dans le constructeur DetailCompteEdit");
	}

	/**
	 * @return the decouvertAutorise
	 */
	public String getDecouvertAutorise() {
		return decouvertAutorise;
	}

	/**
	 * @param decouvertAutorise the decouvertAutorise to set
	 */
	public void setDecouvertAutorise(String decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	/**
	 * Permet le changement de découvert d'un compte avec découvert.
	 *
	 * @return le status de l'action
	 */
	public String changementDecouvert() {
		// Vérifie si le compte est une instance de CompteAvecDecouvert
		if (!(getCompte() instanceof CompteAvecDecouvert)) {
			return "ERROR";
		}

		try {
			// Essaye de convertir la chaîne decouvertAutorise en double
			Double decouvert = Double.parseDouble(decouvertAutorise);

			// Appelle la méthode changeDecouvert du service banque
			banque.changeDecouvert((CompteAvecDecouvert) getCompte(), decouvert);

			return "SUCCESS";
		} catch (NumberFormatException nfe) {
			// Gère l'exception si la conversion en double échoue
			nfe.printStackTrace();
			return "ERROR";
		} catch (IllegalFormatException e) {
			// Gère l'exception si le format du decouvertAutorise est incorrect
			return "NEGATIVEOVERDRAFT";
		} catch (IllegalOperationException e) {
			// Gère l'exception si l'opération est illégale
			return "INCOMPATIBLEOVERDRAFT";
		}
	}
}
