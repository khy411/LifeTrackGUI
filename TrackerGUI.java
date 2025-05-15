import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackerGUI extends JFrame {

    private JTextField foodNameField, foodCaloriesField, activityNameField, activityCaloriesField, servingsField, hoursField, eatFoodField, performActivityField;
    private JTextArea reportArea;
    private JList<String> foodReportList, activityReportList;
    private DefaultListModel<String> foodReportListModel, activityReportListModel;

    private LifestyleTracker tracker;

    public TrackerGUI(LifestyleTracker tracker) {
        this.tracker = tracker;
        setTitle("Lifestyle Tracker");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // food section
        JLabel foodLabel = new JLabel("Food:");
        foodLabel.setBounds(20, 20, 100, 20);
        add(foodLabel);

        JLabel foodNameLabel = new JLabel("Name:");
        foodNameLabel.setBounds(20, 50, 100, 20);
        add(foodNameLabel);

        foodNameField = new JTextField();
        foodNameField.setBounds(120, 50, 150, 20);
        add(foodNameField);

        JLabel foodCaloriesLabel = new JLabel("Calories:");
        foodCaloriesLabel.setBounds(20, 80, 100, 20);
        add(foodCaloriesLabel);

        foodCaloriesField = new JTextField();
        foodCaloriesField.setBounds(120, 80, 150, 20);
        add(foodCaloriesField);

        JButton addFoodButton = new JButton("Add Food");
        addFoodButton.setBounds(20, 120, 100, 30);
        add(addFoodButton);
        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = foodNameField.getText();
                double calories = Double.parseDouble(foodCaloriesField.getText());
                String message = tracker.addFood(foodName, calories);
                JOptionPane.showMessageDialog(null, message);
                updateFoodReportList();
            }
        });

        // activity section
        JLabel activityLabel = new JLabel("Activity:");
        activityLabel.setBounds(300, 20, 100, 20);
        add(activityLabel);

        JLabel activityNameLabel = new JLabel("Name:");
        activityNameLabel.setBounds(300, 50, 100, 20);
        add(activityNameLabel);

        activityNameField = new JTextField();
        activityNameField.setBounds(400, 50, 150, 20);
        add(activityNameField);

        JLabel activityCaloriesLabel = new JLabel("Calories:");
        activityCaloriesLabel.setBounds(300, 80, 100, 20);
        add(activityCaloriesLabel);

        activityCaloriesField = new JTextField();
        activityCaloriesField.setBounds(400, 80, 150, 20);
        add(activityCaloriesField);

        JButton addActivityButton = new JButton("Add Activity");
        addActivityButton.setBounds(300, 120, 120, 30);
        add(addActivityButton);
        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String activityName = activityNameField.getText();
                double calories = Double.parseDouble(activityCaloriesField.getText());
                String message = tracker.addActivity(activityName, calories);
                JOptionPane.showMessageDialog(null, message);
            }
        });

        // eating section
        JLabel servingsLabel = new JLabel("Servings:");
        servingsLabel.setBounds(20, 160, 100, 20);
        add(servingsLabel);

        servingsField = new JTextField();
        servingsField.setBounds(120, 160, 150, 20);
        add(servingsField);

        JLabel eatFoodLabel = new JLabel("Food:");
        eatFoodLabel.setBounds(20, 200, 100, 20);
        add(eatFoodLabel);

        eatFoodField = new JTextField();
        eatFoodField.setBounds(120, 200, 150, 20);
        add(eatFoodField);

        JButton eatButton = new JButton("Eat");
        eatButton.setBounds(20, 240, 100, 30);
        add(eatButton);
        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = eatFoodField.getText();
                double servings = Double.parseDouble(servingsField.getText());
                String message = tracker.eat(foodName, servings);
                if (message.startsWith("The specified food does not exist")) {
                    int result = JOptionPane.showConfirmDialog(null, "The specified food does not exist. Do you want to create a new food?", "Food Not Found", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        double calories = Double.parseDouble(JOptionPane.showInputDialog("Enter calories for the new food:"));
                        tracker.addFood(foodName, calories);
                        JOptionPane.showMessageDialog(null, "Food added. Now you can eat it.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, message);
                    updateFoodReportList();
                }
            }
        });

        // performing section
        JLabel hoursLabel = new JLabel("Hours:");
        hoursLabel.setBounds(300, 160, 100, 20);
        add(hoursLabel);

        hoursField = new JTextField();
        hoursField.setBounds(400, 160, 150, 20);
        add(hoursField);

        JLabel performActivityLabel = new JLabel("Activity:");
        performActivityLabel.setBounds(300, 200, 100, 20);
        add(performActivityLabel);

        performActivityField = new JTextField();
        performActivityField.setBounds(400, 200, 150, 20);
        add(performActivityField);

        JButton performButton = new JButton("Perform");
        performButton.setBounds(300, 240, 120, 30);
        add(performButton);
        performButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String activityName = performActivityField.getText();
                double hours = Double.parseDouble(hoursField.getText());
                String message = tracker.perform(activityName, hours);
                if (message.startsWith("The specified activity does not exist")) {
                    int result = JOptionPane.showConfirmDialog(null, "The specified activity does not exist. Do you want to create a new activity?", "Activity Not Found", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        double calories = Double.parseDouble(JOptionPane.showInputDialog("Enter calories for the new activity:"));
                        tracker.addActivity(activityName, calories);
                        JOptionPane.showMessageDialog(null, "Activity added. Now you can perform it.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, message);
                    updateActivityReportList();
                }
            }
        });

        // report section
        JButton reportButton = new JButton("Generate Report");
        reportButton.setBounds(20, 280, 150, 30);
        add(reportButton);
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReport();
            }
        });

        reportArea = new JTextArea();
        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        reportScrollPane.setBounds(20, 320, 740, 200);
        add(reportScrollPane);

        // food report List
        foodReportListModel = new DefaultListModel<>();
        foodReportList = new JList<>(foodReportListModel);
        JScrollPane foodReportScrollPane = new JScrollPane(foodReportList);
        foodReportScrollPane.setBounds(20, 530, 350, 100);
        add(foodReportScrollPane);

        JButton deleteFoodReportButton = new JButton("Delete Food Report Entry");
        deleteFoodReportButton.setBounds(20, 640, 200, 30);
        add(deleteFoodReportButton);
        deleteFoodReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = foodReportList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tracker.deleteFoodReportEntry(selectedIndex + 1);
                    updateFoodReportList();
                }
            }
        });

        JButton editFoodReportButton = new JButton("Edit Food Report Entry");
        editFoodReportButton.setBounds(240, 640, 200, 30);
        add(editFoodReportButton);
        editFoodReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = foodReportList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String newName = JOptionPane.showInputDialog("Enter new food name:");
                    double newCalories = Double.parseDouble(JOptionPane.showInputDialog("Enter new calories:"));
                    double newServings = Double.parseDouble(JOptionPane.showInputDialog("Enter new servings:"));
                    tracker.updateFoodReportEntry(selectedIndex + 1, newName, newCalories, newServings);
                    updateFoodReportList();
                }
            }
        });

        // activity report List
        activityReportListModel = new DefaultListModel<>();
        activityReportList = new JList<>(activityReportListModel);
        JScrollPane activityReportScrollPane = new JScrollPane(activityReportList);
        activityReportScrollPane.setBounds(400, 530, 350, 100);
        add(activityReportScrollPane);

        JButton deleteActivityReportButton = new JButton("Delete Activity Report Entry");
        deleteActivityReportButton.setBounds(400, 640, 200, 30);
        add(deleteActivityReportButton);
        deleteActivityReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = activityReportList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tracker.deleteActivityReportEntry(selectedIndex + 1);
                    updateActivityReportList();
                }
            }
        });

        JButton editActivityReportButton = new JButton("Edit Activity Report Entry");
        editActivityReportButton.setBounds(620, 640, 200, 30);
        add(editActivityReportButton);
        editActivityReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = activityReportList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String newName = JOptionPane.showInputDialog("Enter new activity name:");
                    double newCalories = Double.parseDouble(JOptionPane.showInputDialog("Enter new calories:"));
                    double newHours = Double.parseDouble(JOptionPane.showInputDialog("Enter new hours:"));
                    tracker.updateActivityReportEntry(selectedIndex + 1, newName, newCalories, newHours);
                    updateActivityReportList();
                }
            }
        });

        // initial updates for report lists
        updateFoodReportList();
        updateActivityReportList();
    }

    void updateFoodReportList() {
        foodReportListModel.clear();
        for (String report : tracker.getFoodReports()) {
            foodReportListModel.addElement(report);
        }
    }

    void updateActivityReportList() {
        activityReportListModel.clear();
        for (String report : tracker.getActivityReports()) {
            activityReportListModel.addElement(report);
        }
    }

    void updateReport() {
        reportArea.setText(tracker.report());
    }
}