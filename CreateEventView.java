
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * CreateEventView creates a new window to create events and add them to the
 * data model.
 * 
 * @author Arselan
 * 
 */
public class CreateEventView {

	/**
	 * Create an event dialoge box
	 * 
	 * @param model
	 *            , event model
	 */
	public CreateEventView(EventModel model) {
		final JFrame frame = new JFrame();

		Calendar cal = model.getCal();

		SimpleDateFormat currentTime = new SimpleDateFormat("hh:mmaa");
		Calendar formatEnd = new GregorianCalendar();
		formatEnd.setTime(cal.getTime());
		formatEnd.add(Calendar.MINUTE, 30);

		currentTime.format(cal.getTime());

		final JTextField descField = new JTextField("Description here");
		final JTextField dateField = new JTextField(
				(cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
		final JTextField startField = new JTextField(currentTime.format(cal.getTime()));
		final JTextField endField = new JTextField(currentTime.format(formatEnd.getTime()));
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		// CONTROLLER gets the from the fields and updates the model with a new
		// Event.
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = descField.getText();
				String date = dateField.getText();
				String start = startField.getText();
				String end = endField.getText();

				SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyyhh:mmaa");

				Date startDate = null;
				try {
					startDate = sf.parse(date + start);
				} catch (ParseException ex) {
					Logger.getLogger(CreateEventView.class.getName()).log(Level.SEVERE, null, ex);
				}
				Date endDate = null;
				try {
					endDate = sf.parse(date + end);
				} catch (ParseException ex) {
					Logger.getLogger(CreateEventView.class.getName()).log(Level.SEVERE, null, ex);
				}

				Calendar startCal = new GregorianCalendar();
				startCal.setTime(startDate);
				Calendar endCal = new GregorianCalendar();
				endCal.setTime(endDate);

				Event newEvent = new Event(name, (GregorianCalendar) startCal, (GregorianCalendar) endCal);

				boolean conflict = false;

				if (newEvent.end.before(newEvent.start) || newEvent.start.equals(newEvent.end)) {
					JOptionPane.showMessageDialog(frame, "End cannot come before start!", "Time Conflict",
							JOptionPane.WARNING_MESSAGE);
					conflict = true;
				}
				for (Event event : model.getEvents()) {
					if (event.compareTo(newEvent) == 0) {
						JOptionPane.showMessageDialog(frame, "Times cannot overlap!", "Time Conflict",
								JOptionPane.WARNING_MESSAGE);
						conflict = true;
						break;
					}
				}

				if (!conflict) {
					model.addEvent(newEvent);

				}

				conflict = false;

				frame.dispose();
			}
		});

		JPanel panel = new JPanel();

		panel.add(descField);
		panel.add(dateField);
		panel.add(startField);
		panel.add(endField);
		panel.add(saveButton);
		panel.add(cancelButton);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
