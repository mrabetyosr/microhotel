package tn.esprit.spring.microhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.microhotel.entity.Chambre;
import tn.esprit.spring.microhotel.entity.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    @Query("SELECT c FROM Chambre c " +
            "WHERE (:type IS NULL OR c.type = :type) " +
            "AND (:prixMax IS NULL OR c.prixParNuit <= :prixMax) " +
            "AND (:disponible IS NULL OR c.disponible = :disponible)")
    List<Chambre> filterChambres(@Param("type") TypeChambre type,
                                 @Param("prixMax") Double prixMax,
                                 @Param("disponible") Boolean disponible);


}
