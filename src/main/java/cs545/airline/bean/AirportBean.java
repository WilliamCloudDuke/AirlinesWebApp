package cs545.airline.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;

@Named
@RequestScoped
public class AirportBean {
	@Inject
	private AirportService airportService;
	private Airport airport = new Airport();
	private List<Airport> airports;

	@PostConstruct
	public void init() {
		airports = airportService.findAll();
	}

	public Airport getAirport() {
		return airport;
	}

	public String createAirport() {
		airportService.create(airport);
		// return "airportList.xhtml";
		return "airportList.xhtml?faces-redirect=true";
	}

	public List<Airport> getAirports() {
		return airports;
	}

	public String editAiportRecord(long id) {
		Airport a = airportService.findById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		System.out.println("sessionMap " + sessionMap);
		sessionMap.put("editAirport", a);
		return "editAirport.xhtml?faces-redirect=true";
	}

	public String updateAirport(Airport airport) {
		airportService.update(airport);
		return "airportList.xhtml?faces-redirect=true";
	}
} 