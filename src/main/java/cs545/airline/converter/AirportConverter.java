package cs545.airline.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.service.AirportService;

@Named("airportConverter")
@ApplicationScoped
public class AirportConverter implements Converter {

	@Inject
	private AirportService airportService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return airportService.findByCode(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
