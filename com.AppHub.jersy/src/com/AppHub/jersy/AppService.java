package com.AppHub.jersy;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database_console.*;
import model.*;


@Path("/AppService")
public class AppService {

	private AppTable appTable = AppTable.getInstance();

	  // This method is called if HTML is request
	  @GET
	  @Path("/test")
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
		ArrayList<App> apps = appTable.getAppList();
		String appName = apps.get(0).getAppName();
		String appDev = apps.get(0).getAppDeveloper();
		String appDesc = apps.get(0).getDescription();
		String appUrl = apps.get(0).getAppUrl();
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "App Name: " + appName + "</h1>"
	        		+ "<h1>" +"App Developer: " + appDev +"</h1>"
	        				+ "<h1>" +"App Description: " + appDesc +"</h1>"
	        						+ "<h1>" +"App URL: " + appUrl +"</h1></body>" + "</html> ";
	  }
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<App> getAllApps() {
		  ArrayList<App> apps = appTable.getAppList();
		  return apps;
	  }
	  
	  @GET
	  @Path("/{appId}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public App getApp(@PathParam("appId") String appId) {
		  App app = appTable.searchTable("appId", appId);
		  return app;
	  }
	  
	  @POST
	  @Path("/create")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public App createApp(App app) {
		  app = appTable.newApp(app);
		  return app;
	  }
	  
	  @PUT
	  @Path("/update")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public App updateApp(App app) {
		  app = appTable.updateApp(app);
		  return app;
	  }
	  
	  @DELETE
	  @Path("/remove/{appId}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response deleteApp(@PathParam("appId") String appId) {
		  boolean deleted = appTable.deleteApp(appId);
		  if(deleted) {
			  return Response.status(201).entity("App " + appId + " deleted").build();
		  }
		  else {
			  return Response.status(400).entity("App " + appId + " was not deleted").build();
		  }
		  
	  }

}
