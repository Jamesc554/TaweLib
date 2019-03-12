package resources;

import java.util.HashMap;

/**
 * <h1>ResourceStatData</h1>
 * <p>A helper class to keep track of borrwing stats for users</p>
 * @author ames Carter.
 * @version 1.0
 */
public class ResourceStatData {
    private HashMap<String, Integer> dateTimesBorrowed;

    public ResourceStatData(){
        dateTimesBorrowed = new HashMap<>();
    }

    /**
     * Adds a date the the hashmap, increasing the value if it is already in the map
     * @param date Date too add
     */
    public void addBorrowDate(String date){
        dateTimesBorrowed.putIfAbsent(date, 0);
        int timesBorrowed = dateTimesBorrowed.getOrDefault(date, 0) + 1;
        dateTimesBorrowed.replace(date, timesBorrowed);
    }

    /**
     * Get how many times this resource has been borrowed
     *
     * @return How many times this resource was borrowed
     */
    public int getTotalTimesBorrowed(){
        int total = 0;
        for (int value : dateTimesBorrowed.values()){
            total += value;
        }

        return total;
    }
}
