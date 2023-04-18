package lab5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab5.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {	
	
	Patient findBySurname(String name);
	
	List<Patient> findByDoctor(Long id);
}
