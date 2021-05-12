package rps.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import rps.model.gamelogic.Selection;
import rps.model.utils.Body;
import rps.model.game.Game;
import rps.model.utils.AppUtils;
import rps.repositories.GameRepository;
import rps.repositories.TokenRepository;
import rps.tokens.Token;

@Service
@AllArgsConstructor
public class GameService {

    TokenRepository tokenRepository;
    GameRepository gameRepository;

    public Game startNewGame(Token token) {

        Game newGame = new Game(AppUtils.createNewId(), token);
        token.setOwnerGame(newGame);
        gameRepository.save(newGame);
        return newGame;
    }



    public Game joinGame(String gameId,Token token) {
        Game gameInAction = gameRepository.getOne(gameId);
        gameInAction.setJoiner(token);
        gameInAction.setState(Game.State.ACTIVE);
        token.setJoinerGame(gameInAction);
        gameRepository.save(gameInAction);
        return gameInAction;
    }



    public Game makeMove(Selection move, String tokenId) {

        Token token = tokenRepository.getOne(tokenId);
        Game ownerGame = token.getOwnerGame();

        if (ownerGame != null) {
            ownerGame.setMove(move);

            checkWinnerOfGame(ownerGame);
            gameRepository.save(ownerGame);
            return ownerGame;
        }

        Game joinerGame = token.getJoinerGame();
        if (joinerGame == null) {
            ownerGame.setId(ownerGame.getId());
            joinerGame.setOpponentMove(move);
            checkWinnerOfGame(joinerGame);
            gameRepository.save(joinerGame);
            return joinerGame;
        }

        //get winner / loser

        return null;
    }

    private void checkWinnerOfGame(Game game) {

    }



  /*  public Game getName(String name, String tokenId) {
        Game enterName = gameRepository.getOne(tokenId);
        enterName.setName(name);
        gameRepository.save(enterName);
        return enterName;
    }*/

    public Game getState(String gameId) {
        Game gameInAction = gameRepository.getOne(gameId);

        gameInAction.getState();

        gameRepository.save(gameInAction);

        return gameInAction;
    }
}