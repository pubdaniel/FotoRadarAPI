package main.java.com.model.dto.google;

import java.io.Serializable;

public class RequestDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FeatureDTO[] features;
	private ImageDTO image;
	
	public RequestDTO() {
		super();
	}
	public FeatureDTO[] getFeatures() {
		return features;
	}
	public void setFeatures(FeatureDTO[] features) {
		this.features = features;
	}
	public ImageDTO getImage() {
		return image;
	}
	public void setImage(ImageDTO image) {
		this.image = image;
	}
	
	
}
