package rps.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import rps.model.utils.Body;
import rps.model.game.Game;
import rps.model.game.GameHistory;
import rps.model.utils.AppUtils;
import rps.model.utils.GameDTO;
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


   /* public Game joinGame(String tokenId) {
        Game gameInAction = new Game();
    }*/




}