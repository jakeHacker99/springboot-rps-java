package rps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rps.model.game.Game;
import rps.tokens.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
}
