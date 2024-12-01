package use_case.add_event;

import data_access.InMemoryScheduleRepository;
import entity.EventFactory;
import interface_adapter.add_event.AddEventPresenter;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class AddEventInteractorTest {

    @Test
    public void addEventScheduleSuccessfulTest() {

        Set<String> tags = Set.of("tag1", "tag2");

        AddEventInputData addEventInputData = new AddEventInputData("Some Event", "2024-12-01", "09:00~10:30", "Some description.", tags, "user");

        AddEventScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddEventOutputBoundary addEventScheduleOutputBoundary = new AddEventPresenter();

        EventFactory eventFactory = new EventFactory();

        AddEventInteractor addEventInteractor = new AddEventInteractor(inMemoryScheduleRepository, addEventScheduleOutputBoundary, eventFactory);

        addEventInteractor.execute(addEventInputData);

        assertEquals("Some Event", addEventInputData.getName());
        assertEquals("2024-12-01", addEventInputData.getDate());
        assertEquals("09:00~10:30", addEventInputData.getTime());
        assertEquals("Some description.", addEventInputData.getDescription());
        assertEquals(tags, addEventInputData.getTags());
        assertEquals("user", addEventInputData.getSource());
    }

    @Test
    public void addEventScheduleFailTestNullName() {

        Set<String> tags = Set.of("tag1", "tag2");

        AddEventInputData addEventInputData = new AddEventInputData(null, "2024-12-01", "09:00~10:30", "Some description.", tags, "user");

        AddEventScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddEventOutputBoundary addEventScheduleOutputBoundary = new AddEventPresenter();

        EventFactory eventFactory = new EventFactory();

        AddEventInteractor addEventInteractor = new AddEventInteractor(inMemoryScheduleRepository, addEventScheduleOutputBoundary, eventFactory);

        addEventInteractor.execute(addEventInputData);

        assertNull(addEventInputData.getName());
        assertEquals("2024-12-01", addEventInputData.getDate());
        assertEquals("09:00~10:30", addEventInputData.getTime());
        assertEquals("Some description.", addEventInputData.getDescription());
        assertEquals(tags, addEventInputData.getTags());
        assertEquals("user", addEventInputData.getSource());
    }

    @Test
    public void addEventScheduleFailTestNullDate() {

        Set<String> tags = Set.of("tag1", "tag2");

        AddEventInputData addEventInputData = new AddEventInputData("Some Event", null, "09:00~10:30", "Some description.", tags, "user");

        AddEventScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddEventOutputBoundary addEventScheduleOutputBoundary = new AddEventPresenter();

        EventFactory eventFactory = new EventFactory();

        AddEventInteractor addEventInteractor = new AddEventInteractor(inMemoryScheduleRepository, addEventScheduleOutputBoundary, eventFactory);

        addEventInteractor.execute(addEventInputData);

        assertEquals("Some Event", addEventInputData.getName());
        assertNull(addEventInputData.getDate());
        assertEquals("09:00~10:30", addEventInputData.getTime());
        assertEquals("Some description.", addEventInputData.getDescription());
        assertEquals(tags, addEventInputData.getTags());
        assertEquals("user", addEventInputData.getSource());
    }

    @Test
    public void addEventScheduleFailTestNullTime() {

        Set<String> tags = Set.of("tag1", "tag2");

        AddEventInputData addEventInputData = new AddEventInputData("Some Event", "2024-12-01", null, "Some description.", tags, "user");

        AddEventScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddEventOutputBoundary addEventScheduleOutputBoundary = new AddEventPresenter();

        EventFactory eventFactory = new EventFactory();

        AddEventInteractor addEventInteractor = new AddEventInteractor(inMemoryScheduleRepository, addEventScheduleOutputBoundary, eventFactory);

        addEventInteractor.execute(addEventInputData);

        assertEquals("Some Event", addEventInputData.getName());
        assertEquals("2024-12-01", addEventInputData.getDate());
        assertNull(addEventInputData.getTime());
        assertEquals("Some description.", addEventInputData.getDescription());
        assertEquals(tags, addEventInputData.getTags());
        assertEquals("user", addEventInputData.getSource());
    }

    @Test
    public void addEventScheduleFailTestWrongDate() {

        Set<String> tags = Set.of("tag1", "tag2");

        AddEventInputData addEventInputData = new AddEventInputData("Some Event", "024-12-01", "09:00~10:30", "Some description.", tags, null);

        AddEventScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddEventOutputBoundary addEventScheduleOutputBoundary = new AddEventPresenter();

        EventFactory eventFactory = new EventFactory();

        AddEventInteractor addEventInteractor = new AddEventInteractor(inMemoryScheduleRepository, addEventScheduleOutputBoundary, eventFactory);

        addEventInteractor.execute(addEventInputData);

        assertEquals("Some Event", addEventInputData.getName());
        assertEquals("024-12-01", addEventInputData.getDate());
        assertEquals("09:00~10:30", addEventInputData.getTime());
        assertEquals("Some description.", addEventInputData.getDescription());
        assertEquals(tags, addEventInputData.getTags());
        assertNull(addEventInputData.getSource());
    }

    @Test
    public void addEventScheduleFailTestWrongTime() {

        Set<String> tags = Set.of("tag1", "tag2");

        AddEventInputData addEventInputData = new AddEventInputData("Some Event", "2024-12-01", "9:00-10:30", "Some description.", tags, "user");

        AddEventScheduleDataAccessInterface inMemoryScheduleRepository = new InMemoryScheduleRepository();

        AddEventOutputBoundary addEventScheduleOutputBoundary = new AddEventPresenter();

        EventFactory eventFactory = new EventFactory();

        AddEventInteractor addEventInteractor = new AddEventInteractor(inMemoryScheduleRepository, addEventScheduleOutputBoundary, eventFactory);

        addEventInteractor.execute(addEventInputData);

        assertEquals("Some Event", addEventInputData.getName());
        assertEquals("2024-12-01", addEventInputData.getDate());
        assertEquals("9:00-10:30", addEventInputData.getTime());
        assertEquals("Some description.", addEventInputData.getDescription());
        assertEquals(tags, addEventInputData.getTags());
        assertEquals("user", addEventInputData.getSource());
    }
}
