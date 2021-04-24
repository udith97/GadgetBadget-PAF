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
import com.model.BuyerServlet;

@Path("/Buyer")
public class BuyerService {

	BuyerServlet buyerObj = new BuyerServlet();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInvestment() {
		return buyerObj.readBuyer();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBuyer(
			@FormParam("buyerName") String buyerName, 
			@FormParam("projectName") String projectName,
			@FormParam("email") String email, 
			@FormParam("contactNo") String contactNo,
			@FormParam("researcherName") String researcherName) {
		String output = buyerObj.insertBuyer(buyerName, projectName, email, contactNo, researcherName);

		return output;

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBuyer(String buyerData) {
		// Convert the input string to a JSON object
		JsonObject buyerObject = new JsonParser().parse(buyerData).getAsJsonObject();
		// Read the values from the JSON object
		String buyerID = buyerObject.get("buyerID").getAsString();
		String buyerName = buyerObject.get("buyerName").getAsString();
		String projectName = buyerObject.get("projectName").getAsString();
		String email = buyerObject.get("email").getAsString();
		String contactNo = buyerObject.get("contactNo").getAsString();
		String researcherName = buyerObject.get("researcherName").getAsString();
		String output = buyerObj.updateBuyer(buyerID, buyerName, projectName, email,contactNo,researcherName);

		return output;
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBuyer(String buyerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String buyerID = doc.select("buyerID").text(); 
	 String output = buyerObj.deleteBuyer(buyerID); 
	return output; 
	}

}
