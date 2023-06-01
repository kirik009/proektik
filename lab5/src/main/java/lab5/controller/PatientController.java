package lab5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lab5.entity.Patient;
import lab5.service.PatientService;

@RestController
@RequestMapping(value = "api/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
 
	@Autowired
	private PatientService service;
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping
	@Operation(summary = "получение всех объектов с типом «пациент»")
	public ResponseEntity<List<Patient>> get() {
		List<Patient> entities = service.read();
		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/{id}")
	@Operation(summary = "получение объекта с типом «пациент» по конкретному id")
	public ResponseEntity<Patient> getById(@PathVariable long id) {
		Patient entity = service.read(id);
		if (entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/surname/{surname}")
	@Operation(summary = "получение объекта с типом «пациент» по фамилии")
	public ResponseEntity<Patient> getIllnessesByName(@PathVariable String surname) {
	Patient patient = service.readBySurname(surname);
	if (patient == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "создание объекта с типом «пациент»")
	public ResponseEntity<String> put(@RequestBody Patient entity) {
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "обновление объекта с типом «пациент»")
	public ResponseEntity<String> post(@RequestBody Patient entity) {
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@DeleteMapping("/{id}")
	@Operation(summary = "удаление объекта с типом «пациент» по id")
	public ResponseEntity<String> delete(@PathVariable long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
