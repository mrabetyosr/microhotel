package tn.esprit.spring.microhotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.iservice.IChambreService;
import tn.esprit.spring.microhotel.repository.ChambreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre getChambreById(Long id) {
        return chambreRepository.findById(id).orElse(null);
    }

    @Override
    public Chambre saveChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(Long id, Chambre updated) {
        Chambre c = getChambreById(id);
        if (c != null) {
            c.setNumero(updated.getNumero());
            c.setType(updated.getType());
            c.setPrixParNuit(updated.getPrixParNuit());
            c.setDisponible(updated.isDisponible());
            c.setHotel(updated.getHotel());
            return chambreRepository.save(c);
        }
        return null;
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }
}
