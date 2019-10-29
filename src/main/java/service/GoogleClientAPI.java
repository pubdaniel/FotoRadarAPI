package main.java.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import main.java.com.model.dto.google.GoogleRequest;

public class GoogleClientAPI {

    private static String GOOGLE_URL = "https://vision.googleapis.com/v1/images:annotate?key=";
    private static String API_KEY = "AIzaSyBkEDEswrALQFj1ySMPc9WfcRMCznCihk4";

    public GoogleClientAPI() {

    }

    public static String detectWebDetections(String imageUrl) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(GOOGLE_URL + API_KEY);

        String json = GoogleRequest.createRequestJson(imageUrl);

        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        //
        CloseableHttpResponse response = client.execute(httpPost);
        //
        String jsonResponse = EntityUtils.toString(response.getEntity());

        client.close();

        return jsonResponse;
    }

//    public static void main(String[] args) throws IOException, Exception {
//        String jsonResponse = GoogleClientAPI
//                .detectWebDetections("https://static.ndonline.com.br/2018/03/cropped/9c795a7a7e23d3e97f683791b09469a5e602cbd0.jpg");
//        FileOutputStream out = new FileOutputStream(new File("teste.json"));
//        out.write(jsonResponse.getBytes());
//        System.out.println("end");
//    }

}
