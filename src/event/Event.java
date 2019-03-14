package event;

import java.util.ArrayList;

public class Event {

    private String title;
    private String date;
    private String time;
    private int maxNumberOfAttending;
    private int currentNumberOfAttending;
    private String description;

    public Event(String title, String date, String time, int maxNumberOfAttending, int currentNumberOfAttending,
                 String description){
        this.title = title;
        this.date = date;
        this.time = time;
        this.maxNumberOfAttending = maxNumberOfAttending;
        this.currentNumberOfAttending = currentNumberOfAttending;
        this.description = description;
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

}
