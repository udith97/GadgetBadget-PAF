package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.ProjectServlet;



@Path("/Project")
public class ProjectService {
	ProjectServlet projectObj = new ProjectServlet(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readproject() 
	 { 
	 return projectObj.readProject(); 
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProject(
			@FormParam("project_type") String project_type, 
			@FormParam("project_name") String project_name, 
			@FormParam("researcher") String researcher, 
			@FormParam("Description") String Description)

	{ 
	 String output = projectObj.insertProject(project_type, project_name, researcher, Description); 
	
	 return output; 
	
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProject(String projectData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String project_id = projectObject.get("project_id").getAsString(); 
	 String project_type = projectObject.get("project_type").getAsString(); 
	 String project_name = projectObject.get("project_name").getAsString(); 
	 String researcher = projectObject.get("researcher").getAsString(); 
	 String Description = projectObject.get("Description").getAsString(); 

	 String output = projectObj.updateProject(project_id, project_type, project_name, researcher, Description); 
	
	 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProject(String projectData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String project_id = doc.select("project_id").text(); 
	 String output = projectObj.deleteProject(project_id); 
	return output; 
	}
	
}






