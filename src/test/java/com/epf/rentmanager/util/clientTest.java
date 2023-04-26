package com.epf.rentmanager.util;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epf.rentmanager.validateur.validateurClient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class clientTest {

    public static validateurClient vClient ;


    @Test
    void isLegal_should_return_true_when_age_is_over_18() {
        // Given
        Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2002,1,12));

        // Then
        assertTrue(vClient.isLegal(legalUser));
    }

    @Test
    void isLegal_should_return_false_when_age_is_under_18() {
        // Given
        Client illegaUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2020,1,12));

        // Then
        assertFalse(vClient.isLegal(illegaUser));
    }
    // verifie la taille du prenom et du nom
    @Test
    void tailleNom_should_return_true_when_nom_et_prenom_suf_2_lettres() {
        // Given
        Client legaUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.of(2001,1,12));

        // Then
        assertFalse(vClient.tailleNom(legaUser));
    }
    @Test
    void tailleNom_should_return_false_when_nom_inf_3_lettres() {
        // Given
        Client illegaUser = new Client("Jo", "Doe", "john.doe@ensta.fr", LocalDate.of(2001,1,12));

        // Then
        assertFalse(vClient.tailleNom(illegaUser));
    }
    @Test
    void tailleNom_should_return_false_when_prenom_inf_3_lettres() {
        // Given
        Client illegaUser = new Client("John", "Do", "john.doe@ensta.fr", LocalDate.of(2001,1,12));

        // Then
        assertFalse(vClient.tailleNom(illegaUser));
    }

    @Test
    void mailOK_should_return_true_when_c_est_un_nouveau_mail() {
        // Given
        Client legaUser = new Client("John", "Doe", "john.doe@epfedu.fr", LocalDate.of(2001,1,12));

        // Then
        assertTrue(vClient.mailOK(legaUser));
    }
    @Test
    void mailOK_should_return_false_when_mail_deja_utilise() {
        // Given
        Client illegaUser = new Client("John", "Doe", "m.c@gmail.com", LocalDate.of(2001,1,12));

        // Then
       assertFalse(vClient.mailOK(illegaUser));
    }

}
