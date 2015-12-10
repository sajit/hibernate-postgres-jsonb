/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.example.jsonb;

import com.github.pires.example.TestEnvironment;
import com.github.pires.example.model.Category;
import com.github.pires.example.model.Episode;
import com.github.pires.example.model.Schedule;
import com.github.pires.example.model.Slot;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by sajit on 11/28/15.
 */
public class ScheduleTest {

    private EntityTransaction tx;

    public static List<List<Slot>> createSample() {
        List<List<Slot>> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Slot> slots1 = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Slot slot = createSlot(j);
                slots1.add(slot);
            }
            result.add(slots1);
        }
        return result;
    }

    public static Slot createSlot(int startTime) {
        Slot slot = new Slot();
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("cat1");
        slot.setCategory(category1);
        Episode episode1 = new Episode();
        episode1.setName(RandomStringUtils.random(5));
        slot.setEpisode(episode1);
        slot.setStartTime(startTime);
        return slot;
    }

    @BeforeClass
    public void setUp() {
        // provision entity transaction
        tx = TestEnvironment.getTransaction();


    }

    @Test
    public void testCreation() {
        int count = getCount().size();
        final String scheduleName = "test-2";
        tx.begin();
        Schedule schedule = new Schedule();
        schedule.setId(2);
        schedule.setName(scheduleName);
        schedule.setSlots(createSample());
        TestEnvironment.getEntityManager().persist(schedule);
        tx.commit();


        List<Schedule> result = getCount();
        Schedule dbSchedule = result.get(0);
        assertEquals(scheduleName, dbSchedule.getName());
        assertEquals(5, dbSchedule.getSlots().size());
        dbSchedule.getSlots().stream().forEach(aList -> aList.stream().forEach(aSlot -> {
                    assertEquals("cat1", aSlot.getCategory().getName());

                }
        ));
        assertEquals(result.size(), count + 1);
        tx.begin();


    }

    private List<Schedule> getCount() {
        tx.begin();
        List<Schedule> resultList = TestEnvironment.getEntityManager().createQuery("from Schedule").getResultList();

        tx.commit();
        return resultList;
    }

    @AfterClass
    public void cleanUp() {
        tx = null;

    }
}

