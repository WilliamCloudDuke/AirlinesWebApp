package cs545.airline.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@RequestScoped
public class FlightBean {

	@Inject
	private FlightService flightService;

	@Inject
	private AirlineService airlineService;

	@Inject
	private AirplaneService airplaneService;

	@Inject
	private AirportService airportService;

	private List<Flight> flightList;
	private List<Flight> flightsFiltered;
	private List<Airline> airlineList;
	private List<Airplane> airplaneList;
	private List<Airport> airportList;
//	private Map<String, String> airlineMap;
//	private Map<String, String> airportMap;
//	private Map<String, String> airplaneMap;
	private Flight flight;

	public FlightBean() {
		super();
	}

	@PostConstruct
	public void init() {
		flightList = flightService.findAll();
		airlineList = airlineService.findAll();
		airplaneList = airplaneService.findAll();
		airportList = airportService.findAll();
//		airlineMap = new HashMap<>();
//		airportMap = new HashMap<>();
//		airplaneMap = new HashMap<>();
		flight = new Flight();
	}

	public String createFlight() {
		System.out.println("***Creating Flight*** ");
		flightService.create(flight);
		return "flightList.xhtml?faces-redirect=true";
	}

	public List<Flight> getFlightList() {
		return flightList;
	}

	public String editFlight(long id) {
		Flight flight = flightService.findById(id);
		System.out.println("flight.getAirline().getName(): " + flight.getAirline().getName());
		System.out.println("flight.getOrigin().getAirportcode(): " + flight.getOrigin().getAirportcode());
		System.out.println("flight.getDestination().getAirportcode(): " + flight.getDestination().getAirportcode());
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("editFlight", flight);
		return "editFlight.xhtml?faces-redirect=true";
	}

	/*
	 * public void onDateSelect(SelectEvent event) { FacesContext facesContext =
	 * FacesContext.getCurrentInstance(); SimpleDateFormat format = new
	 * SimpleDateFormat("dd/MM/yyyy"); facesContext.addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
	 * format.format(event.getObject()))); }
	 */

	public String updateFlight(Flight flight) {
		System.out.println("Updating flight---: " + flight.getId());
		flightService.update(flight);
		return "flightList.xhtml?faces-redirect=true";
	}

	public List<Flight> getFlightsFiltered() {
		return flightsFiltered;
	}

	public void setFlightsFiltered(List<Flight> flightsFiltered) {
		this.flightsFiltered = flightsFiltered;
	}

	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}

	public List<Airline> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airline> airlineList) {
		this.airlineList = airlineList;
	}

	public List<Airplane> getAirplaneList() {
		return airplaneList;
	}

	public void setAirplaneList(List<Airplane> airplaneList) {
		this.airplaneList = airplaneList;
	}

	public List<Airport> getAirportList() {
		return airportList;
	}

	public void setAirportList(List<Airport> airportList) {
		this.airportList = airportList;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Map<String, String> getAirlineMap() {
		Map<String, String> airlineMap = new HashMap<>();
		this.airlineList.stream().forEach(a -> airlineMap.put(a.getName(), a.getName()));
		return airlineMap;
	}

	public Map<String, String> getAirportMap() {
		Map<String, String> airportMap = new HashMap<>();
		this.airportList.stream().forEach(a -> airportMap.put(a.getName(), a.getAirportcode()));
		return airportMap;
	}

	public Map<String, String> getAirplaneMap() {
		Map<String, String> airplaneMap = new HashMap<>();
		this.airplaneList.stream().forEach(a -> airplaneMap.put(a.getSerialnr(), a.getSerialnr()));
		return airplaneMap;
	}

}
