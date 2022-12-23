package GUI;

import Connect_4.*;

public class GameElements {
    private Mode gamemode;

    private int ai1MaxDepth;
    private int ai2MaxDepth;

    private Color player1Color;
    private Color player2Color;

    private int numOfRows;
    private int numOfColumns;
    private int checkersInARow;


    /*default constructor */
    public GameElements(){
        gamemode = Mode.HUMAN_VS_AI;
        ai1MaxDepth = 5;
        ai2MaxDepth = 5;
        player1Color = Color.RED;
        player2Color = Color.YELLOW;
        numOfRows = constants.rows;
        numOfColumns = constants.columns;
        checkersInARow = constants.checkWinRow;
    }

        public GameElements(GameElements otherGameParameters) {
            this.gamemode = otherGameParameters.getGameMode();
            this.ai1MaxDepth = otherGameParameters.getAi1MaxDepth();
            this.ai2MaxDepth = otherGameParameters.getAi2MaxDepth();
            this.player1Color = otherGameParameters.getPlayer1Color();
            this.player2Color = otherGameParameters.getPlayer2Color();
            this.numOfRows = otherGameParameters.getNumOfRows();
            this.numOfColumns = otherGameParameters.getNumOfColumns();
            this.checkersInARow = otherGameParameters.getCheckersInARow();
        }  
        public GameElements( Mode gameMode, 
        int ai1MaxDepth, int ai2MaxDepth, Color player1Color, Color player2Color,
        int numOfRows, int numOfColumns, int checkersInARow) {

this.gamemode = gameMode;

this.ai1MaxDepth = ai1MaxDepth;
this.ai2MaxDepth = ai2MaxDepth;
this.player1Color = player1Color;
this.player2Color = player2Color;
this.numOfRows = numOfRows;
this.numOfColumns = numOfColumns;
this.checkersInARow = checkersInARow;
}


        public Mode getGameMode() {
            return gamemode;
        }
    
        public void setGameMode(Mode gameMode) {
            this.gamemode = gameMode;
        }
        public int getAi1MaxDepth() {
            return ai1MaxDepth;
        }
    
        public void setAi1MaxDepth(int ai1MaxDepth) {
            this.ai1MaxDepth = ai1MaxDepth;
        }
    
        public int getAi2MaxDepth() {
            return ai2MaxDepth;
        }
    
        public void setAi2MaxDepth(int ai2MaxDepth) {
            this.ai2MaxDepth = ai2MaxDepth;
        }
    
        public Color getPlayer1Color() {
            return player1Color;
        }
    
        public void setPlayer1Color(Color player1Color) {
            this.player1Color = player1Color;
        }
    
        public Color getPlayer2Color() {
            return player2Color;
        }
    
        public void setPlayer2Color(Color player2Color) {
            this.player2Color = player2Color;
        }
    
        public int getNumOfRows() {
            return numOfRows;
        }
        public void setNumOfRows(int numOfRows) {
            this.numOfRows = numOfRows;
        }
    
        public int getNumOfColumns() {
            return numOfColumns;
        }
    
        public void setNumOfColumns(int numOfColumns) {
            this.numOfColumns = numOfColumns;
        }
    
        public int getCheckersInARow() {
            return checkersInARow;
        }
    
        public void setCheckersInARow(int checkersInARow) {
            this.checkersInARow = checkersInARow;
        }
    }
    

