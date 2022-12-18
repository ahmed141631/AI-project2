package Connect_4;

public abstract class Agent {
    // Variable that determine which player plays.
    private int ai_player;

    public Agent(int ai_player) {
        this.ai_player = ai_player;
    }

    public int getAiPlayer() {
        return ai_player;
    }

    public void setAiPlayer(int ai_player) {
        this.ai_player = ai_player;
    }

    // to overide it in different algorithms class
    public abstract Move getNextMove(Board board);


    
}
