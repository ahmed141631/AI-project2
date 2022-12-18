package Connect_4;

public class MinMax extends Agent{

    private int  maxDepth;

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public MinMax() {
        super(constants.player_2);
        maxDepth = 2;
    }
    public MinMax(int maxDepth, int aiPlayer) {
        super(aiPlayer);
        this.maxDepth = maxDepth;
    }

    @Override
    public Move getNextMove(Board board) {
        
        return null;
    }

    
}
