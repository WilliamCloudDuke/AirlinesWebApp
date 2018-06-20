package cs545.airline.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;


@Named
@RequestScoped
public class AirplaneBean {
	@Inject
	private AirplaneService airplaneserve;
	private Airplane airplane = new Airplane();
	private List<Airplane> airplanes;
	
	@PostConstruct
	public void init() {
		airplanes = airplaneserve.findAll();
	}
	
	public Airplane getAirplane() {
		return airplane;
	}
	
	public String createAirplane(Airplane a) {
		airplaneserve.create(a);
		return "airplaneList.xhtml";
	}

	public List<Airplane> getAirplanes() {
		return airplanes;
	}

	public String editAirplaneRecord(long id) {
		Airplane p = airplaneserve.findById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("editAirplane", p);
		return "editAirplane.xhtml";
	}

	public String updateAirplane(Airplane airplane) {
		airplaneserve.update(airplane);
		return "editAirplane.xhtml";
	}
	
	public String deleteAirplane(Airplane a) {
		airplaneserve.delete(a);
		return "airplaneList.xhtml";
	}
	
}
