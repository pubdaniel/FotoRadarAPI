package main.java.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import main.java.com.model.dto.google.PageWithMatchingImagesDTO;

@Entity
@Table(name = "pagematched")
public class PageWithMatchingImages implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pageTitle;
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne
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
