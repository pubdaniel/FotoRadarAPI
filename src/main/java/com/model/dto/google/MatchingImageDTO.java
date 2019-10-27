package main.java.com.model.dto.google;

import java.io.Serializable;

public class MatchingImageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;

	public MatchingImageDTO(String url) {
		super();
		this.url = url;
	}

	public MatchingImageDTO() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MatchingImageDTO [url=" + url + "]";
	}
	
	
	
}
