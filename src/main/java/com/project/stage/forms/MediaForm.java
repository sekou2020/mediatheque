package com.project.stage.forms;

public class MediaForm {
	private Long idMedia;
	private Long idTypeMedia; 
	private String title;
	private String description;
	
	public Long getIdTypeMedia() {
		return idTypeMedia;
	}
	
	public Long getIdMedia() {
		return idMedia;
	}

	public void setIdMedia(Long idMedia) {
		this.idMedia = idMedia;
	}

	public void setIdTypeMedia(Long idTypeMedia) {
		this.idTypeMedia = idTypeMedia;
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
	
	
}


