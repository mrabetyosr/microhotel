package tn.esprit.spring.microhotel.iservice;

import tn.esprit.spring.microhotel.entity.Chambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> getAllChambres();
    Chambre getChambreById(Long id);
    Chambre saveChambre(Chambre chambre);
    Chambre updateChambre(Long id, Chambre chambre);
    void deleteChambre(Long id);
    List<Chambre> filterChambres(String type, Double prixMax, Boolean disponible);

}
