package event;

import library.LibraryEvents;

import java.util.ArrayList;

public class Event {
	
	private String eventID;
    private String title;
    private String date;
    private String time;
    private int maxNumberOfAttending;
    private int currentNumberOfAttending;
    private String description;

    public Event(String title, String date, String time, int maxNumberOfAttending,
                 String description){
        this.eventID = generateEventID();
        this.title = title;
        this.date = date;
        this.time = time;
        this.maxNumberOfAttending = maxNumberOfAttending;
        this.currentNumberOfAttending = 0;
        this.description = description;
        System.out.print("New event created");
    }

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

    public String getEventID() {
    	return this.eventID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMaxNumberOfAttending() {
        return maxNumberOfAttending;
    }

    public void setMaxNumberOfAttending(int maxNumberOfAttending) {
        this.maxNumberOfAttending = maxNumberOfAttending;
    }

    public int getCurrentNumberOfAttending() {
        return currentNumberOfAttending;
    }

    public void setCurrentNumberOfAttending(int currentNumberOfAttending) {
        this.currentNumberOfAttending = currentNumberOfAttending;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void RSVP(){
        this.currentNumberOfAttending += 1;
    }

    public String generateEventID(){
        ArrayList<Event> list = LibraryEvents.getAllEvents();
        if(list.size() == 0){
            return "EventID:0";
        }
        int eventID = Integer.valueOf(list.get(list.size()-1).getEventID().split(":")[1]) + 1;
        return "EventID:" + String.valueOf(eventID);
    }
}
