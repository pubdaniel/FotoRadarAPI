package main.java.com.model.dto.google;

import java.io.Serializable;
import java.util.Arrays;

public class ResponsesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebDetectionDTO[] responses;

	public ResponsesDTO() {
		super();
	}

	public WebDetectionDTO[] getResponses() {
		return responses;
	}

	public void setResponses(WebDetectionDTO[] responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "ResponsesDTO [responses=" + Arrays.toString(responses) + "]";
	}
	
	
	
}
