package org.example.eventmodule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;  // AJOUTEZ CETTE LIGNE


@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping
    public Event create(@RequestBody EventRequest request) {
        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .date(request.getDate())
                .place(request.getPlace())
                .price(request.getPrice())
                .organizerId(request.getOrganizerId())
                .imageUrl(request.getImageUrl())
                .nbPlaces(request.getNbPlaces())
                .nbLikes(request.getNbLikes() != null ? request.getNbLikes() : 0)
                .domaines(request.getDomaines() != null ? request.getDomaines() : new ArrayList<>())
                .status(request.getStatus() != null ? request.getStatus() : EventStatus.PUBLISHED)
                .build();

        return service.create(event);
    }

    @GetMapping
    public List<Event> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody EventRequest request) {
        Event event = service.getById(id);

        // Mise à jour des champs
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setPlace(request.getPlace());
        event.setPrice(request.getPrice());
        event.setOrganizerId(request.getOrganizerId());
        event.setImageUrl(request.getImageUrl());
        event.setNbPlaces(request.getNbPlaces());
        event.setDomaines(request.getDomaines());
        event.setStatus(request.getStatus());

        return service.update(event);
    }

    @DeleteMapping("/{id}")
    public void archive(@PathVariable Long id) {
        service.archive(id);
    }

    @PostMapping("/{id}/like")
    public Event like(@PathVariable Long id) {
        return service.likeEvent(id);
    }

    @GetMapping("/organizer/{organizerId}")
    public List<Event> getByOrganizer(@PathVariable Long organizerId) {
        return service.getByOrganizer(organizerId);
    }

    @GetMapping("/search")
    public List<Event> search(@RequestParam String keyword) {
        return service.search(keyword);
    }
}