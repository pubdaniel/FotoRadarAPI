//package main.test.com.service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.apache.tomcat.util.json.JSONParser;
//import org.json.simple.JSONObject;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import junit.framework.Assert;
//
//public class ImageSearchServiceTest {
//
//	@Ignore
//	@Test
//	public void deveInformarImagemPorUrl() throws ClientProtocolException, IOException {
//		CloseableHttpClient client = HttpClients.createDefault();
//
//		HttpGet request = new HttpGet(
//				"http://localhost:8080/FotoRadarAPI/api/search/images/find?url=https://static.ndonline.com.br/2018/03/cropped/9c795a7a7e23d3e97f683791b09469a5e602cbd0.jpg");
//		CloseableHttpResponse response = client.execute(request);
//
//		HttpEntity entity = response.getEntity();
//
//		Assert.assertEquals(response.getStatusLine().getStatusCode(), 202);
//
//		Assert.assertEquals(EntityUtils.toString(entity).length() > 10, true);
//
//		client.close();
//	}
//	@Ignore
//	@Test
//	public void deveInformarListaDeImagens() throws ClientProtocolException, IOException {
//
//		CloseableHttpClient client = HttpClients.createDefault();
//
//		HttpGet request = new HttpGet("http://localhost:8080/FotoRadarAPI/api/search/images");
//		CloseableHttpResponse response = client.execute(request);
//
//		HttpEntity entity = response.getEntity();
//
//		Assert.assertEquals(response.getStatusLine().getStatusCode(), 202);
//
//		Assert.assertEquals(EntityUtils.toString(entity).length() > 10, true);
//
//		client.close();
//
//	}
//	@Ignore
//	@Test
//	public void deveInformarPaginasParaUmaImagem() throws ClientProtocolException, IOException {
//		CloseableHttpClient client = HttpClients.createDefault();
//
//		HttpGet request = new HttpGet("http://localhost:8080/FotoRadarAPI/api/search/matchs/image/1");
//		CloseableHttpResponse response = client.execute(request);
//
//		HttpEntity entity = response.getEntity();
//
//		Assert.assertEquals(202, response.getStatusLine().getStatusCode());
//
//		Assert.assertEquals(EntityUtils.toString(entity).length() > 10, true);
//
//		client.close();
//	}
//	@Ignore
//	@Test
//	public void deveRealizarBuscaDeNovaImagem() throws ClientProtocolException, IOException {
//		CloseableHttpClient client = HttpClients.createDefault();
//
//		HttpPost request = new HttpPost("http://localhost:8080/FotoRadarAPI/api/search");
//		
//		List<NameValuePair> urlParam = new ArrayList<>();
//		urlParam.add(new BasicNameValuePair("url", "https://static.ndonline.com.br/2018/03/cropped/9c795a7a7e23d3e97f683791b09469a5e602cbd0.jpg"));
//		
//		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
//		
//		request.setEntity(new UrlEncodedFormEntity(urlParam));
//		
//		CloseableHttpResponse response = client.execute(request);
//
//		HttpEntity entity = response.getEntity();
//
//		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
//				
//		Assert.assertEquals(EntityUtils.toString(entity).length() > 10, true);
//
//		client.close();
//	}
//
//}
