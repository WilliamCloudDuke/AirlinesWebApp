package cs545.airline.BackingBeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@Named("backingBean")
@SessionScoped
public class BackingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	AirlineService airlineService;
	
	public List<Airline> getList(){
		System.out.println("tEST DATA AND REACHED");
		List<Airline> airlines= airlineService.findAll();
		return airlines;
	}
	
	public List<Airline> getAirlines() {
		System.out.println(">>>>>>>>>>>>>>>> Testing ");
		List<Airline> airlines=airlineService.findAll();
		System.out.println(">>>>>>>>>>>>>>>> Testing "+airlines.size());
		return airlines;
	}

	

}
