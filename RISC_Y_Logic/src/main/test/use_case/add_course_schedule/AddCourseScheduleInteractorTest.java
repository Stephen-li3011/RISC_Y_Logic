package use_case.add_course_schedule;

import data_access.InMemoryScheduleRepository;
import interface_adapter.add_course_schedule.AddCourseSchedulePresenter;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class AddCourseScheduleInteractorTest {

    @Test
    public void addCourseScheduleSuccessfulTestFall() {

        AddCourseScheduleInputData addCourseScheduleInputData = new AddCourseScheduleInputData("CSC207", "Monday", LocalTime.of(11, 0), LocalTime.of(12, 0), "Fall");

        AddCourseScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddCourseScheduleOutputBoundary addCourseScheduleOutputBoundary = new AddCourseSchedulePresenter();

        AddCourseScheduleInteractor addCourseScheduleInteractor = new AddCourseScheduleInteractor(inMemoryScheduleRepository, addCourseScheduleOutputBoundary);

        addCourseScheduleInteractor.add(addCourseScheduleInputData);

        assertEquals("CSC207", addCourseScheduleInputData.getCourseName());
        assertEquals(LocalTime.of(11, 0), addCourseScheduleInputData.getStartTime());
        assertEquals(LocalTime.of(12, 0), addCourseScheduleInputData.getEndTime());
        assertEquals("Monday", addCourseScheduleInputData.getCourseDate());
        assertEquals("Fall", addCourseScheduleInputData.getSemester());
    }

    @Test
    public void addCourseScheduleSuccessfulTestWinter() {

        AddCourseScheduleInputData addCourseScheduleInputData = new AddCourseScheduleInputData("CSC207", "Monday", LocalTime.of(11, 0), LocalTime.of(12, 0), "Winter");

        AddCourseScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddCourseScheduleOutputBoundary addCourseScheduleOutputBoundary = new AddCourseSchedulePresenter();

        AddCourseScheduleInteractor addCourseScheduleInteractor = new AddCourseScheduleInteractor(inMemoryScheduleRepository, addCourseScheduleOutputBoundary);

        addCourseScheduleInteractor.add(addCourseScheduleInputData);

        assertEquals("CSC207", addCourseScheduleInputData.getCourseName());
        assertEquals(LocalTime.of(11, 0), addCourseScheduleInputData.getStartTime());
        assertEquals(LocalTime.of(12, 0), addCourseScheduleInputData.getEndTime());
        assertEquals("Monday", addCourseScheduleInputData.getCourseDate());
        assertEquals("Winter", addCourseScheduleInputData.getSemester());
    }

    @Test
    public void addCourseScheduleSuccessfulTestSummer() {

        AddCourseScheduleInputData addCourseScheduleInputData = new AddCourseScheduleInputData("CSC207", "Monday", LocalTime.of(11, 0), LocalTime.of(12, 0), "Summer");

        AddCourseScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddCourseScheduleOutputBoundary addCourseScheduleOutputBoundary = new AddCourseSchedulePresenter();

        AddCourseScheduleInteractor addCourseScheduleInteractor = new AddCourseScheduleInteractor(inMemoryScheduleRepository, addCourseScheduleOutputBoundary);

        addCourseScheduleInteractor.add(addCourseScheduleInputData);

        assertEquals("CSC207", addCourseScheduleInputData.getCourseName());
        assertEquals(LocalTime.of(11, 0), addCourseScheduleInputData.getStartTime());
        assertEquals(LocalTime.of(12, 0), addCourseScheduleInputData.getEndTime());
        assertEquals("Monday", addCourseScheduleInputData.getCourseDate());
        assertEquals("Summer", addCourseScheduleInputData.getSemester());
    }

    @Test
    public void addCourseScheduleFailureTest() {
        AddCourseScheduleInputData addCourseScheduleInputData = new AddCourseScheduleInputData("CSC207", "Monday", LocalTime.of(11, 0), LocalTime.of(12, 0), "Spring");

        AddCourseScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddCourseScheduleOutputBoundary addCourseScheduleOutputBoundary = new AddCourseSchedulePresenter();

        AddCourseScheduleInteractor addCourseScheduleInteractor = new AddCourseScheduleInteractor(inMemoryScheduleRepository, addCourseScheduleOutputBoundary);

        addCourseScheduleInteractor.add(addCourseScheduleInputData);

        assertEquals("CSC207", addCourseScheduleInputData.getCourseName());
        assertEquals(LocalTime.of(11, 0), addCourseScheduleInputData.getStartTime());
        assertEquals(LocalTime.of(12, 0), addCourseScheduleInputData.getEndTime());
        assertEquals("Monday", addCourseScheduleInputData.getCourseDate());
        assertEquals("Spring", addCourseScheduleInputData.getSemester());

    }
}
