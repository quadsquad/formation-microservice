package com.formation.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.formation.app.entities.Centre;
import com.formation.app.entities.Formation;
//import com.formation.app.repositories.CentreRepository;
import com.formation.app.repositories.FormationRepository;

@Service
public class FormationServiceImpl implements FormationService {
	
	@Autowired
	private FormationRepository form_rep;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	public ResponseEntity<?> getAllFormations() {
		List<Formation> forms = form_rep.findAll();
		if (forms.size() > 0) {
			return new ResponseEntity<List<Formation>>(forms, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Formation Currently Available!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> getSingleFormation(String id_form) {
		Formation formation = form_rep.findById(id_form).get();
		if (form_rep.findById(id_form).isPresent()) {
			return new ResponseEntity<Formation>(formation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error finding formation", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> addFormation(Formation formation) {
		try {
			formation.setCentre(new ArrayList<Object>());
			List<Formation> formations = form_rep.findAll();
			List<String> list_nomf = new ArrayList<String>();
			List<String> list_descf = new ArrayList<String>();
			for (int i = 0; i<formations.size(); i++) {
				list_nomf.add(formations.get(i).getNom_f());
				list_descf.add(formations.get(i).getDesc_f());
			}
			if (list_nomf.contains(formation.getNom_f()) || list_descf.contains(formation.getDesc_f())) {
				return new ResponseEntity<>("Formation already exists", HttpStatus.NOT_ACCEPTABLE);
			} else {
				form_rep.save(formation);
			}
			return new ResponseEntity<Formation>(formation, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> updateFormation(Formation formation, String id_form) {
		try {
			List<Formation> formations = form_rep.findAll();
			List<String> list_nomf = new ArrayList<String>();
			List<String> list_descf = new ArrayList<String>();
			for (int i = 0; i<formations.size(); i++) {
				list_nomf.add(formations.get(i).getNom_f());
				list_descf.add(formations.get(i).getDesc_f());
			}
			if (form_rep.findById(id_form).isPresent()) {
				Formation existedForm = form_rep.findById(id_form).get();
				if (formation.getNom_f() == null) {
					existedForm.setNom_f(existedForm.getNom_f());
				} else {
					if (list_nomf.contains(formation.getNom_f())) {
						return new ResponseEntity<>("Formation already exists", HttpStatus.NOT_ACCEPTABLE);
					} else {
						existedForm.setNom_f(formation.getNom_f());
					}
				}
				if (formation.getDesc_f() == null) {
					existedForm.setDesc_f(existedForm.getDesc_f());
				} else {
					if (list_descf.contains(formation.getDesc_f())) {
						return new ResponseEntity<>("Formation already exists", HttpStatus.NOT_ACCEPTABLE);
					} else {
						existedForm.setDesc_f(formation.getDesc_f());
					}
				}
				
				if (formation.getDomaine_f() == null) {
					existedForm.setDomaine_f(existedForm.getDomaine_f());
				} else {
					if (list_descf.contains(formation.getDomaine_f())) {
						return new ResponseEntity<>("Formation already exists", HttpStatus.NOT_ACCEPTABLE);
					} else {
						existedForm.setDomaine_f(formation.getDomaine_f());
					}
				}
				if (formation.getDuree_f() == null) {
					existedForm.setDuree_f(existedForm.getDuree_f());
				} else {
					if (list_descf.contains(formation.getDuree_f())) {
						return new ResponseEntity<>("Formation already exists", HttpStatus.NOT_ACCEPTABLE);
					} else {
						existedForm.setDuree_f(formation.getDuree_f());
					}
				}
				form_rep.save(existedForm);
			} else {
				return new ResponseEntity<>("Formation not found!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Formation>(form_rep.findById(id_form).get(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//delete formation
	public ResponseEntity<?> deleteFormation(String id_form) {
		try {
			if (form_rep.findById(id_form).isPresent()) {
				form_rep.delete(form_rep.findById(id_form).get());
				return new ResponseEntity<>("Formation Deleted Successfully!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Formation Not Found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> deleteCFF(String id_form) {
		if (form_rep.findById(id_form).isPresent()) {
			Formation formation = form_rep.findById(id_form).get();
			formation.setCentre(null);
			form_rep.save(formation);
			return new ResponseEntity<>("Center deleted from formation", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Could not delete center", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> affectCentreToForm(String id_form, String id_centre) {
		try {
			if (form_rep.findById(id_form).isPresent() /*&& centre_rep.findById(id_centre).isPresent()*/) {
				Formation existedForm = form_rep.findById(id_form).get();
				Object existedCentre = restTemplateBuilder.build().getForObject("https://centre-ms-qs.herokuapp.com/get-centre/"+id_centre, Object.class);
				//Centre existedCentre = centre_rep.findById(id_centre).get();
				existedForm.setCentre(existedCentre);
				form_rep.save(existedForm);
			} else {
				return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("Formation Updated Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> deleteCentreFromForm(String id_form) {
		try {
			if (form_rep.findById(id_form).isPresent() /*&& centre_rep.findById(id_centre).isPresent()*/) {
				Formation existForm = form_rep.findById(id_form).get();
				existForm.setCentre(null);
				form_rep.save(existForm);
			} else {
				return new ResponseEntity<>("Centre not found!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("Formation Updated Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
