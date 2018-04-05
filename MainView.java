
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;

/**
 * Create a Calendar (View Portion)
 * 
 * @author Arselan
 */
public class MainView {
	/**
	 * Populates the day panel with events that have been added to the data
	 * model
	 */

	private static EventModel model;
	private final Calendar cal;
	private final JLabel monthLabel = new JLabel();
	private final JPanel monthPanel;
	private final JPanel dayPanel;
	static JTextField jt;
	JPanel w;
	ArrayList<Event> events;
	EventField[] eventfields = new EventField[48];
	JLabel topday;
	static String name;
	static String date;
	static String start;
	static String end;
	JFrame frame;
	JTextField j;
	JPanel timePanel;

	private final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private final String[] DAYS = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	private final String[] TIME = { "12am", "1am", "2am", "3am", "4am", "5am", "6am", "7am", "8am", "9am", "10am",
			"11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm", "8pm", "9pm", "10pm", "11pm", "12pm" };

	/**
	 * Constructs a new Main View using an EventModel data model.
	 * 
	 * @param model
	 *            is required to produce the correct data for the view.
	 */
	public MainView(final EventModel model) {
		// Initializes model variable

		this.model = model;
		this.cal = model.getCal();
		events = model.getEvents();
		topday = new JLabel(DAYS[cal.get(Calendar.DAY_OF_WEEK) - 1] + " " + (cal.get(Calendar.MONTH) + 1) + "/"
				+ cal.get(Calendar.DAY_OF_MONTH), SwingConstants.CENTER);
		topday.setBackground(Color.WHITE);

		// Initialazes and setsup buttons
		JButton createButton = new JButton("Create");
		createButton.setBackground(Color.red);
		createButton.setForeground(Color.WHITE);
		JButton previousButton = new JButton("<");
		previousButton.setBackground(Color.white);
		JButton nextButton = new JButton(">");
		nextButton.setBackground(Color.white);
		JButton quitButton = new JButton("quit");
		quitButton.setBackground(Color.white);
		JButton deleteButton = new JButton("delete");
		deleteButton.setBackground(Color.red);

		/*
		 * CONTROLLER used for main view. This changes the model by adding or
		 * subtracting a day , create event or quit the application.
		 */
		quitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				for(int i=0;i<events.size();i++){
//					Event et=events.get(i);
//				EventsFiler.saveEvent(et);
//				}

				System.exit(0);
			}
		});
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateEventView cev = new CreateEventView(model);

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ArrayList<Event> events = model.getEvents();
				for (int i=0;i<events.size();i++) {
					Event ee=events.get(i);
					model.clearEvent(ee);;
				}
			}
		});

		previousButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.previousDay();
			}
		});

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.nextDay();
			}
		});

		// Adds buttons to button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(createButton);
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(quitButton);
		buttonPanel.add(deleteButton);
		buttonPanel.setBackground(Color.WHITE);

		// Sets up month panel and calls drawMonth to fill in initial data
		monthPanel = new JPanel();
		monthPanel.setLayout(new GridLayout(0, 7, 5, 5));
		monthPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		monthPanel.setBackground(Color.white);
		JPanel monthWrap = new JPanel();
		monthWrap.setLayout(new BoxLayout(monthWrap, BoxLayout.Y_AXIS));
		monthWrap.add(monthLabel);
		monthWrap.add(monthPanel);
		monthWrap.setBackground(Color.WHITE);
		drawMonth(monthPanel);

		// Sets up day view and puts in a scroll pane
		JScrollPane scroll = new JScrollPane();
		dayPanel = new JPanel();
		timePanel = new JPanel();
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));
		dayPanel.setBackground(Color.WHITE);
		dayPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		dayPanel.setLayout(new GridLayout(48, 1, 0, 0));
		JPanel w = new JPanel();
		w = new JPanel(new BorderLayout());
		w.add(timePanel, BorderLayout.WEST);
		w.add(dayPanel, BorderLayout.CENTER);
		w.add(topday, BorderLayout.NORTH);
		drawDay(dayPanel);

		scroll.getViewport().add(w);
		scroll.setPreferredSize(new Dimension(600, 200));
		scroll.setVerticalScrollBarPolicy(ScrollPaneLayout.VERTICAL_SCROLLBAR_ALWAYS);

		// Adds all panels to frame and sets up frame
		frame = new JFrame();
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(monthWrap, BorderLayout.WEST);
		frame.add(scroll, BorderLayout.EAST);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		// Calendar c=new GregorianCalendar();
		// Date b=c.getTime();
		// SimpleDateFormat fg=new SimpleDateFormat("HH");
		// SimpleDateFormat hh=new SimpleDateFormat("mm");
		// String s=fg.format(b);
		// String m=hh.format(b);
		// System.out.println(m);
		// System.out.println(s);

	}

	/**
	 * draws the event window in the Calendar application
	 * 
	 * @param dayPanel,
	 *            everything related to the particular day is attached to the
	 *            day panel (right part)
	 */
	private void drawDay(JPanel dayPanel) {
		for (int i = 0; i < 48; i++) {
			EventField jt = new EventField(30);
			jt.setBackground(Color.WHITE);
			jt.setEditable(false);

			dayPanel.add(jt);
			eventfields[i] = jt;
		}

		timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
		timePanel.setBackground(Color.WHITE);
		timePanel.setLayout(new GridLayout(24, 1, 0, 0));
		Calendar c = new GregorianCalendar();
		int sm = c.getActualMaximum(Calendar.HOUR_OF_DAY);
		int s = c.getActualMinimum(Calendar.HOUR_OF_DAY);
		// for(int i=s+1;i<=sm+1;i++)
		for (int i = 0; i < 24; i++)
		// for(int i=0;i<TIME.length;i++)
		{
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));
			p.setBackground(Color.WHITE);
			p.setLayout(new GridLayout(2, 1, 0, 0));
			j = new JTextField(5);
			j.setEditable(false);

			int hour = i;
			String m = "PM";
			if (hour < 12) {
				m = "AM";
			}
			if (hour == 0) {
				hour += 12;
			}
			if (hour > 12) {
				hour -= 12;
			}
			j.setText("" + hour + m);
			// j.setText(i+":"+"00");
			// j.setText(TIME[i]);
			j.setHorizontalAlignment(JTextField.RIGHT);
			j.setBorder(null);
			JTextField o = new JTextField(5);
			p.add(j);
			o.setBorder(null);
			o.setEditable(false);
			p.add(o);
			p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			timePanel.add(p);
		}

		ArrayList<Event> todaysEvents = model.getEvents();

		for (int i = 0; i < events.size(); i++) {

			Event e = todaysEvents.get(i);
			if (e.start.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {

				Date startDate = e.start.getTime();
				Date endDate = e.end.getTime();

				SimpleDateFormat sf = new SimpleDateFormat("hh:mm aa");

				SimpleDateFormat fg = new SimpleDateFormat("HH");
				String starttimehour = fg.format(startDate);
				int starthour = Integer.parseInt(starttimehour);

				SimpleDateFormat hh = new SimpleDateFormat("mm");
				String starttimemin = hh.format(startDate);
				int startmin = Integer.parseInt(starttimemin);

				String endtime = sf.format(endDate);
				// int endt=Integer.parseInt(endtime);

				int index = starthour * 2;
				if (startmin >= 30) {
					index++;
				}
				eventfields[index].setText(e.eventname +" "+ sf.format(startDate) + " - " + sf.format(endDate));
				eventfields[index].setEvent(e);

				index++;
				// if(endtime != null)
				{
					String endtimehour = fg.format(endDate);
					int endhour = Integer.parseInt(endtimehour);

					// String endtimemin=hh.format(endtime);
					// int endmin=Integer.parseInt(endtimemin);
					while (index / 2 < endhour) {
						eventfields[index].setText(e.eventname +" "+ sf.format(startDate) + " - " + sf.format(endDate));
						eventfields[index].setEvent(e);
						index++;
					}
					{
						eventfields[index].setText(e.eventname +" "+ sf.format(startDate) + " - " + sf.format(endDate));
						eventfields[index].setEvent(e);
						index++;
					}
					{
						eventfields[index].setText(e.eventname +" "+ sf.format(startDate) + " - " + sf.format(endDate));
						eventfields[index].setEvent(e);
						index++;
					}
				}

			}
		}

	}
	public void deleteEvent()
	{
		int index=0;
		
	}

	/**
	 * This forces the repainting of all variable items in main view. It starts
	 * by removing all previous items then redrawing with updated data
	 */
	public void repaint() {
		monthPanel.removeAll();
		drawMonth(monthPanel);
		monthPanel.revalidate();
		monthPanel.repaint();

		dayPanel.removeAll();
		timePanel.removeAll();
		drawDay(dayPanel);
		dayPanel.revalidate();
		dayPanel.repaint();
		// timePanel.revalidate();
		// timePanel.repaint();

	}

	/**
	 * Takes a panel and populates it with the month set in the data model.
	 * 
	 * @param monthPanel,
	 *            everything related to the monthly calendar is attached to
	 *            month panel( left part)
	 */
	private void drawMonth(JPanel monthPanel) {

		monthLabel.setText(new SimpleDateFormat("MMM yyyy").format(cal.getTime()));

		// Add Week Labels at top of Month View
		String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (int i = 0; i < 7; i++) {
			// JLabel day = new JLabel("<html><u>" + daysOfWeek[i] +
			// "</u></html>");

			JLabel day = new JLabel(daysOfWeek[i]);

			monthPanel.add(day);
		}

		// Add days in month
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		Calendar getStart = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		int startDay = getStart.get(Calendar.DAY_OF_WEEK);

		for (int i = 1; i < daysInMonth + startDay; i++) {
			if (i < startDay) {
				final JLabel day = new JLabel("");

				monthPanel.add(day);
			} else {
				int dayNumber = i - startDay + 1;
				final JLabel day = new JLabel(dayNumber + "");
				day.addMouseListener(new MouseListener() {

					// CONTROLLER updates the model on the currently looked day
					@Override
					public void mouseClicked(MouseEvent e) {
						int num = Integer.parseInt(day.getText());
						model.setDay(num);

					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}
				});
				if (dayNumber == cal.get(Calendar.DAY_OF_MONTH)) {
					day.setBorder(BorderFactory.createLineBorder(Color.blue));
				}
				monthPanel.add(day);
			}
		}
	}

}