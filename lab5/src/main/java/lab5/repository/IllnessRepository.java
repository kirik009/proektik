package lab5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab5.entity.Illness;

@Repository
public interface IllnessRepository extends JpaRepository<Illness, Long> {	
	List<Illness> findByName(String name);
	
	List<Illness> findByPatient(Long id);
}
