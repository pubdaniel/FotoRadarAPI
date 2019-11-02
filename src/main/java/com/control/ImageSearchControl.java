package main.java.com.control;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.com.dao.ImageDAO;
import main.java.com.dao.PageWithMatchingImagesDAO;
import main.java.com.model.Image;
import main.java.com.model.PageWithMatchingImages;
import main.java.com.model.dto.google.PageWithMatchingImagesDTO;
import main.java.service.GoogleClientAPI;

public class ImageSearchControl {

    private String url;

    public ImageSearchControl(String url) {
        this.url = url;
    }

    public ImageSearchControl() {}

    public static List<PageWithMatchingImages> searchPagesWithMatchingImages(String url)
            throws ClientProtocolException, IOException, ParseException {
        
    	ImageDAO dao = new ImageDAO();
    	Image i = new Image(url);
    	Image image = dao.salvar(i);
    	
    	Gson gson = new Gson();

        String json = GoogleClientAPI.detectWebDetections(url);

        // String json = ImageSearchControl.getJsonTeste();//teste mock
        System.out.println(json);

        PageWithMatchingImagesDAO pageWithMatchingImagesDAO = new PageWithMatchingImagesDAO();

        List<PageWithMatchingImages> pagesWithMatchingImages = getPagesWithMatchingImages(convertJsonToPagesWithMatchingImages(json));
        pagesWithMatchingImages.forEach(p -> {
            p.setImage(image);
            pageWithMatchingImagesDAO.salvar(p);
        });

        return pagesWithMatchingImages;

    }

    private static List<PageWithMatchingImages> getPagesWithMatchingImages(List<PageWithMatchingImagesDTO> pagesWithMatchingImages) {
        List<PageWithMatchingImages> pageWithMatchingImages = new ArrayList<>();

        pagesWithMatchingImages.forEach(pDTO -> {
            pageWithMatchingImages.add(new PageWithMatchingImages(pDTO));
        });

        return pageWithMatchingImages;
    }

    public String getUrl() {
        return url;
    }


    private static List<PageWithMatchingImagesDTO> convertJsonToPagesWithMatchingImages(String json) throws ParseException {
        Gson gson = new Gson();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        JSONArray response = (JSONArray) jsonObject.get("responses");
        JSONObject responseFisrtResult = (JSONObject) response.get(0);
        JSONObject webDetection = (JSONObject) responseFisrtResult.get("webDetection");
        JSONArray pagesWithMatchingImagesObj = (JSONArray) webDetection.get("pagesWithMatchingImages");

        return Arrays.asList(gson.fromJson(pagesWithMatchingImagesObj.toString(), PageWithMatchingImagesDTO[].class));
    }

}
