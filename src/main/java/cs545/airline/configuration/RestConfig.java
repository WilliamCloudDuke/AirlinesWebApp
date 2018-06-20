package cs545.airline.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import cs545.airline.rest.AirlineRestService;

@ApplicationPath("/rest")
public class RestConfig extends Application {
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestConfig() {
		super();
		classes.add(AirlineRestService.class);
		classes.add(JacksonConfig.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

}
