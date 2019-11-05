package main.java.com.model;

import javax.persistence.Column;

import main.java.com.model.dto.google.MatchingImageDTO;

public class MatchingImage {
	
	private Long id;
    @Column(columnDefinition="TEXT")
	private String url;
	
	public MatchingImage() {
		super();
	}
	public MatchingImage(MatchingImageDTO iDTO) {
		this.url = iDTO.getUrl();
	}
	public Long getId() { 
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
