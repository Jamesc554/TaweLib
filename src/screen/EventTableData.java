package screen;
import javafx.beans.property.SimpleStringProperty;


public class EventTableData {
	private final SimpleStringProperty eventID;
	private final SimpleStringProperty title;
	private final SimpleStringProperty date;
	private final SimpleStringProperty time;
	private final SimpleStringProperty eventAttendees;
	private final SimpleStringProperty description;
	private final SimpleStringProperty attending;
	

	public EventTableData(String eventID, String title, String date,
			String time, String eventAttendees, String description, String attending) {
		
		this.eventID = new SimpleStringProperty(eventID);
		this.title = new SimpleStringProperty(title);
		this.date = new SimpleStringProperty(date);
		this.time = new SimpleStringProperty(time);
		this.eventAttendees = new SimpleStringProperty(eventAttendees);
		this.description = new SimpleStringProperty(description);
		this.attending = new SimpleStringProperty(attending);
	}

	/**
	 * Gets the resource's ID.
	 * @return resourceID
	 * The resource's ID.
	 */
	public String getEventID() {
		return eventID.get();
	}
	
	/**
	 * Gets the resource's Title.
	 * @return resourceTitle
	 * The resource's Title.
	 */
	public String getTitle() {
		return title.get();
	}
	
	/**
	 * Gets the date the copy of the resource was borrowed on.
	 * @return borrowDate
	 * The date the resource was borrowed.
	 */
	public String getDate() {
		return date.get();
	}
	
	/**
	 * Gets the date the copy of the resources was returned on.
	 * @return returnDate
	 * The date the copy was returned.
	 */
	public String getTime() {
		return time.get();
	}
	
	/**
	 * Gets whether the copy is overdue.
	 * @return overdue
	 * Whether the copy is overdue (returned after the returnDate).
	 */
	public String getDescription() {
		return description.get();
	}
	
	/**
	 * Gets the current list of event attendees.
	 * @return eventAttendees the list of event attendees (String)
	 */
	public String getEventAttendees() {
		return eventAttendees.get();
	}

	/**
	 * Gets the current attending information.
	 * @returns attending (String)
	 */
	public String getAttending(){
		return attending.get();
	}
}
