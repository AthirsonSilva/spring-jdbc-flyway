package com.amigoscode.actor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("{id}")
    public ResponseEntity<Actor> getActor(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(actorService.getActor(id));
    }

    @PostMapping
    public ResponseEntity<Actor> addNewActor(@RequestBody Actor actor) {
        actorService.addNewActor(actor);

        return ResponseEntity.ok(actor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") Integer id) {
        actorService.deleteActor(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Integer id, @RequestBody Actor actor) {
        actorService.updateActor(id, actor);

        return ResponseEntity.ok(actor);
    }
}
