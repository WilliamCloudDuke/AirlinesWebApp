package cs545.airline.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@Named
@RequestScoped
public class FlightBean {
@Inject
private FlightService flightservice;
private Flight flight = new Flight();
private List<Flight> flights ;
private String searchType = "All";
private Date ddate;
private Airline line;
private Airport dport;
private Airport oport;
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


/*filters by date-time, airline, departure, and destination*/
public void filterbyDate(Date filterDate) {
	flights = flightservice.findByDeparture(filterDate);	
}

public void filterbyairline(Airline line) {
	flights = flightservice.findByAirline(line);
}

public void filterbydestinationPort(Airport port){
	flights = flightservice.findByDestination(port);
}

public void filterbyOriginPort(Airport port) {
	flights = flightservice.findByOrigin(port);
}


public List<String> getDropDownListOptions() {
	List<String> selectOptions = new ArrayList<String>();
	selectOptions.add("All");
	selectOptions.add("DepartureDate");
	selectOptions.add("AirLine");
	selectOptions.add("Origin");
	selectOptions.add("Destination");
	return selectOptions;
}

public boolean checkType(String toCheck) {
	boolean checked = false;
	
	
	return checked;
}

public void findbyselect() {
	switch(searchType) {
	 case "DepartureDate":
		 filterbyDate(ddate);
		 break;
		 
     case "AirLine":
    	 filterbyairline(line);
    	 break;
    	 
     case "Origin":
    	 filterbyOriginPort(dport);
    	 break;
    	 
     case "Destination":	
    	 filterbydestinationPort(oport);
    	 break;
    	 
     default:
    	 getflights(); 
	}

}


public FlightService getFlightservice() {
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

public String getSearchType() {
	return searchType;
}

public void setSearchType(String searchType) {
	this.searchType = searchType;
}

public Date getDdate() {
	return ddate;
}

public void setDdate(Date ddate) {
	this.ddate = ddate;
}

public Airline getLine() {
	return line;
}

public void setLine(Airline line) {
	this.line = line;
}

public Airport getDport() {
	return dport;
}

public void setDport(Airport dport) {
	this.dport = dport;
}

public Airport getOport() {
	return oport;
}

public void setOport(Airport oport) {
	this.oport = oport;
}

public void setFlight(Flight flight) {
	this.flight = flight;
}



}
