package library;

import event.Event;
import io.ReadFile;
import java.util.ArrayList;

/**
 * This class handles the storage and maintenance of an event by the library.
 * @author Dominik R Wojtasiewicz, Deyan Naydenov, Peter Daish.
 *
 */
public class LibraryEvents {

    private static ArrayList<Event> listOfEvents = new ArrayList<>();

    /**
     * Populates the listOfEvents with all currently stored events.
     */
    public static void start(){
        listOfEvents = ReadFile.readEvents();
    }

    /**
     * Adds an event to the listOfEvents.
     * @param event the event to be added to listOfEvents.
     */
    public static void addEvent(Event event){
        listOfEvents.add(event);
    }

    /**
     * Returns a list of all the events held by the library.
     * @return listOfEvents the list of events being held by the library.
     */
    public static ArrayList<Event> getAllEvents(){
        return listOfEvents;
    }

    /**
     * Returns a specific event.
     * @param name the name of the specific event.
     * @return The event - if present - which is specified by the name/title
     */
    public static Event getEvent(String eventID){
        for(Event event : listOfEvents){
            if(event.getEventID().equals(eventID)){
                return event;
            }
        }
        return null;
    }
} 
