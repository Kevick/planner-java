package com.tungs.planner.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tungs.planner.trip.participant.ParticipantService;

@RestController
@RequestMapping("/trips")
public class TripController {
    
    @Autowired
    private ParticipantService participantService;
    
    @Autowired
    private TripRepository repository;
    
    @PostMapping
    public ResponseEntity<String> createTrip(@RequestBody TripRequestPayload payload) {
        Trip newTrip = new Trip(payload);
        this.repository.save(newTrip);
        this.participantService.registerParticipantToEvent(payload.emails_to_invite(), newTrip.getId());
        this.repository.save(newTrip);
        return ResponseEntity.ok("Sucesso!");
    }
}
