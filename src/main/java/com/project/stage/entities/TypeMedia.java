package com.project.stage.entities;

import javax.persistence.*;


@Entity
@Table(name = "type_media")
public class TypeMedia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_type_media")
	private long idTypeMedia;
	
	
	@Column(name = "type")
	private String typeMedia;


	public long getIdTypeMedia() {
		return idTypeMedia;
	}


	public void setIdTypeMedia(long idTypeMedia) {
		this.idTypeMedia = idTypeMedia;
	}


	public String getTypeMedia() {
		return typeMedia;
	}


	public void setTypeMedia(String typeMedia) {
		this.typeMedia = typeMedia;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTypeMedia ^ (idTypeMedia >>> 32));
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
		TypeMedia other = (TypeMedia) obj;
		if (idTypeMedia != other.idTypeMedia)
			return false;
		if (typeMedia == null) {
			if (other.typeMedia != null)
				return false;
		} else if (!typeMedia.equals(other.typeMedia))
			return false;
		return true;
	}

	
	
	
}