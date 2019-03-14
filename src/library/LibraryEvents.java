package library;

import event.Event;

import java.util.ArrayList;

public class LibraryEvents {

    private static ArrayList<Event> listOfEvents = new ArrayList<>();

    public static void start(){
        //TODO listOfEvents = ReadFile.readEvents();
    }

    public static void addEvent(Event event){
        listOfEvents.add(event);
    }

    public static ArrayList<Event> getAllEvents(){
        return listOfEvents;
    }

    public static Event getEvent(String name){
        for(Event event : listOfEvents){
            if(event.getTime().equals(name)){
                return event;
            }
        }
        return null;
    }
}
