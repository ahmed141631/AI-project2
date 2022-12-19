package Connect_4;
/* very similar to MINMIX except will contains ALPHA BETA purnning */
public class MinimaxAlphaBeta extends Agent{
      /* this varible will hold the max depth that player can reach 
     * by minmix algorithms 
           */
   private int max_Depth; 

   public MinimaxAlphaBeta(){
    super(constants.player_2); //make the constructor of the agent and assign to player2
    max_Depth=2; // default depth that can reach
   }

   public MinimaxAlphaBeta(int max_Depth,int Agent_player){ //constructor for the Agent_vs_Agent Mode
        super(Agent_player); // like default constructor
        this.max_Depth=max_Depth;
   }

   // setter and getter of max_depth

   public void SetDepth(int max_Depth){
    this.max_Depth=max_Depth;
   }
    public int GetDepth(){
        return this.max_Depth;
    }

    @Override
    public Move getNextMove(Board board) {
        if(getAiPlayer()==constants.player_1){
            return maxAlphaBeta(new Board(board), 0, Double.MAX_VALUE, Double.MIN_VALUE);
        }
        // If P2 plays then it wants to MINimize the heuristics value.
        else {
            return minAlphaBeta(new Board(board), 0, Double.MIN_VALUE, Double.MAX_VALUE);
        }
      
    }
    private Move maxAlphaBeta(Board board, int depth, double a, double b) {
        return null;
    }

    private Move minAlphaBeta(Board board, int depth, double a, double b) {
        return null;
    }

}
