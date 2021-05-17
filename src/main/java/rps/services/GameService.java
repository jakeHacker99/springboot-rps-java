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


    public Game joinGame(String gameId, Token token) {
        Game gameInAction = gameRepository.getOne(gameId);
        gameInAction.setJoiner(token);
        gameInAction.setState(Game.State.ACTIVE);

        token.setJoinerGame(gameInAction);
        token.setOwnerGame(gameInAction);

        gameRepository.save(gameInAction);
        return gameInAction;
    }


    public Game makeMove(Selection move, String tokenId) {

        Token token = tokenRepository.getOne(tokenId);
        Game ownerGame = token.getOwnerGame();

        if (ownerGame != null && ownerGame.getMove() == null) {
            ownerGame.setMove(move);
            tokenRepository.save(token);
            gameRepository.save(ownerGame);
            return ownerGame;
        }

        Game joinerGame = token.getJoinerGame();
        if (joinerGame.getJoiner().getId() != null) {
            ownerGame.setOpponentMove(move);
            tokenRepository.save(token);
            gameRepository.save(joinerGame);
            gameRepository.save(ownerGame);

            if (ownerGame.getMove() != null) {
                checkWinnerOfGame(joinerGame);
            }
            return joinerGame;
        }

        //get winner / loser

        return null;
    }

    private void checkWinnerOfGame(Game game) {
        Selection ownerMove = game.getMove();
        Selection joinerMove = game.getOpponentMove();

        String setOwnerToWinner = game.getOwner().getId();
        String setJoinerToWinner = game.getJoiner().getId();


        if (ownerMove.equals(Selection.ROCK) && joinerMove.equals(Selection.PAPER)) {
            game.setState(Game.State.LOSE);
        }
        if (ownerMove.equals(Selection.PAPER) && joinerMove.equals(Selection.SCISSORS)) {
            game.setState(Game.State.LOSE);
        }

        if (ownerMove.equals(Selection.SCISSORS) && joinerMove.equals(Selection.ROCK)) {
            game.setState(Game.State.LOSE);

        }

        if (ownerMove.equals(Selection.ROCK) && joinerMove.equals(Selection.SCISSORS)) {
            game.setState(Game.State.WIN);
        }
        if (ownerMove.equals(Selection.PAPER) && joinerMove.equals(Selection.ROCK)) {
            game.setState(Game.State.WIN);
        }

        if (ownerMove.equals(Selection.SCISSORS) && joinerMove.equals(Selection.PAPER)) {
            game.setState(Game.State.WIN);
        }


        if (joinerMove.equals(ownerMove)) {
            game.setState(Game.State.DRAW);
        }

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