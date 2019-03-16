package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public int getTimeBorrowedWithin(int numDays){
        int numTimes = 0;
        SimpleDateFormat dateFormatDMY = new SimpleDateFormat("dd-MM-yyyy");

        for (String date : dateTimesBorrowed.keySet()){
            Date borrowDate = null;

            try {
                borrowDate = dateFormatDMY.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (borrowDate != null){
                if (isWithinDate(new Date(), numDays, borrowDate)){
                    numTimes++;
                }
            }
        }

        return numTimes;
    }

    /**
     * Check if a date is within a given date and number of days
     * @param startDate The start Date
     * @param numDays How many days to check between
     * @param date The Date being checked
     * @return If the date is between startDate + numDays
     */
    private boolean isWithinDate(Date startDate, int numDays, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, -numDays);
        Date endDate = c.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String endDateS = dateFormat.format(endDate);
        try {
            endDate = dateFormat.parse(endDateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date.toString() + " - " + startDate.toString() + " - " + endDate.toString());

        System.out.println(date.before(startDate) && date.after(endDate));

        return (date.before(startDate) && date.after(endDate));
    }
}
