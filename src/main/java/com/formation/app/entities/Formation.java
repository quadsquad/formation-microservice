package com.formation.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="formation")
public class Formation {
	
	@Id
	private String id_f;
	
	private String nom_f;
	
	private String desc_f;
	
	private String domaine_f;
	
	private String duree_f;
	
	private Object centre;

	public String getId_f() {
		return id_f;
	}

	public void setId_f(String id_f) {
		this.id_f = id_f;
	}

	public String getNom_f() {
		return nom_f;
	}

	public void setNom_f(String nom_f) {
		this.nom_f = nom_f;
	}

	public String getDesc_f() {
		return desc_f;
	}

	public void setDesc_f(String desc_f) {
		this.desc_f = desc_f;
	}

	public String getDomaine_f() {
		return domaine_f;
	}

	public void setDomaine_f(String domaine_f) {
		this.domaine_f = domaine_f;
	}

	public String getDuree_f() {
		return duree_f;
	}

	public void setDuree_f(String duree_f) {
		this.duree_f = duree_f;
	}

	public Object getCentre() {
		return centre;
	}

	public void setCentre(Object centre) {
		this.centre = centre;
	}

}