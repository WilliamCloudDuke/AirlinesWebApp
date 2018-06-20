package cs545.airline.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;

@Named
@RequestScoped
public class FlightBean {
@Inject
private FlightService flightservice;
@Inject
private AirlineService airlineService;

private Flight flight = new Flight();
private String selectedName;
private List<Flight> flights = new ArrayList<>();
private List<Airline> airlines = new ArrayList<>();
public FlightBean() {}


@PostConstruct
public void init() {
	flights = flightservice.findAll();
}

public Flight getFlight() {
	return this.flight;
}

public String createFlight(Flight f) {
	flightservice.create(f);
	return "flightList.xhtml";
}

public List<Flight> getflights() {
	return this.flights;
}

public String editFlightRecord(long id) {
	Flight f = flightservice.findById(id);
	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	sessionMap.put("editFlight", f);
	return "editFlight.xhtml";
}

public String updateFlight(Flight f) {
	flightservice.update(f);
	return "flightList.xhtml";
}

public void getSelectedFlights() {
	Airline airline = airlineService.findByName(selectedName);
	flights = airline.getFlights();
}

public void setSelectedName(String selected) {
	this.selectedName = selected;
}

public String getSelectedName() {
	return this.selectedName;
}


public FlightService getFlightservice(){
	return flightservice;
}

public void setFlightservice(FlightService flightservice) {
	this.flightservice = flightservice;
}

public List<Flight> getFlights() {
	return flights;
}

public void setFlights(List<Flight> flights) {
	this.flights = flights;
}

public void setFlight(Flight flight) {
	this.flight = flight;
}


public AirlineService getAirlineService() {
	return airlineService;
}


public void setAirlineService(AirlineService airlineService) {
	this.airlineService = airlineService;
}


public List<Airline> getAirlines() {
	airlines = airlineService.findAll();
	return airlines;
}


public void setAirlines(List<Airline> airlines) {
	this.airlines = airlines;
}

}
