package rps.services;
import java.util.*;

import antlr.collections.impl.LList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.context.annotation.Bean;
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

        gameRepository.save(gameInAction);
        return gameInAction;
    }



    public Game makeMove(Selection move, String tokenId) {

        Token token = tokenRepository.getOne(tokenId);

        Game getGameByOwnerId =  gameRepository.getOne(token.getOwnerGame().getId());

        if (getGameByOwnerId.getMove() == null) {
            getGameByOwnerId.setMove(move);
        }

        if (token.getJoinerGame() != null) {
            token.getJoinerGame().setOpponentMove(move);
        }
        if (token.getOwnerGame() != null) {
            token.getOwnerGame().setMove(move);
        }



        //get winner / loser


        tokenRepository.save(token);
        gameRepository.save(getGameByOwnerId);

        return getGameByOwnerId;
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