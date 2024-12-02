package view.add_course_schedule;

import interface_adapter.add_course_schedule.AddCourseScheduleController;
import use_case.add_course_schedule.AddCourseScheduleDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class AddCourseScheduleView {

    private AddCourseScheduleDataAccessInterface repository;
    private AddCourseScheduleController controller;

    public AddCourseScheduleView(AddCourseScheduleDataAccessInterface repository, AddCourseScheduleController controller) {

        this.repository = repository;
        this.controller = controller;


        JFrame frame = new JFrame("Add Course Schedule");
        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Day of the Week (e.g., Monday):"));
        JTextField dayField = new JTextField();
        panel.add(dayField);

        panel.add(new JLabel("Course Name:"));
        JTextField courseNameField = new JTextField();
        panel.add(courseNameField);

        panel.add(new JLabel("Start Time (HH:mm):"));
        JTextField startTimeField = new JTextField();
        panel.add(startTimeField);

        panel.add(new JLabel("End Time (HH:mm):"));
        JTextField endTimeField = new JTextField();
        panel.add(endTimeField);

        panel.add(new JLabel("Semester:"));
        String[] semesters = {"Fall", "Winter", "Summer"};
        JComboBox<String> semesterComboBox = new JComboBox<>(semesters);
        panel.add(semesterComboBox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Add Course Schedule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String day = dayField.getText().trim();
                String courseName = courseNameField.getText().trim();
                String startTime = startTimeField.getText().trim();
                String endTime = endTimeField.getText().trim();
                String semester = (String) semesterComboBox.getSelectedItem();

                LocalTime startTimeInLocalTime = LocalTime.parse(startTime);
                LocalTime endTimeInLocalTime = LocalTime.parse(endTime);

                controller.execute(courseName, day, startTimeInLocalTime , endTimeInLocalTime , semester);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
