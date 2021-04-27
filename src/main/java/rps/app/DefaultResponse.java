package rps.app;

import rps.app.player.PlayersStack;

public class DefaultResponse implements Response {

    private String reply;
    private String state;
    private int    playersReady;

    public String getReply() {
        return reply;
    }

    public DefaultResponse(String reply, String state) {
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
