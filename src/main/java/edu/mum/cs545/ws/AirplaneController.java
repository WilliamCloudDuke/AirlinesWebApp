package edu.mum.cs545.ws;
import cs545.airline.model.Airplane;
import cs545.airline.model.ErrorMessage;
import cs545.airline.service.AirplaneService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Path("airplane")
public class AirplaneController {


    @Inject
    private AirplaneService airplaneService;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Airplane> getAllAirplane() {
        return airplaneService.findAll();
    }


    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response getAirplaneById(@PathParam("id") long id) {
        Airplane airplane = new Airplane();
        airplane.setId(id);
        airplane = airplaneService.find(airplane);
        if (airplane == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(airplane).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAirplane(Airplane airplane) {
        try {
            airplaneService.create(airplane);
        } catch (Exception ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode("500");
            errorMessage.setErrorMsg("An error occurred saving Airplane");
            return Response.status(500).entity(errorMessage).build();
        }
        return Response.status(204).build();
    }
}