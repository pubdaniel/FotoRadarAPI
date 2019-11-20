package main.java.com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.parser.ParseException;

import main.java.com.control.ImageSearchControl;
import main.java.com.dao.ImageDAO;
import main.java.com.dao.PageWithMatchingImagesDAO;

import com.google.gson.Gson;

import main.java.com.model.Image;
import main.java.com.model.PageWithMatchingImages;
import main.java.com.model.dto.google.ResponsesDTO;



@Path("/search")
public class ImageSearchService {

	Gson gson = new Gson();
	
	
	@GET
	@Path("/images/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImages(@QueryParam("url") String url) {
		
		ImageDAO imageDao = new ImageDAO();
		Image image = imageDao.buscarImagePorUrl(url);
		
		return Response.accepted(image).build();
	}
	
	@GET
	@Path("/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImages() {
		List<Image> images = new ArrayList<>();
		
		ImageDAO imageDao = new ImageDAO();
		images = imageDao.buscarTodos();
		
		return Response.accepted(images).build();
	}
	
	@GET
	@Path("/matchs/image/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMathsFromImage(@PathParam("id") Long id) {
		
		PageWithMatchingImagesDAO pwmiDAO = new PageWithMatchingImagesDAO();
		List<PageWithMatchingImages> pagesMatchess = pwmiDAO.buscarMatchsFromImage(id);
		
		
		return Response.accepted(pagesMatchess).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchFormImages(@FormParam("url") String url) {
		
		try {
			return Response.ok(ImageSearchControl.searchPagesWithMatchingImages(url)).build();
		} catch (IOException | ParseException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}		
	}
	
	
}
