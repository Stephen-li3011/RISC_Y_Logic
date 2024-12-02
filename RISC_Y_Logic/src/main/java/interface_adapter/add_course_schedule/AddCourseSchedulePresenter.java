package interface_adapter.add_course_schedule;

import use_case.add_course_schedule.AddCourseScheduleOutputBoundary;
import use_case.add_course_schedule.AddCourseScheduleOutputData;

import javax.swing.*;

public class AddCourseSchedulePresenter implements AddCourseScheduleOutputBoundary {

    @Override
    public void present(AddCourseScheduleOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage(), "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
