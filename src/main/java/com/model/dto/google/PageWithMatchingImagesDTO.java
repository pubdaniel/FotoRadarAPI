package main.java.com.model.dto.google;

import java.io.Serializable;
import java.util.Arrays;

public class PageWithMatchingImagesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pageTitle;
	private MatchingImageDTO[] fullMatchingImagesDTO;
	private MatchingImageDTO[] partialMatchingImagesDTO;
	private String url;
	
	
	public PageWithMatchingImagesDTO() {
		super();
	}


	public String getPageTitle() {
		return pageTitle;
	}


	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}


	public MatchingImageDTO[] getFullMatchingImagesDTO() {
		return fullMatchingImagesDTO;
	}


	public void setFullMatchingImagesDTO(MatchingImageDTO[] fullMatchingImagesDTO) {
		this.fullMatchingImagesDTO = fullMatchingImagesDTO;
	}


	public MatchingImageDTO[] getPartialMatchingImagesDTO() {
		return partialMatchingImagesDTO;
	}


	public void setPartialMatchingImagesDTO(MatchingImageDTO[] partialMatchingImagesDTO) {
		this.partialMatchingImagesDTO = partialMatchingImagesDTO;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "PageWithMatchingImagesDTO [pageTitle=" + pageTitle + ", fullMatchingImagesDTO="
				+ Arrays.toString(fullMatchingImagesDTO) + ", partialMatchingImagesDTO="
				+ Arrays.toString(partialMatchingImagesDTO) + ", url=" + url + "]";
	}

	

	
	
}
