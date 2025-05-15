
import java.util.Scanner;

public class TrackerConsole {
    public static void main(String[] args) {
        if (args.length < 1) { // to prevent program from proceeding without user name input
            System.out.println("Please provide the name of the user as a command line argument.");
            return;
        }

        LifestyleTracker tracker = new LifestyleTracker();
        String name = args[0];
        System.out.println("Welcome to " + name + "'s Lifestyle Tracker!");

        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            System.out.println("Enter command (Food, Activity, Eat, Perform, Report, DeleteFood, DeleteActivity, Display, UpdateFood, UpdateActivity):");
            command = sc.next();

            if (command.equalsIgnoreCase("Food")) {
                String foodName = sc.next();
                double calories = sc.nextDouble();
                System.out.println(tracker.addFood(foodName, calories));
            } else if (command.equalsIgnoreCase("Activity")) {
                String activityName = sc.next();
                double calories = sc.nextDouble();
                System.out.println(tracker.addActivity(activityName, calories));
            } else if (command.equalsIgnoreCase("Eat")) {
                String foodName = sc.next();
                double servings = sc.nextDouble();
                Food food = tracker.findFood(foodName);
                if (food == null) {
                    System.out.println("The specified food does not exist. Do you want to create a new food? (yes/no)");
                    String choice = sc.next();
                    if (choice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter calories for the new food:");
                        double calories = sc.nextDouble();
                        System.out.println(tracker.addFood(foodName, calories));
                        System.out.println(tracker.eat(foodName, servings));
                    } else {
                        System.out.println("Food not added.");
                    }
                } else {
                    System.out.println(tracker.eat(foodName, servings));
                }
            } else if (command.equalsIgnoreCase("Perform")) {
                String activityName = sc.next();
                double hours = sc.nextDouble();
                Activity activity = tracker.findActivity(activityName);
                if (activity == null) {
                    System.out.println("The specified activity does not exist. Do you want to create a new activity? (yes/no)");
                    String choice = sc.next();
                    if (choice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter calories for the new activity:");
                        double calories = sc.nextDouble();
                        System.out.println(tracker.addActivity(activityName, calories));
                        System.out.println(tracker.perform(activityName, hours));
                    } else {
                        System.out.println("Activity not added.");
                    }
                } else {
                    System.out.println(tracker.perform(activityName, hours));
                }
            } else if (command.equalsIgnoreCase("Report")) {
                System.out.println(tracker.report());
            } else if (command.equalsIgnoreCase("DeleteFood")) {
                System.out.println("Enter index of the food to delete:");
                int index = sc.nextInt();
                System.out.println(tracker.deleteFoodReportEntry(index));
            } else if (command.equalsIgnoreCase("DeleteActivity")) {
                System.out.println("Enter index of the activity to delete:");
                int index = sc.nextInt();
                System.out.println(tracker.deleteActivityReportEntry(index));
            } else if (command.equalsIgnoreCase("Display")) {
                tracker.displayFoodAndActivities();
            } else if (command.equalsIgnoreCase("UpdateFood")) {
                System.out.println("Enter index of the food entry to update:");
                int index = sc.nextInt();
                System.out.println("Enter new food name:");
                String newName = sc.next();
                System.out.println("Enter new calories per serving:");
                double newCalories = sc.nextDouble();
                System.out.println("Enter new number of servings:");
                double newServings = sc.nextDouble();
                System.out.println(tracker.updateFoodReportEntry(index, newName, newCalories, newServings));
            } else if (command.equalsIgnoreCase("UpdateActivity")) {
                System.out.println("Enter index of the activity entry to update:");
                int index = sc.nextInt();
                System.out.println("Enter new activity name:");
                String newName = sc.next();
                System.out.println("Enter new calories burned per hour:");
                double newCalories = sc.nextDouble();
                System.out.println("Enter new number of hours:");
                double newHours = sc.nextDouble();
                System.out.println(tracker.updateActivityReportEntry(index, newName, newCalories, newHours));
            }
        }
    }
}
