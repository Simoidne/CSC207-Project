package use_case;
import entity.Calendar;
import entity.Event;
import entity.WeeklyPlanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CreateCalendarUseCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ICSConverterTest {

    private Calendar calendar;
    private WeeklyPlanner weeklyPlanner;
    private final String filePath = "test_calendar.ics";

    @BeforeEach
    void setUp() {
        // Create the Event manually
        Event event = new Event("E1", "Test Event",
                LocalDateTime.of(2024, 8, 20, 10, 0),
                LocalDateTime.of(2024, 8, 20, 12, 0),
                "event");

        // Create a list of events and add the created event
        List<Event> events = new ArrayList<>();
        events.add(event);

        // Create the WeeklyPlanner with the event
        weeklyPlanner = new WeeklyPlanner(LocalDateTime.now(), LocalDateTime.now().plusDays(7), new ArrayList<>(), new ArrayList<>(), events);

        // Create a new Calendar with a modifiable list of WeeklyPlanner
        List<WeeklyPlanner> weeks = new ArrayList<>();
        weeks.add(weeklyPlanner);

        CreateCalendarUseCase useCase = new CreateCalendarUseCase();
        calendar = useCase.createCalendar(weeklyPlanner);
    }

    @Test
    void testConvertToICS_NewFile() throws IOException {
        // Delete the file if it exists to simulate creating a new file
        Files.deleteIfExists(Paths.get(filePath));

        ICSConverter.convertToICS(calendar, filePath);

        // Check that the file was created and contains the expected content
        assertTrue(Files.exists(Paths.get(filePath)));
        String icsContent = new String(Files.readAllBytes(Paths.get(filePath)));
        assertTrue(icsContent.contains("BEGIN:VCALENDAR"));
        assertTrue(icsContent.contains("SUMMARY:Test Event"));
        assertTrue(icsContent.contains("DTSTART:20240820T100000"));
        assertTrue(icsContent.contains("DTEND:20240820T120000"));
        assertTrue(icsContent.contains("END:VCALENDAR"));
    }

    @Test
    void testConvertToICS_ExistingFile() throws IOException {
        // Create a file with some existing content
        Files.write(Paths.get(filePath), "BEGIN:VCALENDAR\nEND:VCALENDAR".getBytes());

        ICSConverter.convertToICS(calendar, filePath);

        // Check that the file was appended correctly
        String icsContent = new String(Files.readAllBytes(Paths.get(filePath)));
        assertTrue(icsContent.contains("BEGIN:VCALENDAR"));
        assertTrue(icsContent.contains("SUMMARY:Test Event"));
        assertTrue(icsContent.contains("DTSTART:20240820T100000"));
        assertTrue(icsContent.contains("DTEND:20240820T120000"));
        assertTrue(icsContent.contains("END:VCALENDAR"));
    }
}
