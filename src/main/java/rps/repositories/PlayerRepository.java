package rps.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rps.model.player.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
