package lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab5.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {	
	Doctor findByDocName(String name);
	
	Doctor findByDepartment(String name);
}
