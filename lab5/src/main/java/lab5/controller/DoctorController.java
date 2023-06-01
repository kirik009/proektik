package lab5.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lab5.dto.DocDTO;
import lab5.entity.Doctor;
import lab5.service.DoctorService;

@RestController
@RequestMapping(value = "api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)

public class DoctorController {
 
	@Autowired
	private DoctorService service;
	
	
	@Autowired
	private ModelMapper modelMapper;

	private DocDTO toDoctorDTO(Doctor doctor)
	{
	   DocDTO docDTO = modelMapper.map(doctor, DocDTO.class);
	   docDTO.setId(doctor.getId());
	   docDTO.setDocName(doctor.getDocName());
	   return docDTO;
	}
	
	@GetMapping()
	@Operation(summary = "Получение списка докторов")
	public ResponseEntity<?> get() {
		
		List<Doctor> entities = service.read();
		if (entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]"))	
		return new ResponseEntity<>(entities.stream().map(this::toDoctorDTO).collect(Collectors.toList()), HttpStatus.OK);	
		 else 		 
			 return new ResponseEntity<>(entities, HttpStatus.OK);	
}
	@GetMapping("/{id}/")
	@Operation(summary = "получение объекта с типом «доктор» по конкретному id")
	public ResponseEntity<?> getById(@PathVariable long id) {
		Doctor entity = service.read(id);
		if (entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]"))	
		return new ResponseEntity<>(toDoctorDTO(entity), HttpStatus.OK);	
		 else 		 
			 return new ResponseEntity<>(entity, HttpStatus.OK);	
	}
	
	@GetMapping("/docName/{docName}/")
	@Operation(summary = "получение объекта с типом «доктор» по имени")
	public ResponseEntity<?> getPatientsBySurname(@PathVariable String docName) {
		Doctor doctor = service.readByDocName(docName);
	if (doctor == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]"))	
		return new ResponseEntity<>(toDoctorDTO(doctor), HttpStatus.OK);	
		 else 		 
			 return new ResponseEntity<>(doctor, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/illPatients")
	@Operation(summary = "получение списка докторов у которых, есть больные пациенты")
	public ResponseEntity<?> getIllPatients() {
			
			List<Doctor> entities = service.read();
			if (entities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
			return new ResponseEntity<>(entities.stream().
					filter(p -> 
					p.getPatient().stream()
					.anyMatch(i -> 
					i.getIllnesses().stream()
					.allMatch(d -> d.getDateEnd()== null))).collect(Collectors.toList()), HttpStatus.OK);
		            
		}	
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "создание объекта с типом «доктор»)")
	public ResponseEntity<String> put(@RequestBody Doctor entity) {
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "обновление объекта с типом «доктор»")
	public ResponseEntity<String> post(@RequestBody Doctor entity) {
		service.save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	@Operation(summary = "удаление объекта с типом «доктор») по id")
	public ResponseEntity<String> delete(@PathVariable long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
