package tn.esprit.spring.microhotel.iservice;

import tn.esprit.spring.microhotel.entity.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation saveReservation(Reservation reservation);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
}
