package rewards.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RewardsServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class RewardsServiceImplTest {

    @Autowired
    private RewardsServiceImpl rewardsServiceImpl;

    @Test
    public void testGetAttractionRewardPoints() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        UUID attractionId = UUID.randomUUID();
        this.rewardsServiceImpl.getAttractionRewardPoints(attractionId, UUID.randomUUID());
    }
}

