package resources;

import java.util.HashMap;

public class ResourceStatData {
    private HashMap<String, Integer> dateTimesBorrowed;

    public ResourceStatData(){
        dateTimesBorrowed = new HashMap<>();
    }

    public void addBorrowDate(String date){
        dateTimesBorrowed.putIfAbsent(date, 0);
        int timesBorrowed = dateTimesBorrowed.getOrDefault(date, 0) + 1;
        dateTimesBorrowed.replace(date, timesBorrowed);
    }

    public int getTotalTimesBorrowed(){
        int total = 0;
        for (int value : dateTimesBorrowed.values()){
            total += value;
        }

        return total;
    }

    public int getTimesBorrowed(String date){
        return dateTimesBorrowed.get(date);
    }
}
