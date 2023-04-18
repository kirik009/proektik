package lab5.service;

import java.util.List;

import lab5.entity.Illness;

public interface IllnessService extends Service<Illness> {

List<Illness> readByName(String name);
	
	List<Illness> readByPatient(Long id);
}
