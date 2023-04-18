package lab5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab5.entity.Illness;
import lab5.entity.Patient;
import lab5.repository.IllnessRepository;
import lab5.repository.PatientRepository;
import lab5.service.IllnessService;

@Service
public class IllnessServiceImpl implements IllnessService {
	@Autowired
	private IllnessRepository iRepository;
	@Autowired
	private PatientRepository pRepository;

	@Override
	public Illness read(Long id) {
		return iRepository.findById(id).get();
	}

	@Override
	public List<Illness> read() {
		return iRepository.findAll();
	}

	@Override
	public void save(Illness entity) {
		Patient patient = entity.getPatient();
		Long id = patient.getId();
		patient = pRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		entity.setPatient(patient);
		patient.getIllnesses().add(entity);
		pRepository.save(patient);
	}

	@Override
	public void delete(Long id) {
		Illness illness = iRepository.findById(id).get();
		Patient patient = illness.getPatient();
		patient.getIllnesses().remove(illness);
		pRepository.save(patient);
	}

	@Override
	public List<Illness> readByPatient(Long patientId) {
		return iRepository.findByPatient(patientId);
	}

	@Override
	public List<Illness> readByName(String name) {
		return iRepository.findByName(name);
	}

	@Override
	public void edit(Illness entity) {
		Patient patient = entity.getPatient();
		Long pId = patient.getId();
		Long iId = entity.getId();
		patient = pRepository.findById(pId).orElseThrow(IllegalArgumentException::new);
		Illness illness = patient.getIllnesses().stream().filter(s -> iId.equals(s.getId())).findAny()
				.orElseThrow(IllegalArgumentException::new);
		illness.setPatient(patient);
		illness.setName(entity.getName());
		illness.setDateStart(entity.getDateStart());
		illness.setDateEnd(entity.getDateEnd());
		patient.getIllnesses().add(illness);
		iRepository.save(illness);
	}
}
