package cs545.airline.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;

@Api(value = "airports", description = "REST API for Airport service")
@Path("airports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped

public class AirportRestService {

	@Inject
	private AirportService airportService;

	@ApiOperation(value = "Get Airport by id", notes = "Get Airport by given id", response = Airport.class)
	@Path("/{id}")
	@GET
	public Airport getAirport(@PathParam("id") long id) {
		return airportService.findById(id);
	}

	@ApiOperation(value = "Delete an Airport by id", notes = "Delete Airport by id", response = Airport.class)
	@Path("/{id}")
	@DELETE
	public void deleteAirport(@PathParam("id") long id) {
		airportService.delete(id);
	}

}
