
import javax.swing.*;

public class TrackerApp {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the name of the user as a command line argument.");
            return;
        }

        LifestyleTracker tracker = new LifestyleTracker();
        TrackerGUI trackerGUI = new TrackerGUI(tracker);
        trackerGUI.setVisible(true);

        String name = args[0];
        JOptionPane.showMessageDialog(null, "Welcome to " + name + "'s Lifestyle Tracker!");
    }
}
