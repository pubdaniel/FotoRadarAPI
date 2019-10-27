package main.java.com.model.dto.google;

import java.io.Serializable;
import java.util.Arrays;

public class ObjectResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResponsesDTO[] responses;

	public ObjectResponseDTO() {
		super();
	}

	public ResponsesDTO[] getResponses() {
		return responses;
	}

	public void setResponses(ResponsesDTO[] responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "ObjectResponseDTO [responses=" + Arrays.toString(responses) + "]";
	}
	
	
			
}
