package pavlo.restserver.service;


import pavlo.restserver.model.StatusWork;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class StatusWorkServiceTest {

    @Autowired
    private StatusWorkService statusWorkService;

    @Before
    public void before() {
        statusWorkService.deleteAll();
    }

    @Test
    public void saveTest() {
        StatusWork statusWork = new StatusWork("test");
        statusWorkService.save(statusWork);

        StatusWork test = statusWorkService.findByStatusWork("test");

        assertEquals(statusWork, test);
    }
}
