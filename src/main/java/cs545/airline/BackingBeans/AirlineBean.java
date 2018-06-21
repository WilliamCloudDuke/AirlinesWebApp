package cs545.airline.BackingBeans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;

@Named
@FlowScoped(value = "airlineBean")
public class AirlineBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String flightnr;

	private String departureDate;

	private String departureTime;

	private String arrivalDate;

	private String arrivalTime;

	private Airline airline;
	private String airlineName;
	private Flight flight;
	private String airplaneSerialnr;

	  private String airportOrigin;
	  private String airportDestination;

	

	public String getAirportDestination() {
		return airportDestination;
	}

	public void setAirportDestination(String airportDestination) {
		this.airportDestination = airportDestination;
	}

	public String getAirportOrigin() {
		return airportOrigin;
	}

	public void setAirportOrigin(String airportOrigin) {
		this.airportOrigin = airportOrigin;
	}

	public String getAirplaneSerialnr() {
		return airplaneSerialnr;
	}

	public void setAirplaneSerialnr(String airplaneSerialnr) {
		this.airplaneSerialnr = airplaneSerialnr;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	private Airport origin;

	private Airport destination;

	private Airplane airplane;

	@Inject
	private AirlineService airlineService;
	@Inject
	private AirplaneService airplaneService;
	@Inject
	private AirportService airportService;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlightnr() {
		return flightnr;
	}

	public void setFlightnr(String flightnr) {
		this.flightnr = flightnr;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Airline getAirline(String name) {
		return this.airlineService.findByName(name);
	}

	public String createFlight() {
		this.airline = getAirline(this.airlineName);

		this.flight = new Flight(this.flightnr, this.departureDate, this.departureTime, this.arrivalDate,
				this.arrivalTime);
		 this.flight.setAirplane(airplaneService.findBySrlnr(this.airplaneSerialnr));
		 this.flight.setOrigin(airportService.findByCode(this.airportOrigin));
		 this.flight.setDestination(airportService.findByCode(this.airportDestination));
		 this.airline.addFlight(flight);
		 this.airlineService.update(this.airline);
		 return "create Flight";// to be created

	}

}
