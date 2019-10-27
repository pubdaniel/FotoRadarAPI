package main.java.com.model.dto.google;

import java.io.Serializable;

public class FeatureDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Integer maxResults;
	public FeatureDTO() {
		super();
		this.type = "WEB_DETECTION";
		this.maxResults = 1000;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
	
	
}
