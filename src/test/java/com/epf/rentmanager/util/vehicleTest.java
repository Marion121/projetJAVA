package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.validateur.validateurVehicle;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class vehicleTest {

    public static validateurVehicle vVehicle ;

    @Test
    void Nb_placesOK_should_return_true_when_nbr_place_sup_2_et_inf_10() {
        // Given
        vVehicle = new validateurVehicle();
        Vehicle legalCar = new Vehicle("Dacia",4);

        // Then
        assertTrue(vVehicle.Nb_placesOK(legalCar));
    }
    @Test
    void Nb_placesOK_should_return_true_when_nbr_place_inf_3() {
        // Given
        Vehicle illegalCar = new Vehicle("Dacia",2);

        // Then
        assertFalse(vVehicle.Nb_placesOK(illegalCar));
    }
   @Test
    void Nb_placesOK_should_return_false_when_nbr_place_sup_9() {
        // Given
        Vehicle illegalCar = new Vehicle("Dacia",11);

        // Then
        assertFalse(vVehicle.Nb_placesOK(illegalCar));
    }
}
