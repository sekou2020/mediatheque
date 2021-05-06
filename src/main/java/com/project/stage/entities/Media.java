package com.project.stage.entities;

import javax.persistence.*;


@Entity
@Table(name = "media")
public class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "id_media")
	private Long id;	
	
	@Column(name = "titre")
	private String title;
	
	@Column(name="resume")
	private String description;

	@Column(name = "auteur")
	private String auteur;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_media", referencedColumnName = "id_type_media")
	private TypeMedia typeMedia;
	
	//les getters/setters
	
	public Media() {
		super();
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeMedia getTypeMedia() {
		return typeMedia;
	}

	public void setTypeMedia(TypeMedia typeMedia) {
		this.typeMedia = typeMedia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((typeMedia == null) ? 0 : typeMedia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (typeMedia == null) {
			if (other.typeMedia != null)
				return false;
		} else if (!typeMedia.equals(other.typeMedia))
			return false;
		return true;
	}

	
	

	
	
	
}