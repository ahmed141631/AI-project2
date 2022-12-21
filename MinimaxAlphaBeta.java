package Connect_4;

import java.util.ArrayList;
import java.util.Random;

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
        /* Double. MAX_VALUE is the maximum value a double can represent 
        (somewhere around 1.7*10^308) Whether Double.MIN_VALUE is a constant holding the smallest 
        POSITIVE nonzero value of type double, 2^(-1074). */

        // If P1 plays then it wants to MAXimize the heuristics value.
        if(getAiPlayer()==constants.player_1){
            return maxAlphaBeta(new Board(board), 0, Double.MAX_VALUE, Double.MIN_VALUE);
        }
        // If P2 plays then it wants to MINimize the heuristics value.
        else {
            return minAlphaBeta(new Board(board), 0, Double.MIN_VALUE, Double.MAX_VALUE);
        }
      
    }


    private Move maxAlphaBeta(Board board, int depth, double a, double b) {
        /*as in minmix this help us if two moves a has same heuristic we 
        choice one of them randomly */
        Random r=new Random(); 

        /* if max is called on a state that is teriminal(gameover) or after the max depth is reached
         * the heuristic will be caluclated and the move returned.
         */
        if(board.isGameOver()|| depth==max_Depth){
            return new Move(board.getLastMove().getRow(),board.getLastMove().getColumn(),board.evaluate());

        }
        // first we will produce the children moves from current state
         // The children-moves of the state are calculated
         ArrayList<Board> children = new ArrayList<>(board.getChildren(constants.player_1));
         Move maxMove = new Move(Integer.MIN_VALUE);
         for (Board child : children) {
             // And for each child min is called, on a lower depth.
             Move move = minAlphaBeta(child, depth + 1, a, b);
             // The child-move with the greatest value is selected and returned by max.
             if (move.getValue() >= maxMove.getValue()) {
                 if ((move.getValue() == maxMove.getValue())) {
                     // If the heuristic has the same value, then we randomly choose one of the two moves.
                     if (r.nextInt(2) == 0 || move.getValue() == Integer.MIN_VALUE) {
                         maxMove.setRow(child.getLastMove().getRow());
                         maxMove.setColumn(child.getLastMove().getColumn());
                         maxMove.setValue(move.getValue());
                     }
                 } else {
                     maxMove.setRow(child.getLastMove().getRow());
                     maxMove.setColumn(child.getLastMove().getColumn());
                     maxMove.setValue(move.getValue());
                 }
             }
 
             // Beta pruning.
             if (maxMove.getValue() >= b) {
                 // System.out.println("Beta pruning: " + b);
                 return maxMove;
             }
 
             // Update the a of the current max node.
             a = (a > maxMove.getValue()) ? a : maxMove.getValue();
         }
         return maxMove;
     }
 
     // Min works similarly to max.
     private Move minAlphaBeta(Board board, int depth, double a, double b) {
         Random r = new Random();
 
         if ((board.checkForGameOver()) || (depth == max_Depth)) {
             return new Move(board.getLastMove().getRow(), board.getLastMove().getColumn(), board.evaluate());
         }
         ArrayList<Board> children = new ArrayList<>(board.getChildren(constants.player_1));
         Move minMove = new Move(Integer.MAX_VALUE);
         for (Board child : children) {
             Move move = maxAlphaBeta(child, depth + 1, a, b);
             if (move.getValue() <= minMove.getValue()) {
                 if ((move.getValue() == minMove.getValue())) {
                     if (r.nextInt(2) == 0 || move.getValue() == Integer.MAX_VALUE) {
                         minMove.setRow(child.getLastMove().getRow());
                         minMove.setColumn(child.getLastMove().getColumn());
                         minMove.setValue(move.getValue());
                     }
                 } else {
                     minMove.setRow(child.getLastMove().getRow());
                     minMove.setColumn(child.getLastMove().getColumn());
                     minMove.setValue(move.getValue());
                 }
             }
 
             // Alpha pruning
             if (minMove.getValue() <= a) {
                 // System.out.println("Alpha pruning: " + a);
                 return minMove;
             }
 
             // Update the b of the current min node.
             b = (b < minMove.getValue()) ? b : minMove.getValue();
         }
         return minMove;
     }
}
