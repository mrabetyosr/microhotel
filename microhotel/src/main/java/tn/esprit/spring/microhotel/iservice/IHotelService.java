package tn.esprit.spring.microhotel.iservice;

import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.entity.Hotel;

import java.util.List;

public interface IHotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
    Hotel saveHotel(Hotel hotel);
    Hotel updateHotel(Long id, Hotel hotel);
    void deleteHotel(Long id);

    List<Chambre> getChambresByHotel(Long hotelId);
}
