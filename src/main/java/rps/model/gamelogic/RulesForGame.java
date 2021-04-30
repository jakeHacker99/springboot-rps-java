package rps.model.gamelogic;

public class RulesForGame {

    public Selection result(Selection s1, Selection s2) {
        if(s1.getValue() == s2.getValue()) {

            return Selection.Draw;

        }  	if((isRock(s1) && isScissors(s2)) || (isScissors(s1) && isRock(s2))) {

            return Selection.ROCK;

        } 	if((isPaper(s1) && isScissors(s2)) || (isScissors(s1) && isPaper(s2))) {

            return Selection.SCISSORS;
        }
        if((isRock(s1) && isPaper(s2)) || (isPaper(s1) && isRock(s2))) {

            return Selection.PAPER;
        }
        return Selection.Draw;
    }

    private boolean isPaper(Selection select) {
        return select.getValue() == Selection.PAPER.getValue();
    }
    private boolean isRock(Selection select) {
        return select.getValue() == Selection.ROCK.getValue();
    }
    private boolean isScissors(Selection select) {
        return select.getValue() == Selection.SCISSORS.getValue();
    }

}

