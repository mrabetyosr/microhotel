package tn.esprit.spring.microhotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.entity.Hotel;
import tn.esprit.spring.microhotel.iservice.IHotelService;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HotelController {

    private final IHotelService hotelService;


    @GetMapping("/getAllHotels")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }


    @GetMapping("/getHotelById/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }


    @PostMapping("/addHotel")
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }


    @PutMapping("/updateHotel/{id}")
    public Hotel updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(id, hotel);
    }


    @DeleteMapping("/deleteHotel/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }


    @GetMapping("/{id}/chambres")
    public List<Chambre> getChambresByHotel(@PathVariable Long id) {
        return hotelService.getChambresByHotel(id);
    }
}