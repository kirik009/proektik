package lab5.service;

import lab5.entity.Doctor;

public interface DoctorService extends Service<Doctor> {	
	Doctor readByDocName(String name);
	
	Doctor readByDepartment(String depatrment);
}
