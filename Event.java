
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Object type Event hold a name, Calendar start and end times.
 * 
 * @author Arselan
 */
class Event implements Comparable<Event> {
	String eventname;
	Calendar start;
	Calendar end;

	/**
	 * create an event
	 * 
	 * @param name
	 * @param start
	 * @param end
	 * @throws IllegalArgumentException
	 */
	public Event(String name, GregorianCalendar start, GregorianCalendar end) throws IllegalArgumentException {
		this.eventname = name;
		this.start = start;
		this.end = end;
	}

	/**
	 * get the event string
	 * 
	 * @param e,
	 *            the event @return, the event string
	 */
	public String getEventString(Event e) {
		Date startDate = e.start.getTime();
		Date endDate = e.end.getTime();

		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		return e.eventname + sf.format(startDate) + " - " + sf.format(endDate);
	}
	// public String toString() {
	// return start.toString() + end.toString();
	// }

	@Override
	/**
	 * compare two events
	 */
	public int compareTo(Event newEvent) {
		if (newEvent.start.before(this.start) && newEvent.end.before(this.start)) {
			return 1;
		} else if (newEvent.start.after(this.end) && newEvent.end.after(this.end)) {
			return -1;
		} else {
			return 0;
		}
	}

}
