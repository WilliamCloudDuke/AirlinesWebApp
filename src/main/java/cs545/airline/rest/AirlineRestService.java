package cs545.airline.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@Api(value = "airlines", description = "REST API for Airline services")
@Path("airlines")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped

public class AirlineRestService {

	@Inject
	private AirlineService airlineService;

	@GET
	@ApiOperation(value = "Get all Airlines", notes = "Get all the Airlines", responseContainer = "List", response = Airline.class)
	public List<Airline> getAllAirlines() {
		return airlineService.findAll();
	}

	@ApiOperation(value = "Get Airline by id", notes = "Get Airline by given id", response = Airline.class)
	@Path("/{id}")
	@GET
	public Airline getAirline(@PathParam("id") long id) {
		return airlineService.findById(id);
	}

	@ApiOperation(value = "Delete an Airline by id", notes = "Delete an Airline by id", response = Airline.class)
	@Path("/{id}")
	@DELETE
	public void deleteAirline(@PathParam("id") long id) {
		airlineService.delete(id);
	}

}
