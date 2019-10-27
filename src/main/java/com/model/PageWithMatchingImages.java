package main.java.com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import main.java.com.model.dto.google.PageWithMatchingImagesDTO;

@Entity
@Table(name = "pagematched")
public class PageWithMatchingImages implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pagematched_id")
	private Long id;
	private String pageTitle;
	private String url;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Image image;
	
	public PageWithMatchingImages() {
		super();
	}
	
	public PageWithMatchingImages(Image image) {
		super();
		this.image = image;
	}

	public PageWithMatchingImages(PageWithMatchingImagesDTO p) {
		this.pageTitle = p.getPageTitle();
		this.url = p.getUrl();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
