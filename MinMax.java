package Connect_4;

import java.util.ArrayList;
import java.util.Random;

public class MinMax extends Agent{
    /* this varible will hold the max depth that player can reach 
     * by minmix algorithms 
           */
   private int max_Depth; 

   public MinMax(){
    super(constants.player_2); //make the constructor of the agent and assign to player2
    max_Depth=2; // default depth that can reach
   }

   public MinMax(int max_Depth,int Agent_player){ //constructor for the Agent_vs_Agent Mode
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
    /* the methode just check it is the turn of p1 it call 
     * want to maximize his heuristic so it will call the max method
     * and if it is  the role of p2 it will call the min method 
     */
    public Move getNextMove(Board board) {

        if(getAiPlayer()==constants.player_1){
            return  max(new Board(),0);
        }else{
            return min(board, 0);
        }
    }

    /*max and min methode will be called one after anthor 
    till the Max_Depth be reached
    
    */
    private Move max(Board board,int depth){
        Random r=new Random(); // if we have two moves has same heuristic we will choose one of them randomly
        /* if max is called on a state that is teriminal(gameover) or after the max depth is reached
         * the heuristic will be caluclated and the move returned.
         */
        if(board.checkForGameOver()|| depth==max_Depth){
            return new Move(board.getLastMove().getRow(),board.getLastMove().getColumn(),board.evaluate());
        }

        // then will produce the children moves

        ArrayList <Board>children=new ArrayList<Board>(board.getChildren(constants.player_1));
    
        Move maxMove = new Move(Integer.MIN_VALUE);
        for (Board child : children) {
            // And for each child min is called, on a lower depth
            Move move=min(board, depth+1);
            // The child-move with the greatest value is selected and returned by max
            if(move.getValue()>=maxMove.getValue()){
                if(move.getValue()==maxMove.getValue()){
                    // If the heuristic has the same value then we randomly choose one of the two moves
                    if (r.nextInt(2) == 0 || move.getValue() == Integer.MIN_VALUE) {
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setColumn(child.getLastMove().getColumn());
                        maxMove.setValue(move.getValue());
                    }else{
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setColumn(child.getLastMove().getColumn());
                    maxMove.setValue(move.getValue());
                    }
                }
            }
            
        }



        return maxMove;
    }
    // work similar to max method
    private Move min(Board board,int depth){
        Random r=new Random();
        
        if ((board.checkForGameOver()) || (depth == max_Depth)) {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getColumn(), board.evaluate());
        }
        ArrayList<Board> children = new ArrayList<>(board.getChildren(constants.player_2));
        Move minMove = new Move(Integer.MAX_VALUE);
        for (Board child : children) {
            Move move = max(child, depth + 1);
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
        }
        return minMove;
    }
    
}
