package pavlo.restserver.service;

import pavlo.restserver.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class EventServiceTest {


    @Autowired
    private EventService eventService;

    @Before
    public void beforeTest() {
        eventService.deleteAll();
    }

    @Test
    public void saveTest() {
        Event event = eventService.save(new Event("workDay"));
        Event id = eventService.findById(event.getId());

        assertEquals(event, id);
    }
}
