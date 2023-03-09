package com.amigoscode.actor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorDao actorDao;

    public List<Actor> getAllActors() {
        return actorDao.selectAllActors();
    }

    public Actor getActor(int id) {
        return actorDao.selectActorById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Actor with id %s does not exist", id)));
    }

    public void addNewActor(Actor actor) {
        if (actorDao.existsByName(actor.name())) {
            throw new IllegalStateException(String.format("Actor with name %s already exists", actor.name()));
        }

        int result = actorDao.insertActor(actor);
        if (result != 1) {
            throw new IllegalStateException("Failed to insert actor");
        }
    }

    public void deleteActor(Integer id) {
        Optional<Actor> actors = actorDao.selectActorById(id);

        actors.ifPresentOrElse(actor -> {
            int result = actorDao.deleteActorById(id);
            if (result != 1) {
                throw new IllegalStateException("Failed to delete actor");
            }
        }, () -> {
            throw new IllegalStateException(String.format("Actor with id %s does not exist", id));
        });
    }

    public void updateActor(Integer id, Actor actor) {
        Optional<Actor> actors = actorDao.selectActorById(id);

        actors.ifPresentOrElse(foundActor -> {
            int result = actorDao.updateActorById(id, actor);
            if (result != 1) {
                throw new IllegalStateException("Failed to update actor");
            }
        }, () -> {
            throw new IllegalStateException(String.format("Actor with id %s does not exist", id));
        });
    }
}
