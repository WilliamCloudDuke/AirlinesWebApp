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
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
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
	@Inject
	private AirportService airportService;
	@Inject
	private FlightService flightService;
	private Airline airline = new Airline();
	private List<Airline> airlines = new ArrayList<>();
	private List<Flight>flights=new ArrayList<>();
	private Airline selectedAirLine = null; // to get list of flights
	private String selectedAirlineName;
	private String selectedAirportCode;
	
	

	public String getSelectedAirportCode() {
		return selectedAirportCode;
	}

	public void setSelectedAirportCode(String selectedAirportCode) {
		this.selectedAirportCode = selectedAirportCode;
	}
	
	//public List<Flight>get

	public void setAirlineService(AirlineService airlineService) {
		this.airlineService = airlineService;
	}

	public String getSelectedAirlineName() {
		return selectedAirlineName;
	}

	//get it in the combobox
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
		System.out.println(">>>>>>>>>>> error free?>>>>>>>>>");
		return airlineService.findAll().stream().map(a -> a.getName()).collect(Collectors.toList());
		
	}
	
	public List<String> getAirPorts() {
		return airportService.findAll().stream().map(a ->a.getAirportcode()).collect(Collectors.toList());
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
		flights=selectedAirLine.getFlights();
		return "flightList.xhtml?faces-redirect=true";
	}
	
	public List<Flight> getFlights() {
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
//		flights=flightService.findAll();
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+flights.size());
		return flights;
	}

	// Ajax for select airline name
	public void setSelectedAirline() {
		selectedAirLine = airlineService.findByName(selectedAirlineName);
		flights=selectedAirLine.getFlights();
	}
	
	
	public void selectFlightsByDestinaction() {
		Airport airport=airportService.findByCode(selectedAirportCode);
		flights=flightService.findByDestination(airport);
	}
	
	
	

}
