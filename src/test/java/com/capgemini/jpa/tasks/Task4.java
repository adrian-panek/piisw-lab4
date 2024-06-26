package com.capgemini.jpa.tasks;

import com.capgemini.jpa.repositories.EventRepository;
import com.capgemini.jpa.repositories.ServerRepository;
import com.capgemini.jpa.repositories.ServerStatistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class Task4 {

    @Autowired
    EventRepository eventRepository;

    @Test
    void shouldCountEventsByServer() throws Exception {
        // given ensured by script
        long expectedServer_1 = 15l;
        long expectedServer_2 = 14l;
        long expectedServer_3 = 11l;

        // when
        List<ServerStatistic> result = eventRepository.listServerDetails();

        // then

        assertThat(result, hasSize(3));

        // convert to map
        Map<Long, Long> map = result.stream()
                .collect(Collectors.toMap(s -> s.getServer().getId(), ServerStatistic::getCount));
        assertThat(map.get(1L), is(expectedServer_1));
        assertThat(map.get(2L), is(expectedServer_2));
        assertThat(map.get(3L), is(expectedServer_3));
    }

}
