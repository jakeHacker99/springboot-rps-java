package rps.services;
import java.util.*;

import antlr.collections.impl.LList;
import lombok.AllArgsConstructor;
import org.hibernate.engine.query.spi.ParamLocationRecognizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import rps.model.gamelogic.RulesForGame;
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

//    public List<Game> joinGame(Token Token) {
////
////        List getAllOpenGames= gameRepository.findAll();
////
////        return  getAllOpenGames;
//    }


    public Game joinGame(String gameId,Token token) {
        Game gameInAction = gameRepository.getOne(gameId);
        gameInAction.setJoiner(token);
        gameInAction.setState(Game.State.ACTIVE);

        token.setJoinerGame(gameInAction);
//        token.setOwnerGame(gameInAction);


        gameRepository.save(gameInAction);
        return gameInAction;
    }



    public Game makeMove(Selection move, String tokenId) {

        Token token = tokenRepository.getOne(tokenId);
        Game ownerGame = token.getOwnerGame();

        if (ownerGame != null) {
            ownerGame.setMove(move);
            System.out.println("owner move is\t"+ move);
            checkWinnerOfGame(ownerGame);
            gameRepository.save(ownerGame);
            return ownerGame;
        }

        Game joinerGame = token.getJoinerGame();
        if (joinerGame != null) {
            joinerGame.setOpponentMove(move);
            System.out.println("joiner move is\t"+ move);
            checkWinnerOfGame(joinerGame);
            gameRepository.save(joinerGame);
            return joinerGame;
        }

        //get winner / loser
        return null;


    }

    private void checkWinnerOfGame(Game game) {
       /* Game checkResult = new Game();

        if (checkResult.getMove() == checkResult.getOpponentMove() ) {
            return ;
        }
        if (checkResult.getMove() == Selection.ROCK && checkResult.getOpponentMove() == Selection.SCISSORS) {

        }
        RulesForGame checkResult = new RulesForGame();
        checkResult.result(game.getMove(),game.getOpponentMove());*/

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
