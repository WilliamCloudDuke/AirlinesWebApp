package cs545.airline.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Flight {
	@Id
	@GeneratedValue
	private long id;
	private String flightnr;
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	@Temporal(TemporalType.TIME)
	private Date departureTime;
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airline airline;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airport origin;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airport destination;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airplane airplane;

	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.US);

	/* Constructors */
	public Flight() {
	}

	public Flight(String flightnr, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
		this.flightnr = flightnr;
		setDepartureDate(departureDate);
		setDepartureTime(departureTime);
		setArrivalDate(arrivalDate);
		setArrivalTime(arrivalTime);
	}

	public Flight(String flightnr, String departureDate, String departureTime, String arrivalDate, String arrivalTime,
			Airline airline, Airport origin, Airport destination, Airplane airplane) {
		this.flightnr = flightnr;
		setDepartureDate(departureDate);
		setDepartureTime(departureTime);
		setArrivalDate(arrivalDate);
		setArrivalTime(arrivalTime);
		airline.addFlight(this);
		origin.addDeparture(this);
		destination.addArrival(this);
		airplane.addFlight(this);
	}

	/* Getters & Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlightnr() {
		return flightnr;
	}

	public void setFlightnr(String flightnr) {
		this.flightnr = flightnr;
	}

	public String getDepartureDate() {
		if (null != departureDate) {
			System.out.println("departureDate: " + departureDate);
			return df.format(departureDate);
		} else {
			return "";
		}
	}

	public void setDepartureDate(String departureDate) {
		try {
			if (null != departureDate) {
				System.out.println("departureDate: " + departureDate);
				this.departureDate = df.parse(departureDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDepartureTime() {
		if (null != departureTime) {
			System.out.println("departureTime: " + departureTime);
			return tf.format(departureTime);
		} else {
			return "";
		}

	}

	public void setDepartureTime(String departureTime) {
		try {
			if (null != departureTime) {
				System.out.println("departureTime: " + departureTime);
				this.departureTime = tf.parse(departureTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getArrivalDate() {
		if (null != arrivalDate) {
			System.out.println("arrivalDate: " + arrivalDate);
			return df.format(arrivalDate);
		} else {
			return "";
		}

	}

	public void setArrivalDate(String arrivalDate) {
		try {
			if (null != arrivalDate) {
				System.out.println("arrivalDate: " + arrivalDate);
				this.arrivalDate = df.parse(arrivalDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getArrivalTime() {
		if (null != arrivalTime) {
			System.out.println("arrivalTime: " + arrivalTime);
			return tf.format(arrivalTime);
		} else {
			return "";
		}
	}

	public void setArrivalTime(String arrivalTime) {
		try {
			if (null != arrivalTime) {
				this.arrivalTime = tf.parse(arrivalTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

}
