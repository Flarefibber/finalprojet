package pavlo.restserver.service;

import pavlo.restserver.Service.PositionService;
import pavlo.restserver.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Before
    public void beforeTest() {
        positionService.deleteAll();
    }

    @Test
    public void saveTest() {
        Position position = new Position("test", 1f);
        positionService.save(position);
        Position id = positionService.findById(position.getId());

        assertEquals(position, id);
    }

}
