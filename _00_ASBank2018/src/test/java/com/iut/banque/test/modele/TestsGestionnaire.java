package com.iut.banque.test.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.iut.banque.modele.Gestionnaire;
import org.junit.Test;

import com.iut.banque.exceptions.IllegalFormatException;

public class TestsGestionnaire {

    @Test
    public void testConstructeurAvecParametres() {
        try {
            Gestionnaire gestionnaire = new Gestionnaire("Nom", "Prenom", "Adresse", true, "gestionnaireId", "gestionnairePwd");

            assertEquals("Nom", gestionnaire.getNom());
            assertEquals("Prenom", gestionnaire.getPrenom());
            assertEquals("Adresse", gestionnaire.getAdresse());
            assertEquals(true, gestionnaire.isMale());
            assertEquals("gestionnaireId", gestionnaire.getUserId());
            assertEquals("gestionnairePwd", gestionnaire.getUserPwd());

        } catch (IllegalFormatException e) {
            fail("Une exception ne devrait pas être levée ici.");
        }
    }


    @Test
    public void testToString() {
        try {
            Gestionnaire gestionnaire = new Gestionnaire("Nom", "Prenom", "Adresse", true, "gestionnaireId", "gestionnairePwd");
            String expectedToString = "Gestionnaire [nom=Nom, prenom=Prenom, adresse=Adresse, male=true, userId=gestionnaireId, userPwd=gestionnairePwd]";
            assertEquals(expectedToString, gestionnaire.toString());
        } catch (IllegalFormatException e) {
            fail("Une exception ne devrait pas être levée ici.");
        }
    }
}
