package org.example.eventmodule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;

    public Event create(Event event) {
        return repository.save(event);
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Event getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public Event update(Event event) {
        return repository.save(event);
    }

    public void archive(Long id) {
        Event event = getById(id);
        event.setArchived(true);
        repository.save(event);
    }

    public Event likeEvent(Long id) {
        Event event = getById(id);
        event.setNbLikes(event.getNbLikes() + 1);
        return repository.save(event);
    }

    public List<Event> getByOrganizer(Long organizerId) {
        return repository.findByOrganizerId(organizerId);
    }

    public List<Event> search(String keyword) {
        return repository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }
}