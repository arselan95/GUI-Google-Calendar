
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import calendar3.myDate;

/**
 * Data model used to hold an Arraylist of Events, Calendar of selected day.
 * (Model part)
 * 
 * @author Arselan
 */
public final class EventModel {
	ArrayList<Event> events = new ArrayList<>();
	private Calendar todayCal;
	private MainView view;

	/**
	 * create an Event model
	 */
	public EventModel() {
		todayCal = new GregorianCalendar();
	}

	/**
	 * highlight the day in the calendar
	 * 
	 * @param day
	 */
	public void setDay(int day) {
		todayCal.set(Calendar.DAY_OF_MONTH, day);
		view.repaint();
	}

	/**
	 * goes to the previous day
	 */
	public void previousDay() {
		todayCal.add(Calendar.DAY_OF_MONTH, -1);
		view.repaint();
	}

	/**
	 * goes to the next day
	 */
	public void nextDay() {
		todayCal.add(Calendar.DAY_OF_MONTH, 1);

		view.repaint();
	}

	/**
	 * returns the calendar
	 * 
	 * @return , the calendar
	 */
	public Calendar getCal() {
		return todayCal;
	}

	/**
	 * add event to the list
	 * 
	 * @param e
	 */
	public void addEvent(Event e) {
		events.add(e);
		Collections.sort(events);
		view.repaint();
	}

	/**
	 * delete event from the list
	 * 
	 * @param e
	 */
	public void deleteevent(Event e) {
		events.remove(e);
		Collections.sort(events);
		view.repaint();
	}
public void clearEvent(Event e)
{
	for(int i = events.size() - 1; i >= 0; i--)
	{
		if(events.get(i) == e)
		{
			events.remove(e);
		}
	}
	view.repaint();
}
	/**
	 * get all the events
	 * 
	 * @return
	 */
	public ArrayList<Event> getEvents() {

		return events;
	}

	/**
	 * sets the main view
	 * 
	 * @param view
	 */
	void setView(MainView view) {
		this.view = view;
	}
}
