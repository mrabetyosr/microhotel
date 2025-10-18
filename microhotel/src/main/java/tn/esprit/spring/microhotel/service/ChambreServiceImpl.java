package tn.esprit.spring.microhotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.entity.Hotel;
import tn.esprit.spring.microhotel.iservice.IChambreService;
import tn.esprit.spring.microhotel.repository.ChambreRepository;
import tn.esprit.spring.microhotel.repository.HotelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;
    private final HotelRepository hotelRepository;

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
        Chambre savedChambre = chambreRepository.save(chambre);

        if (savedChambre.getHotel() != null && savedChambre.getHotel().getId() != null) {
            Hotel hotel = hotelRepository.findById(savedChambre.getHotel().getId()).orElse(null);
            savedChambre.setHotel(hotel);
        }

        return savedChambre;
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
