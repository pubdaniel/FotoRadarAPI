package main.java.com.model.dto.google;

import java.io.Serializable;

public class ImageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SourceDTO source;

	public ImageDTO() {
		super();
	}

	
	
	public ImageDTO(SourceDTO source) {
		this.source = source;
	}



	public SourceDTO getSource() {
		return source;
	}

	public void setSource(SourceDTO source) {
		this.source = source;
	}
	
	
}
