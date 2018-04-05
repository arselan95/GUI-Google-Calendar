import java.util.GregorianCalendar;

/**
 * run the calendar app 
 */

/**
 *
 * @author Arselan
 */
public class SimpleCalendar {

	public static void main(String[] args) {

		EventModel model = new EventModel();
		MainView mv = new MainView(model);
		model.setView(mv);

	}
}