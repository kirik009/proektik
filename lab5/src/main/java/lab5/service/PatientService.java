package lab5.service;

import java.util.List;

import lab5.entity.Patient;

public interface PatientService extends Service<Patient> {	
	Patient readBySurname(String name);
	
	List<Patient> readByDoctor(Long id);
	
}
