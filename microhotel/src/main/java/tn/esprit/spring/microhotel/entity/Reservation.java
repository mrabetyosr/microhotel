package tn.esprit.spring.microhotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prixTotal;
    @Enumerated(EnumType.STRING)
    private Formule formule; // CHAMBRE_SEULE, DEMI_PENSION, PENSION_COMPLETE

    @ManyToOne
    @JoinColumn(name = "chambre_id")
    @JsonIgnoreProperties("reservations")
    private Chambre chambre;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("reservations")
    private Client client;

}
