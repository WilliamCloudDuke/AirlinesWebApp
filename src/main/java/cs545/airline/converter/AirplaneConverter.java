package cs545.airline.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.service.AirplaneService;

@Named("airplaneConverter")
@ApplicationScoped
public class AirplaneConverter implements Converter {

	@Inject
	private AirplaneService airplaneService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return airplaneService.findBySrlnr(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
