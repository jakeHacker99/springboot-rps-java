package rps.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rps.model.game.Game;
@Repository
public interface GameRepository extends CrudRepository<Game,String> {
}
