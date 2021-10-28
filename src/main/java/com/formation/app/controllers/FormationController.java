package com.formation.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.app.entities.Formation;
import com.formation.app.services.FormationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FormationController {
	
	@Autowired
	public FormationService form_service;
	
	@GetMapping("/all-formations")
	public ResponseEntity<?> getAllFormations() {
		return form_service.getAllFormations();
	}
	
	@GetMapping("/formation/{id_form}")
	public ResponseEntity<?> getFormation(@PathVariable String id_form) {
		return form_service.getSingleFormation(id_form);
	}
	
	@PostMapping("/add-formation")
	public ResponseEntity<?> addFormation(@RequestBody Formation formation) {
		return form_service.addFormation(formation);
	}
	
	@PutMapping("/update-formation/{id_form}")
	public ResponseEntity<?> updateFormation(@RequestBody Formation formation, @PathVariable String id_form) {
		return form_service.updateFormation(formation, id_form);
	}
	
	@DeleteMapping("/delete-formation/{id_form}")
	public ResponseEntity<?> deleteFormation(@PathVariable String id_form) {
		return form_service.deleteFormation(id_form);
	}
	
	@GetMapping("/deleteCentreFromForm/{id_form}")
	public ResponseEntity<?> deleteCFF(@PathVariable String id_form) {
		return form_service.deleteCFF(id_form);
	}
	
	@PutMapping("/affectCentreToForm/{id_form}/{id_centre}")
	public ResponseEntity<?> affectCentreToForm(@PathVariable String id_form, @PathVariable String id_centre) {
		return form_service.affectCentreToForm(id_form, id_centre);
	}
	
	@PutMapping("/deleteCentreFromForm/{id_form}")
	public ResponseEntity<?> deleteCentreFromForm(@PathVariable String id_form) {
		return form_service.deleteCentreFromForm(id_form);
	}

}
