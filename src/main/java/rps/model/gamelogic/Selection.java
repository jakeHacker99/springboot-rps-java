package rps.model.gamelogic;



public enum Selection{
    ROCK(0),
    PAPER(1),
    SCISSORS(2),
    Draw(3);


    public void setValue(int value) {
        this.value = value;
    }

    private int value;


    public int getValue() {
        return value;
    }

    private Selection(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        switch (value) {
            case 0:
                return "ROCK";
            case 1:
                return "PAPER";
            case 2:
                return "SCISSORS";
            default:
                return "Draw";
        }
    }
}

