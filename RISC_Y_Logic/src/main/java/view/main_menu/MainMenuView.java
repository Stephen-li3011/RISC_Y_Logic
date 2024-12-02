package view.main_menu;

import data_access.InMemoryScheduleRepository;
import entity.EventFactory;
import interface_adapter.add_course_schedule.AddCourseScheduleController;
import interface_adapter.add_course_schedule.AddCourseSchedulePresenter;
import interface_adapter.add_event.AddEventPresenter;
import interface_adapter.add_event.AddRecommendEventController;
import interface_adapter.add_event.AddUserEventController;
import interface_adapter.calendar.CalendarPresenter;
import interface_adapter.mainmenu.MainMenuBackButtonController;
import interface_adapter.mainmenu.MainMenuCalendarController;
import interface_adapter.mainmenu.MainMenuEventsController;
import use_case.add_course_schedule.AddCourseScheduleInteractor;
import use_case.add_event.AddEventInteractor;
import use_case.calendar.CalendarInputBoundary;
import use_case.calendar.CalendarInteractor;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView {
    JFrame frame = new JFrame("Main Menu");
    JButton calendarButton = new MainMenuUIComponentFactory().createButton("Calendar");
    JButton eventsButton = new MainMenuUIComponentFactory().createButton("Events");
    JButton backButton = new MainMenuUIComponentFactory().createButton("Back");

    public MainMenuView() {
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new MainMenuUIComponentFactory().createPanel(new Color(70, 130, 180));

        JLabel titleLabel = new MainMenuUIComponentFactory().createLabel("Main Menu");
        topPanel.add(titleLabel);

        JPanel centrePanel = new MainMenuUIComponentFactory().createPanel(new Color(245, 245, 245));
        centrePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        InMemoryScheduleRepository repository = new InMemoryScheduleRepository();
        AddCourseSchedulePresenter addCourseSchedulePresenter = new AddCourseSchedulePresenter();
        AddCourseScheduleInteractor addCourseScheduleInteractor = new AddCourseScheduleInteractor(repository, addCourseSchedulePresenter);
        AddCourseScheduleController courseScheduleController = new AddCourseScheduleController(addCourseScheduleInteractor);

        AddEventPresenter addEventPresenter = new AddEventPresenter();
        EventFactory eventFactory = new EventFactory();
        AddEventInteractor addEventInteractor = new AddEventInteractor(repository, addEventPresenter, eventFactory);
        AddUserEventController addUserEventController = new AddUserEventController(addEventInteractor);
        AddRecommendEventController addRecommendEventController = new AddRecommendEventController(addEventInteractor);

        CalendarPresenter calendarPresenter = new CalendarPresenter();
        CalendarInputBoundary calendarInteractor = new CalendarInteractor(calendarPresenter);


        MainMenuCalendarController calendarController = new MainMenuCalendarController(calendarInteractor, calendarPresenter, repository, courseScheduleController, repository, addUserEventController, addRecommendEventController);

        calendarButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {calendarController.click();
                    }
                });

        centrePanel.add(calendarButton);

        MainMenuEventsController mainMenuEventsController = new MainMenuEventsController();
        eventsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mainMenuEventsController.click();
                    }
                });

        centrePanel.add(eventsButton);

        JPanel bottomPanel = new JPanel();

        MainMenuBackButtonController mainMenuBackButtonController = new MainMenuBackButtonController();
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mainMenuBackButtonController.click();
                    }
                });

        bottomPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centrePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
