package tn.esprit.spring.microhotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.microhotel.entity.*;
import tn.esprit.spring.microhotel.iservice.IReservationService;
import tn.esprit.spring.microhotel.repository.ChambreRepository;
import tn.esprit.spring.microhotel.repository.ClientRepository;
import tn.esprit.spring.microhotel.repository.ReservationRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ChambreRepository chambreRepository;
    private final ClientRepository clientRepository;

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
        // Récupérer la chambre réelle depuis la base
        Chambre chambre = chambreRepository.findById(reservation.getChambre().getId())
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
        reservation.setChambre(chambre);

        // Récupérer le client réel depuis la base
        Client client = clientRepository.findById(reservation.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        reservation.setClient(client);

        // Calcul du nombre de nuits
        long nbNuits = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());

        // Calcul du prix total
        double prixParNuit = chambre.getPrixParNuit();
        double prixTotal = prixParNuit * nbNuits;
        if (reservation.getFormule() == Formule.DEMI_PENSION) {
            prixTotal += 20 * nbNuits;
        } else if (reservation.getFormule() == Formule.PENSION_COMPLETE) {
            prixTotal += 40 * nbNuits;
        }

        reservation.setPrixTotal(prixTotal);

        return reservationRepository.save(reservation);
    }


    @Override
    public Reservation updateReservation(Long id, Reservation updated) {
        Reservation r = getReservationById(id);
        if (r != null) {
            if (updated.getDateDebut() != null) r.setDateDebut(updated.getDateDebut());
            if (updated.getDateFin() != null) r.setDateFin(updated.getDateFin());
            if (updated.getFormule() != null) r.setFormule(updated.getFormule());
            if (updated.getStatus() != null) r.setStatus(updated.getStatus());

            // Si la chambre est changée
            if (updated.getChambre() != null) {
                Chambre chambre = chambreRepository.findById(updated.getChambre().getId())
                        .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
                r.setChambre(chambre);
            }

            // Si le client est changé
            if (updated.getClient() != null) {
                Client client = clientRepository.findById(updated.getClient().getId())
                        .orElseThrow(() -> new RuntimeException("Client non trouvé"));
                r.setClient(client);
            }

            // Recalcul du prix total si les dates ou formule ont changé
            long nbNuits = ChronoUnit.DAYS.between(r.getDateDebut(), r.getDateFin());
            double prixTotal = r.getChambre().getPrixParNuit() * nbNuits;
            if (r.getFormule() == Formule.DEMI_PENSION) prixTotal += 20 * nbNuits;
            else if (r.getFormule() == Formule.PENSION_COMPLETE) prixTotal += 40 * nbNuits;
            r.setPrixTotal(prixTotal);

            // 🔁 Gestion automatique de la disponibilité de la chambre
            if (r.getStatus() == StatusReservation.CONFIRMEE) {
                r.getChambre().setDisponible(false);
            } else if (r.getStatus() == StatusReservation.ANNULEE
                    || r.getStatus() == StatusReservation.EXPIREE) {
                r.getChambre().setDisponible(true);
            }

            chambreRepository.save(r.getChambre());
            return reservationRepository.save(r);
        }
        return null;
    }


    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}