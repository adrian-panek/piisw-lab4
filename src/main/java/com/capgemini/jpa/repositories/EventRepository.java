package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByTimeIsBetweenAndAnalysisRequired (LocalDateTime start, LocalDateTime end, boolean toBeAnalyzed, Pageable pageable);
    void deleteAllByTimeLessThan(LocalDateTime givenDate);

    @Modifying
    @Query("UPDATE Event e SET e.analysisRequired = 'T' WHERE e.duration > :minDuration and TYPE(e) = :clazz")
    void updateEventsByEventDuration(@Param("clazz") Class <? extends Event> clazz, @Param("minDuration") int minDuration);

    @Query("SELECT NEW com.capgemini.jpa.repositories.ServerStatistic(e.server, COUNT(e)) FROM Event e GROUP BY e.server")
    List<ServerStatistic> listServerDetails();

}
