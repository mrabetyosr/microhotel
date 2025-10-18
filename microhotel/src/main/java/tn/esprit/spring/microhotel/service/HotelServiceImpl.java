package tn.esprit.spring.microhotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.entity.Hotel;
import tn.esprit.spring.microhotel.iservice.IHotelService;
import tn.esprit.spring.microhotel.repository.HotelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long id, Hotel updated) {
        Hotel h = getHotelById(id);
        if (h != null) {
            h.setNom(updated.getNom());
            h.setAdresse(updated.getAdresse());
            h.setVille(updated.getVille());
            h.setPays(updated.getPays());
            h.setNbEtoiles(updated.getNbEtoiles());
            h.setTelephone(updated.getTelephone());
            return hotelRepository.save(h);
        }
        return null;
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getChambresByHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if (hotel != null) {
            return hotel.getChambres();
        } else {
            return List.of();
        }
    }


}