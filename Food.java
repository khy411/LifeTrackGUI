

public class Food {

    private String foodName;
    private double foodCalorieValue;
    private double servings;

    public Food(String f, double c) {
        foodName = f;
        foodCalorieValue = c;
        servings = 0; // default value
    }

    public String getFoodName() {
        return foodName;
    }

    public double getFoodCalories() {
        return foodCalorieValue;
    }

    public double getServings() {
        return servings;
    }

    public void updateCalories(double c) {
        foodCalorieValue = c;
    }

    public void updateServings(double s) {
        servings = s;
    }

    // method to update food name and calorie value
    public void updateFood(String n, double c) {
        foodName = n;
        foodCalorieValue = c;
    }
}
