
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



/**
 * This class saves the events to events.txt when program terminates, and also
 * loads the events from the text file.
 * 
 * @author Arselan
 *
 */
public class EventsFiler {
	static EventModel model;
	static ArrayList<Event> events = model.getEvents();

	/**
	 * loads the events from the text file
	 */
	public static void loadEvent() {
		Scanner s = null;
		try {
			s = new Scanner(new File("C:\\Users\\Arselan\\Desktop\\events.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()) {
			list.add(s.nextLine());
		}

		s.close();
		for (String l : list) {
			System.out.println(l);
		}

	}

	/**
	 * saves the event to the text file
	 * 
	 * @param eve
	 *            , an event
	 */
//	public static void saveEvents() {
//		try {
//			URL path = myCalendar.class.getResource("events.txt");
//			System.out.println(myCalendar.class.getResource("events.txt"));
//			try {
//				File schedule = new File(path.getFile());
//				boolean exists = schedule.exists();
//				if (exists == true) {
//					PrintWriter writer = new PrintWriter(schedule, "UTF-8");
//
//					for (int i = 0; i < events.size(); i++) {
//						Event j = events.get(i);
//						Date startDate = j.start.getTime();
//						Date endDate = j.end.getTime();
//
//						SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
//						writer.print(j.eventname);
//						writer.print("@");
//						writer.print(sf.format(startDate));
//						writer.print("@");
//						writer.print(sf.format(endDate));
//						// if(events.get(i).getEndTime() != null)
//						// {
//						// writer.print("@");
//						// writer.println(events.get(i).getEndTime());
//						// }
//						// else
//						// {
//						// writer.println();
//						// }
//					}
//					writer.close();
//				}
//			} catch (NullPointerException e) {
//				System.out.println("x");
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("Error writing to file");
//		} catch (UnsupportedEncodingException e) {
//			System.out.println("Error unsupported encoding exception while writing to file");
//		}
//	}
	 public static void saveEvent(Event eve) {
	 ArrayList<Event> ee = new ArrayList<Event>();
	// ArrayList<Event> eventss=model.getEvents();
	 for (int i = 0; i < events.size(); i++) {
	 ee.add(events.get(i));
	 }
	 for(int i=0;i<ee.size();i++)
	 {
	 System.out.println(i);
	 }
	 File f = new File("C:\\Users\\Arselan\\Desktop\\events.txt");
	 if (!f.exists()) {
	 try {
	 f.createNewFile();
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 }
	 FileWriter fw = null;
	 try {
	 fw = new FileWriter(f.getAbsoluteFile());
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 BufferedWriter bw = new BufferedWriter(fw);
	
	 for (Event s : ee) {
	 try {
	 bw.write(s.getEventString(s) + System.getProperty("line.separator"));
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 }
	 try {
	 bw.close();
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	
	
	 }
}
