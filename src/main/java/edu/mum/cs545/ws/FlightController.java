package edu.mum.cs545.ws;


import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.ErrorMessage;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.FlightService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("flight")
public class FlightController {

    @Inject
    private FlightService flightService;

    @Inject
    private AirlineService airlineService;

    @Inject
    private AirplaneService airplaneService;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Flight> getAllAirplane() {
        return flightService.findAll();
    }


    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response getFlightById(@PathParam("id") long id) {
        Flight flight = new Flight();
        flight.setId(id);
        flight = flightService.find(flight);
        if (flight == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(flight).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFlight(Flight flight) {
        try {


            Airline airline = flight.getAirline();
            airline.addFlight(flight);
            airlineService.update(airline);
            Airplane airplane = flight.getAirplane();
            airplane.addFlight(flight);
            airplaneService.update(airplane);


           //flightService.create(flight);
        } catch (Exception ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode("500");
            errorMessage.setErrorMsg("An error occurred saving Flight");
            return Response.status(500).entity(errorMessage).build();
        }
        return Response.status(204).build();
    }

}