package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

    @RunWith(MockitoJUnitRunner.class)
    public class ReservationServicesTest {
        @InjectMocks
        private ReservationService reservationService;

        @Mock
        private ReservationDao reservationDao ;

        @Test
        void findAll_should_fail_when_dao_throws_exception() throws DaoException {
            // When
            when(this.reservationDao.findAll()).thenThrow(DaoException.class);

            // Then
            assertThrows(ServiceException.class, () -> this.reservationService.findAll());
        }
    }

