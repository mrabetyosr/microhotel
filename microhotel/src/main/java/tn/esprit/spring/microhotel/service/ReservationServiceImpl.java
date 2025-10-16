package tn.esprit.spring.microhotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.microhotel.entity.Reservation;
import tn.esprit.spring.microhotel.iservice.IReservationService;
import tn.esprit.spring.microhotel.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updated) {
        Reservation r = getReservationById(id);
        if (r != null) {
            r.setDateDebut(updated.getDateDebut());
            r.setDateFin(updated.getDateFin());
            r.setPrixTotal(updated.getPrixTotal());
            r.setFormule(updated.getFormule());
            r.setChambre(updated.getChambre());
            r.setClient(updated.getClient());
            return reservationRepository.save(r);
        }
        return null;
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}