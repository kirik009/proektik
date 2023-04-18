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

import lab5.entity.Illness;
import lab5.service.IllnessService;

	@RestController
	@RequestMapping(value = "api/illness", produces = MediaType.APPLICATION_JSON_VALUE)
	public class IllnessController {
	 
		@Autowired
		private IllnessService service;
		
		@PreAuthorize("hasAuthority('ROLE_USER')")
		@GetMapping
		public ResponseEntity<List<Illness>> get() {
			List<Illness> entities = service.read();
			if (entities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
		
		@PreAuthorize("hasAuthority('ROLE_USER')")
		@GetMapping("/{id}")
		public ResponseEntity<Illness> getById(@PathVariable long id) {
			Illness entity = service.read(id);
			if (entity == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(entity, HttpStatus.OK);
		}
		
		
//		@GetMapping("/patient/{id}")
//		public ResponseEntity<List<Illness>> getIllnessesByPatient(@PathVariable long id) {
//		List<Illness> illnesses = service.readByPatient(id);
//		if (illnesses.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(illnesses, HttpStatus.OK);
//		}
		
		@PreAuthorize("hasAuthority('ROLE_USER')")
		@GetMapping("/name/{name}")
		public ResponseEntity<List<Illness>> getIllnessesByName(@PathVariable String name) {
			List<Illness> illnesses = service.readByName(name);
			if (illnesses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(illnesses, HttpStatus.OK);
		}
		
		
		@PreAuthorize("hasAuthority('ROLE_USER')")
		@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> put(@RequestBody Illness entity) {
			service.save(entity);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		@PreAuthorize("hasAuthority('ROLE_USER')")
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> post(@RequestBody Illness entity) {
			service.save(entity);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@PreAuthorize("hasAuthority('ROLE_USER')")
		@DeleteMapping("/{id}")
		public ResponseEntity<String> delete(@PathVariable long id) {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

		
	