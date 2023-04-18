package lab5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab5.entity.Doctor;
import lab5.entity.Patient;
import lab5.repository.DoctorRepository;
import lab5.repository.PatientRepository;
import lab5.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository pRepository;
	
	@Autowired
	private DoctorRepository dRepository;

	@Override
	public Patient read(Long id) {
		return pRepository.findById(id).get();
	}

	@Override
	public List<Patient> read() {
		return pRepository.findAll();
	}

	@Override
	public void save(Patient entity) {
		Doctor doctor = entity.getDoctor();
		Long id = doctor.getId();
		doctor = dRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		entity.setDoctor(doctor);
		doctor.getPatient().add(entity);
		dRepository.save(doctor);
	}

	@Override
	public void delete(Long id) {
		Patient patient = pRepository.findById(id).get();
		Doctor doctor = patient.getDoctor();
		doctor.getPatient().remove(patient);
		dRepository.save(doctor);
	}


	@Override
	public Patient readBySurname(String surname) {
		return pRepository.findBySurname(surname);
	}
	
	@Override
	public List<Patient> readByDoctor(Long id) {
		return pRepository.findByDoctor(id);
	}

	@Override
	public void edit(Patient entity) {
		Doctor doctor = entity.getDoctor();
		Long dId = doctor.getId();
		Long pId = entity.getId();
		doctor = dRepository.findById(dId).orElseThrow(IllegalArgumentException::new);
		Patient patient = doctor.getPatient().stream().filter(s -> pId.equals(s.getId())).findAny()
				.orElseThrow(IllegalArgumentException::new);
		patient.setDoctor(doctor);
		patient.setPatName(entity.getPatName());
		patient.setSurname(entity.getSurname());
		patient.setPhoneNumber(entity.getPhoneNumber());
		doctor.getPatient().add(patient);
		pRepository.save(patient);
	}
}