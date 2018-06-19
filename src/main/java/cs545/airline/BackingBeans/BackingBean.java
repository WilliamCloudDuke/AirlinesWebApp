package cs545.airline.BackingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;

@Named("backingBean")
@SessionScoped
public class BackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private AirlineService airlineService;
	private Airline airline = new Airline();
	private List<Airline> airlines = new ArrayList<>();
	private Airline selectedAirLine = null; // to get list of flights
	private String departureDate;
	

	
	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	private String selectedAirlineName;
	
	@Inject
	private FlightService flightService;

	public FlightService getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightService flightService) {
		this.flightService = flightService;
	}

	public String getSelectedAirlineName() {
		return selectedAirlineName;
	}

	//get seting the value to pass it to ajax
	public void setSelectedAirlineName(String selectedAirlineName) {
		this.selectedAirlineName = selectedAirlineName;
	}

	  
	public Airline getSelectedAirLine() {
		return selectedAirLine;
	}

	public void setSelectedAirLine(Airline selectedAirLine) {
		this.selectedAirLine = selectedAirLine;
	}

	public String createAirline() {

		airlineService.create(airline);
		airline = new Airline();
		return "ListAirLines.xhtml?faces-redirect=true";

	}

	public AirlineService getAirlineService() {
		return airlineService;
	}

	public Airline getAirline() {
		return airline;
	}

	public List<Airline> getAirlines() {
		airlines = airlineService.findAll();
		return airlines;
	}
	
	// get all airlines names to show up on the select menu
	public List<String> getAirlineNames() {
		return airlineService.findAll().stream().map(a -> a.getName()).collect(Collectors.toList());
	}
	
	public String editAirLine(long id) {
		Airline air = airlineService.findById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		System.out.println("sessionMap " + sessionMap);
		sessionMap.put("editAirline", air);
		return "editAirline.xhtml?faces-redirect=true";
	}
	
	public String updateAirline(Airline airline) {
		airlineService.update(airline);
		
		//airlineService.create(airline);;
		return "ListAirLines.xhtml?faces-redirect=true";
		
	}
	
	public String deleteAirline(long id) {
		airlineService.delete(id);
		return "ListAirLines.xhtml?faces-redirect=true";
		
	}
	
	public String airlineDetails(String name) {
		selectedAirLine = airlineService.findByName(name);
		return "flightList.xhtml?faces-redirect=true";
	}
	
	// Ajax for select airline name
	public void setSelectedAirline() {
		selectedAirLine = airlineService.findByName(selectedAirlineName);
	}
	
	// get list of departure dates
	
	public List<String> getDeprtureDate() {
		
		return flightService.findAll().stream().map(e->e.getDepartureDate()).collect(Collectors.toList());
		
		
	}
	
	

}
