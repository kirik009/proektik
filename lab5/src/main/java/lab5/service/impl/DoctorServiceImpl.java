package lab5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab5.entity.Doctor;
import lab5.repository.DoctorRepository;
import lab5.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository repository;

	@Override
	public Doctor read(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Doctor> read() {
		return repository.findAll();
	}

	@Override
	public void save(Doctor entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Doctor readByDocName(String name) {
		return repository.findByDocName(name);
	}
	
	
	@Override
	public Doctor readByDepartment(String department) {
		return repository.findByDepartment(department);
	}

	@Override
	public void edit(Doctor entity) {
		Doctor doctor = repository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
		doctor.setDocName(entity.getDocName());
		doctor.setDepartment(entity.getDepartment());
		repository.save(doctor);
	}	
}
