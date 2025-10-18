package tn.esprit.spring.microhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.microhotel.entity.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h " +
            "WHERE (:ville IS NULL OR h.ville = :ville) " +
            "AND (:pays IS NULL OR h.pays = :pays) " +
            "AND (:nbEtoiles IS NULL OR h.nbEtoiles = :nbEtoiles)")
    List<Hotel> searchHotels(@Param("ville") String ville,
                             @Param("pays") String pays,
                             @Param("nbEtoiles") Integer nbEtoiles);

}
