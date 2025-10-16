package tn.esprit.spring.microhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.microhotel.entity.Chambre;
@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
}
