package main.java.com.model.dto.google;

import java.io.Serializable;
import java.util.Arrays;

public class WebDetectionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PageWithMatchingImagesDTO[] pagesWithMatchingImages;
	
	public WebDetectionDTO() {
		super();
	}
	public PageWithMatchingImagesDTO[] getPagesWithMatchingImages() {
		return pagesWithMatchingImages;
	}
	public void setPagesWithMatchingImages(PageWithMatchingImagesDTO[] pagesWithMatchingImages) {
		this.pagesWithMatchingImages = pagesWithMatchingImages;
	}
	@Override
	public String toString() {
		return "WebDetectionDTO [pagesWithMatchingImages=" + Arrays.toString(pagesWithMatchingImages) + "]";
	}
	
	
}
