package edu.mum.cs545.ws;



import cs545.airline.model.Airport;
import cs545.airline.model.ErrorMessage;
import cs545.airline.service.AirportService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("airport")
public class AirportController {
    @Inject
    private AirportService airportService;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Airport> getAllAirports() {
        return airportService.findAll();
    }


    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response gietAirportById(@PathParam("id") long id) {
        Airport airport = new Airport();
        airport.setId(id);
        airport = airportService.find(airport);
        if (airport == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(airport).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAirport(Airport airport) {
        try {
            airportService.create(airport);
        } catch (Exception ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode("500");
            errorMessage.setErrorMsg("An error occurred saving airport");
            return Response.status(500).entity(errorMessage).build();
        }
        return Response.status(204).build();
    }
}
