package rps.app.utilities;

import rps.app.player.PlayersStack;

public class DefaultBody implements Body {

    private String reply;
    private String state;
    private int    playersReady;

    public String getReply() {
        return reply;
    }

    public DefaultBody(String reply, String state) {
        this.playersReady = PlayersStack.getInstance().size();
        this.reply = reply;
        this.state = state;
    }

    public int getPlayersReady() {
        return playersReady;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
