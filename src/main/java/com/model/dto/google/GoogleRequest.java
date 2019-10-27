package main.java.com.model.dto.google;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import com.google.gson.Gson;

public class GoogleRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FeatureDTO[] features;
	private ImageDTO image;
	
	
	public GoogleRequest() {
		super();
	}

	public GoogleRequest(String imageURL) {

		SourceDTO source = new SourceDTO(imageURL);
		image = new ImageDTO(source);
		
		FeatureDTO feature = new FeatureDTO();
		features = new FeatureDTO[1];
		features[0] = feature;
		
		
	}
	
	public static String createRequestJson(String imageURL) {
		
		GoogleRequest request = new GoogleRequest(imageURL);
		
		Map<String, Object[]> requests = new HashMap<String, Object[]>();
		Object[] objArray = {request};
		requests.put("requests", objArray);

		Gson gson = new Gson();
		
		return gson.toJson(requests);
	}

		
	
	public static void main(String[] args) {
		String imagePath = "https://www.guru99.com/array-of-objects.html";
		
		System.out.println(GoogleRequest.createRequestJson(imagePath));
	}
	
}






