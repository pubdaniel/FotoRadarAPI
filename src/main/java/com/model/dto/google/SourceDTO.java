package main.java.com.model.dto.google;

import java.io.Serializable;

public class SourceDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String imageUri;

	public SourceDTO() {
		super();
	}
	
	

	public SourceDTO(String imageUri) {
		super();
		this.imageUri = imageUri;
	}



	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	
	

}
