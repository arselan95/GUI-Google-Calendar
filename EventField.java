
import javax.swing.JTextField;

/**
 * create event field for the events
 * 
 * @author Arselan
 *
 */

public class EventField extends JTextField {
	private Event event;

	/**
	 * make a field
	 * 
	 * @param columns
	 */
	public EventField(int columns) {
		super(columns);
		event = null;
	}

	/**
	 * set events to the field
	 * 
	 * @param event,
	 *            event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * get events from the field
	 * 
	 * @return
	 */
	public Event getEvent() {
		return event;
	}
}
