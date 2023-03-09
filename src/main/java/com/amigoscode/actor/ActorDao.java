package com.amigoscode.actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao {
    List<Actor> selectAllActors();

    Optional<Actor> selectActorById(Integer id);

    int insertActor(Actor actor);

    int deleteActorById(Integer id);

    int updateActorById(Integer id, Actor actor);

    boolean existsByName(String name);
}
