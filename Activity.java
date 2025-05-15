
public class Activity {

    private String activityName;
    private double activityCalorieValue;
    private double hours;

    public Activity(String n, double c) {
        activityName = n;
        activityCalorieValue = c;
        hours = 1; // default value
    }
    
    public String getActivityName() {
        return activityName;
    }

    public double getActivityCalories() {
        return activityCalorieValue;
    }

    public double getHours() {
        return hours;
    }

    public void updateCalories(double c) {
        activityCalorieValue = c;
    }

    public void updateHours(double h) {
        hours = h;
    }

    // method to update activity name and calorie value
    public void updateActivity(String n, double c) {
        activityName = n;
        activityCalorieValue = c;
    }
}
