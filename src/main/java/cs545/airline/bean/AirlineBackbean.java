package cs545.airline.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@Named
@RequestScoped
public class AirlineBackbean {
	@Inject
	private AirlineService airlineserve;
	private Airline airline = new Airline();
	private List<Airline> airlines;
	
	@PostConstruct
	public void init() {
		airlines = airlineserve.findAll();
	}
	
	public Airline getAirline() {
		return airline;
	}
	
	public String createAirline(Airline airliine) {
		airlineserve.create(airliine);
		return "airlineList.xhtml";
	}

	public List<Airline> getAirlines() {
		return airlines;
	}

	public String editAirlineRecord(long id) {
		Airline l = airlineserve.findById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("editAirline", l);
		return "editAirline.xhtml";
	}

	public String updateAirplane(Airline airline) {
		airlineserve.update(airline);
		return "editAirline.xhtml";
	}
	
	public String deleteAirline(Airline a) {
		airlineserve.delete(a);
		return "airlineList.xhtml";
	}
	
}
