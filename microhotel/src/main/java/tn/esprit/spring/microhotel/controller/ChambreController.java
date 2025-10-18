package tn.esprit.spring.microhotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.iservice.IChambreService;

import java.util.List;

@RestController
@RequestMapping("/api/chambres")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChambreController {

    private final IChambreService chambreService;

    @GetMapping("/getAllChambres")
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @GetMapping("/getChambreById/{id}")
    public Chambre getChambreById(@PathVariable Long id) {
        return chambreService.getChambreById(id);
    }

    @PostMapping("/addChambre")
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreService.saveChambre(chambre);
    }

    @PutMapping("/updateChambre/{id}")
    public Chambre updateChambre(@PathVariable Long id, @RequestBody Chambre chambre) {
        return chambreService.updateChambre(id, chambre);
    }

    @DeleteMapping("/deleteChambre/{id}")
    public void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }

    @GetMapping("/filter")
    public List<Chambre> filterChambres(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double prixMax,
            @RequestParam(required = false) Boolean disponible) {
        return chambreService.filterChambres(type, prixMax, disponible);
    }


}