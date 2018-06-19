package cs545.airline.BackingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.service.AirlineService;

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
	private Airline selectedAirLine = null;

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
	
	public String editAirLine(long id) {
		Airline air = airlineService.findById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		System.out.println("sessionMap " + sessionMap);
		sessionMap.put("editAirline", air);
		return "editAirline.xhtml?faces-redirect=true";
	}
	
	public String updateAirline(Airline airline) {
		
		airlineService.create(airline);;
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

}
