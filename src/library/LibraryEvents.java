package library;

import event.Event;
import io.ReadFile;

import java.util.ArrayList;

public class LibraryEvents {

    private static ArrayList<Event> listOfEvents = new ArrayList<>();

    public static void start(){
        listOfEvents = ReadFile.readEvents();
    }

    public static void addEvent(Event event){
        listOfEvents.add(event);
    }

    public static ArrayList<Event> getAllEvents(){
        return listOfEvents;
    }

    public static Event getEvent(String name){
        for(Event event : listOfEvents){
            if(event.getTitle().equals(name)){
                return event;
            }
        }
        return null;
    }
}
