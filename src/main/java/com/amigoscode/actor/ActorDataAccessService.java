package com.amigoscode.actor;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActorDataAccessService implements ActorDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Actor> selectAllActors() {
        String sql = "SELECT id, name FROM actor LIMIT 100";

        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public Optional<Actor> selectActorById(Integer id) {
        String sql = "SELECT id, name FROM actor WHERE id = ?";

        return jdbcTemplate.query(sql, new ActorRowMapper(), id).stream().findFirst();
    }

    @Override
    public int insertActor(Actor actor) {
        String sql = "INSERT INTO actor (name) VALUES (?)";

        return jdbcTemplate.update(sql, actor.name());
    }

    @Override
    public int deleteActorById(Integer id) {
        String sql = "DELETE FROM actor WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateActorById(Integer id, Actor actor) {
        String sql = "UPDATE actor SET name = ? WHERE id = ?";

        return jdbcTemplate.update(sql, actor.name(), id);
    }

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT EXISTS(SELECT 1 FROM actor WHERE name = ?)";

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, name));
    }
}
