package event;

import library.LibraryEvents;

import java.util.ArrayList;

/**
 * This class provides the framework for creating an event - provided by the library -  which users can attend.
 * @author Dominik R Wojtasiewicz, Deyan Naydenov, Peter Daish.
 *
 */
public class Event {
	 
	private String eventID;
    private String title;
    private String date;
    private String time;
    private int maxNumberOfAttending;
    private int currentNumberOfAttending;
    private String description;

    /**
     * A constructor for an Event which initialises parameters and sets default values.
     * @param title
     * The title of the event
     * @param date
     * The date the event will take place.
     * @param time
     * The time of the start of an event.
     * @param maxNumberOfAttending
     * The maximum number of possible attendees.
     * @param description
     * The description of the event.
     */
    public Event(String title, String date, String time, int maxNumberOfAttending,
                 String description) {
        this.eventID = generateEventID();
        this.title = title;
        this.date = date;
        this.time = time;
        this.maxNumberOfAttending = maxNumberOfAttending;
        this.currentNumberOfAttending = 0;
        this.description = description;
        System.out.print("New event created");
    }

    /**
     * A constructor for an Event which initialises all parameters.
     * @param eventID
     * The unique id of the event.
     * @param title
     * The title of this event.
     * @param date
     * The date this event will take place on.
     * @param time
     * The time of this event.
     * @param maxNumberOfAttending
     * The maximum number of attendees of this event.
     * @param currentNumberOfAttending
     * The current number of attendees to this event.
     * @param description
     * The description of this event.
     */
    public Event(String eventID, String title, String date, String time, int maxNumberOfAttending, int currentNumberOfAttending,
                 String description){
        this.eventID = eventID;
        this.title = title;
        this.date = date;
        this.time = time;
        this.maxNumberOfAttending = maxNumberOfAttending;
        this.currentNumberOfAttending = currentNumberOfAttending;
        this.description = description;
        System.out.print("New event created");
    }

    /**
     * Returns this event's unique id.
     * @return eventID this event's unique id.
     */
    public String getEventID() {
    	return this.eventID;
    }

    /**
     * Returns the date this event takes place on.
     * @return date the date this event takes place on.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date this event will take place on.
     * @param date the date this event will take place on.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the title of this event
     * @return title the title of this event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this event.
     * @param title the title of this event.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the start time of this event.
     * @return time the start time of this event.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the start time of this event.
     * @param time the start time of this event.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns the maximum possible number of attendees.
     * @return maxNumberOfAttending the maximum number which can attend.
     */
    public int getMaxNumberOfAttending() {
        return maxNumberOfAttending;
    }

    /**
     * Sets the maximum possible number of attendees.
     * @param maxNumberOfAttending the maximum number which can attend this event.
     */
    public void setMaxNumberOfAttending(int maxNumberOfAttending) {
        this.maxNumberOfAttending = maxNumberOfAttending;
    }

    /**
     * Returns the current number of attendees for this event.
     * @return currentNumberOfAttending the current number of attendees.
     */
    public int getCurrentNumberOfAttending() {
        return currentNumberOfAttending;
    }

    /**
     * Sets the current number of attendees for this event.
     * @param currentNumberOfAttending the current number of attendees.
     */
    public void setCurrentNumberOfAttending(int currentNumberOfAttending) {
        this.currentNumberOfAttending = currentNumberOfAttending;
    }

    /**
     * Returns the description of this event.
     * @return description the description of this event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this event.
     * @param description the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds a response by user - to attend.
     */
    public void RSVP(){
        this.currentNumberOfAttending += 1;
    }

    /**
     * Generates the unique id of this event.
     * @return A string containing the unique id of this event.
     */
    public String generateEventID(){
        ArrayList<Event> list = LibraryEvents.getAllEvents();
        if(list.size() == 0){
            return "EventID:0";
        }
        int eventID = Integer.valueOf(list.get(list.size()-1).getEventID().split(":")[1]) + 1;
        return "EventID:" + String.valueOf(eventID);
    }
}
