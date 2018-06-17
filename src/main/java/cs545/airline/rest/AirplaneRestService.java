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

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

@Api(value = "airplanes", description = "REST API for Airplane service")
@Path("airplanes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped

public class AirplaneRestService {

	@Inject
	private AirplaneService airplaneService;

	@GET
	@ApiOperation(value = "Get all Airplanes", notes = "Get all Airplanes", responseContainer = "List", response = Airplane.class)
	public List<Airplane> getAllAirplanes() {
		return airplaneService.findAll();
	}

	@ApiOperation(value = "Get Airplane by id", notes = "Get Airplane by id", response = Airplane.class)
	@Path("/{id}")
	@GET
	public Airplane getAirplane(@PathParam("id") long id) {
		return airplaneService.findById(id);
	}

	@ApiOperation(value = "Delete an Airplane by id", notes = "Delete an Airplane by id", response = Airplane.class)
	@Path("/{id}")
	@DELETE
	public void deleteAirplane(@PathParam("id") long id) {
		airplaneService.delete(id);
	}

}
