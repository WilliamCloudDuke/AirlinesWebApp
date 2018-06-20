package edu.mum.cs545.ws;



import cs545.airline.model.Airline;
import cs545.airline.model.ErrorMessage;
import cs545.airline.service.AirlineService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("airline")
public class AirlineController {

    @Inject
    private AirlineService airlineService;


    @GET
    @Produces({"application/xml", "application/json"})
    public List<Airline> getAllAirlines() {
        return airlineService.findAll();
    }


    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response getAirlineById(@PathParam("id") long id) {
        Airline airline = new Airline();
        airline.setId(id);
        airline = airlineService.find(airline);
        if(airline==null){
            return Response.status(404).build();
        }
        return Response.status(200).entity(airline).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAirline(Airline airline){
        try{
            airlineService.create(airline);
        }catch (Exception ex){
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode("500");
            errorMessage.setErrorMsg("An error occurred saving Airline");
            return Response.status(500).entity(errorMessage).build();
        }
        return Response.status(204).build();
    }
}