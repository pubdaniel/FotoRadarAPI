package main.java.com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @OneToMany
    private List<PageWithMatchingImages> pagesWithMatchingImages;

    public Image() {
        super();
    }

    public Image(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PageWithMatchingImages> getPagesWithMatchingImages() {
        return pagesWithMatchingImages;
    }

    public void setPagesWithMatchingImages(List<PageWithMatchingImages> pagesWithMatchingImages) {
        this.pagesWithMatchingImages = pagesWithMatchingImages;
    }

}
