package com.formation.app.services;

import org.springframework.http.ResponseEntity;

import com.formation.app.entities.Formation;

public interface FormationService {
	
	public ResponseEntity<?> getAllFormations();
	public ResponseEntity<?> getSingleFormation(String id_form);
	public ResponseEntity<?> addFormation(Formation formation);
	public ResponseEntity<?> updateFormation(Formation formation, String id_form);
	public ResponseEntity<?> deleteFormation(String id_form);
	public ResponseEntity<?> deleteCFF(String id_form);
	public ResponseEntity<?> affectCentreToForm(String id_form, String id_centre);
	public ResponseEntity<?> deleteCentreFromForm(String id_form);

}
