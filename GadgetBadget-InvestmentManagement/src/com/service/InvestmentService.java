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
import com.model.InvestmentServlet;
;



@Path("/Investment") 
public class InvestmentService 
{
	InvestmentServlet investmentObj = new InvestmentServlet(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readInvestment() 
	 { 
	 return investmentObj.readInvestment(); 
	 }
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertInvestment(
			@FormParam("projectName") String projectName, 
			@FormParam("researcherName") String researcherName, 
			@FormParam("investorName") String investorName, 
			@FormParam("investAmount") String investAmount,
			@FormParam("cardNo") String cardNo,
			@FormParam("cardNo") String cvvNo)
	{ 
	 String output = investmentObj.insertInvestment(projectName, researcherName, investorName, investAmount, cardNo, cvvNo); 
	
	 return output; 
	
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateInvestment(String investmentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject investmentObject = new JsonParser().parse(investmentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String investmentID = investmentObject.get("investmentID").getAsString(); 
	 String projectName = investmentObject.get("projectName").getAsString(); 
	 String researcherName = investmentObject.get("researcherName").getAsString(); 
	 String investorName = investmentObject.get("investorName").getAsString(); 
	 String investAmount = investmentObject.get("investAmount").getAsString(); 
	 String cardNo = investmentObject.get("cardNo").getAsString();
	 String cvvNo = investmentObject.get("cvvNo").getAsString();
	 String output = investmentObj.updateInvestment(investmentID, projectName, researcherName, investorName, investAmount, cardNo, cvvNo); 
	
	 return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteInvestment(String investmentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(investmentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String investmentID = doc.select("investmentID").text(); 
	 String output = investmentObj.deleteInvestment(investmentID); 
	return output; 
	}
	
	
}