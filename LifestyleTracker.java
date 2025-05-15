
import java.util.ArrayList;
import java.util.List;

public class LifestyleTracker {

    private List<Food> foods;
    private List<Activity> activities;
    private List<String> foodReport;
    private List<String> activityReport;
    private double totalCaloriesConsumed;
    private double totalCaloriesBurned;

    public LifestyleTracker() {
        foods = new ArrayList<>();
        activities = new ArrayList<>();
        foodReport = new ArrayList<>();
        activityReport = new ArrayList<>();
        totalCaloriesConsumed = 0;
        totalCaloriesBurned = 0;
    }

    public String addFood(String n, double c) {
        for (Food food : foods) {
            if (food.getFoodName().equals(n)) {
                food.updateCalories(c);
                return "Updated Food " + n + " with " + c + " kcal";
            }
        }
        Food newFood = new Food(n, c);
        foods.add(newFood);
        return "Added Food " + n + " with " + c + " kcal";
    }

    public String addActivity(String n, double c) {
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(n)) {
                activity.updateCalories(c);
                return "Updated Activity " + n + " with " + c + " kcal";
            }
        }
        Activity newActivity = new Activity(n, c);
        activities.add(newActivity);
        return "Added Activity " + n + " with " + c + " kcal";
    }

    public String eat(String foodName, double servings) {
        Food food = findFood(foodName);
        if (food == null) {
            return "The specified food does not exist. Do you want to create a new food?";
        }
        double caloriesConsumed = servings * food.getFoodCalories();
        totalCaloriesConsumed += caloriesConsumed;
        foodReport.add("Ate " + servings + " serving(s) of " + foodName + ", " + caloriesConsumed + " kcal");
        return "Ate " + servings + " serving(s) of " + foodName + ", " + caloriesConsumed + " kcal";
    }

    public String perform(String actName, double hours) {
        Activity activity = findActivity(actName);
        if (activity == null) {
            return "The specified activity does not exist. Do you want to create a new activity?";
        }
        double caloriesBurned = hours * activity.getActivityCalories();
        totalCaloriesBurned += caloriesBurned;
        activity.updateHours(hours);
        activityReport.add("Performed " + hours + " hour(s) of " + actName + ", " + caloriesBurned + " kcal");
        return "Performed " + hours + " hour(s) of " + actName + ", " + caloriesBurned + " kcal";
    }

    public Food findFood(String foodName) {
        for (Food food : foods) {
            if (food.getFoodName().equals(foodName)) {
                return food;
            }
        }
        return null;
    }

    public Activity findActivity(String activityName) {
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    public String report() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("----------------\n");
        reportBuilder.append("LIFESTYLE REPORT\n");
        reportBuilder.append("----------------\n");
        reportBuilder.append("Food Consumed:\n");
        for (String report : foodReport) {
            reportBuilder.append(report).append("\n");
        }
        reportBuilder.append("----------------\n");
        reportBuilder.append("Total Calories Consumed: ").append(totalCaloriesConsumed).append(" kcal\n");
        reportBuilder.append("----------------\n");
        reportBuilder.append("Activities Performed:\n");
        for (String report : activityReport) {
            reportBuilder.append(report).append("\n");
        }
        reportBuilder.append("----------------\n");
        reportBuilder.append("Total Calories Burned: ").append(totalCaloriesBurned).append(" kcal\n");
        reportBuilder.append("----------------\n");
        double netCalories = totalCaloriesConsumed - totalCaloriesBurned;
        reportBuilder.append("Net Calories for the Day: ").append(netCalories).append(" kcal\n");

        double weightLossPerKcal = 0.00012959782; // 1 kcal = 0.00012959782 kg
        double weightLossPerWeek = netCalories * weightLossPerKcal * 7;
        double weightLossPerMonth = netCalories * weightLossPerKcal * 30;
        double weightLossPerThreeMonths = netCalories * weightLossPerKcal * 90;
        double weightLossPerSixMonths = netCalories * weightLossPerKcal * 180;

        reportBuilder.append("If you keep up this lifestyle...\n");
        reportBuilder.append("In a week, you will ").append(netCalories >= 0 ? "gain" : "lose").append(" ").append(String.format("%.2f", Math.abs(weightLossPerWeek))).append(" kilograms.\n");
        reportBuilder.append("In a month, you will ").append(netCalories >= 0 ? "gain" : "lose").append(" ").append(String.format("%.2f", Math.abs(weightLossPerMonth))).append(" kilograms.\n");
        reportBuilder.append("In 3 months, you will ").append(netCalories >= 0 ? "gain" : "lose").append(" ").append(String.format("%.2f", Math.abs(weightLossPerThreeMonths))).append(" kilograms.\n");
        reportBuilder.append("In 6 months, you will ").append(netCalories >= 0 ? "gain" : "lose").append(" ").append(String.format("%.2f", Math.abs(weightLossPerSixMonths))).append(" kilograms.\n");

        return reportBuilder.toString();
    }

    public String deleteFoodReportEntry(int index) {
        if (index < 1 || index > foodReport.size()) {
            return "Invalid index!";
        }
        String entry = foodReport.remove(index - 1);
        String[] parts = entry.split(", ");
        double calories = Double.parseDouble(parts[1].split(" ")[0]);
        totalCaloriesConsumed -= calories;
        return "Deleted Food Report Entry: " + entry;
    }

    public String deleteActivityReportEntry(int index) {
        if (index < 1 || index > activityReport.size()) {
            return "Invalid index!";
        }
        String entry = activityReport.remove(index - 1);
        String[] parts = entry.split(", ");
        double calories = Double.parseDouble(parts[1].split(" ")[0]);
        totalCaloriesBurned -= calories;
        return "Deleted Activity Report Entry: " + entry;
    }

    public void displayFoodAndActivities() {
        System.out.println("Food Report:");
        int i = 1;
        for (String report : foodReport) {
            System.out.println(i + ": " + report);
            i++;
        }
        System.out.println("Activity Report:");
        i = 1;
        for (String report : activityReport) {
            System.out.println(i + ": " + report);
            i++;
        }
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<String> getFoodReports() {
        return foodReport;
    }

    public List<String> getActivityReports() {
        return activityReport;
    }

    // methods for updating food and activity records
    public String updateFoodReportEntry(int index, String newName, double newCalories, double newServings) {
        if (index < 1 || index > foodReport.size()) {
            return "Invalid index!";
        }
        String entry = foodReport.get(index - 1);
        String[] parts = entry.split(", ");
        double oldCalories = Double.parseDouble(parts[1].split(" ")[0]);
        totalCaloriesConsumed -= oldCalories;
        totalCaloriesConsumed += newServings * newCalories;
        foodReport.set(index - 1, "Ate " + newServings + " serving(s) of " + newName + ", " + newServings * newCalories + " kcal");
        return "Updated Food Report Entry: " + foodReport.get(index - 1);
    }
    

    public String updateActivityReportEntry(int index, String newName, double newCalories, double newHours) {
        if (index < 1 || index > activityReport.size()) {
            return "Invalid index!";
        }
        String entry = activityReport.get(index - 1);
        String[] parts = entry.split(", ");
        double oldCalories = Double.parseDouble(parts[1].split(" ")[0]);
        totalCaloriesBurned -= oldCalories;
        totalCaloriesBurned += newHours * newCalories;
        activityReport.set(index - 1, "Performed " + newHours + " hour(s) of " + newName + ", " + newHours * newCalories + " kcal");
        return "Updated Activity Report Entry: " + activityReport.get(index - 1);
    }
}
