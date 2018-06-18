package cs545.airline.BackingBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@Named("backingBean")
@RequestScoped
public class BackingBean implements Serializable {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AirlineService airlineService;
	List<Airline> airlines;
	Airline airline;

	public AirlineService getAirlineService() {
		return airlineService;
	}

	public Airline getAirline() {
		return airline;
	}

	

	public BackingBean(AirlineService airlineService, List<Airline> airlines, Airline airline) {
		super();

		this.airlines = airlineService.findAll();
		this.airline = new Airline();

	}

	public List<Airline> getAirlines() {
		return airlines;

	}

	public String createAirline() {
		this.airlines.add(airline);
		return "ListAirLines";

	}

}
