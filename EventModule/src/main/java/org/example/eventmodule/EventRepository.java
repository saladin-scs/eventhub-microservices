package org.example.eventmodule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(Long organizerId);
    List<Event> findByTitleContainingOrDescriptionContaining(String title, String description);
    List<Event> findByStatus(EventStatus status);
}