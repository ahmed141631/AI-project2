package Connect_4;

import java.util.ArrayList;

public class Board {
    private  int[][] gameBoard;
    private int numOfRows;
    private int numOfColums;
    private int checkInRow;

    //Imdiate move or last move
    Move lastMove;
    private int Lastplayer;
    private int winner;
    private boolean overflow;
    private boolean GameOver;
    private int turn;


    public Board(int numOfRows, int numOfColumns, int checkersInARow) {
        this.numOfRows = numOfRows;
        this.numOfColums = numOfColumns;
        this.checkInRow = checkersInARow;

        this.lastMove = new Move();
        this.Lastplayer = constants.player_2;
        this.winner = constants.Empty_pos;
        this.gameBoard = new int[numOfRows][numOfColumns];
        this.overflow = false;
        this.GameOver = false;
        this.turn = 0;
        // initalize all the board with 0
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                gameBoard[i][j] = constants.Empty_pos;
            }
        }
    }
     //Default constructor
     public Board(){
        this(constants.rows,constants.columns,constants.checkWinRow);
    }

    // copy constructor that is initalize with a board

    public Board(Board board){
        numOfRows = board.getNumOfRows();
        numOfColums = board.getNumOfColumns();
        checkInRow = board.getCheckersInARow();

        lastMove = board.getLastMove();
        Lastplayer = board.getLastPlayer();
        winner = board.getWinner();

        this.overflow = board.isOverflow();
        this.GameOver = board.isGameOver();
        this.turn = board.getTurn();

        int n1 = board.getGameBoard().length;
        int n2 = board.getGameBoard()[0].length;
        this.gameBoard = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                this.gameBoard[i][j] = board.getGameBoard()[i][j];
            }
        }
    }




    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColums;
    }

    public void setNumOfColumns(int numOfColumns) {
        this.numOfColums = numOfColumns;
    }

    public int getCheckersInARow() {
        return checkInRow;
    }

    public void setCheckersInARow(int checkersInARow) {
        this.checkInRow = checkersInARow;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setColumn(lastMove.getColumn());
        this.lastMove.setValue(lastMove.getValue());
    }

    public int getLastPlayer() {
        return Lastplayer;
    }

    public void setLastPlayer(int lastPlayer) {
        this.Lastplayer = lastPlayer;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }
    public boolean checkFullColumn(int col) {
        return gameBoard[0][col] != constants.Empty_pos;
    }

    public void setGameBoard(int[][] gameBoard) {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColums; j++) {
                this.gameBoard[i][j] = gameBoard[i][j];
            }
        }
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isGameOver() {
        return GameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.GameOver = isGameOver;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }


     // It prints the board 
     public static void print(int[][] gameBoard) {
        
            printConnect4Board(gameBoard);
        }

        public static void printConnect4Board(int[][] gameBoard) {

            System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
            System.out.println();
            for (int i = 0; i < constants.rows; i++) {
                for (int j = 0; j < constants.columns; j++) {
                    if (j != constants.columns - 1) {
                        if (gameBoard[i][j] != constants.Empty_pos) {
                            System.out.print("| " + gameBoard[i][j] + " ");
                        } else {
                            System.out.print("| " + "-" + " ");
                        }
                    } else {
                        if (gameBoard[i][j] != constants.Empty_pos) {
                            System.out.println("| " + gameBoard[i][j] + " |");
                        } else {
                            System.out.println("| " + "-" + " |");
                        }
                    }
                }
            }
            System.out.println("\n*****************************");
        }
        // It returns the position of the first empty row in a column.
    public int getEmptyRowPosition(int col) {
        int rowPosition = -1;
        for (int row = 0; row < numOfRows; row++) {
            if (gameBoard[row][col] == constants.Empty_pos) {
                rowPosition = row;
            }
        }
        return rowPosition;
    }
    
        // make a move based on the given column
        // we have no worry about the row as it will find the empty position and put in it 
        public void makemove(int col,int player){
            /*The variable "lastMove" must be changed before the variable
             * gameboard because of the method getRowPosition(col)
             */
            try{
            this.lastMove=new Move();
            this.Lastplayer=player;
            this.gameBoard[getEmptyRowPosition(col)][col] = player;
            this.turn++;
            }
            catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Column " + (col + 1) + " is full!");
            setOverflow(true);
        }

            }
     
    // This function is used when we want to search the whole board,
    // without getting out of borders.
    public boolean canMove(int row, int col) {
        return (row > -1) && (col > -1) && (row <= numOfRows - 1) && (col <= numOfColums - 1);
    }  

    /* Generates the children of the state.
     * The max number of the children is "numOfColumns".
     */
    public ArrayList<Board> getChildren(int letter) {
        ArrayList<Board> children = new ArrayList<Board>();
        for (int col = 0; col < numOfColums; col++) {
            if (!checkFullColumn(col)) {
                Board child = new Board(this);
                child.makemove(col, letter);
                children.add(child);
            }
        }
        return children;
    }
     // It returns the frequency of "N" checkers in a row,
    // for the given player, with "checkersInARow - N" adjacent checkers
    // of the same player or empty tiles.
    // The aim is to search for checkers in a row,
    // with a potential to form a Connect-"checkersInARow".
    public int countNInARow(int n, int player) {
        int times = 0;

        // Check for "checkersInARow" consecutive checkers of the same player or empty tiles in a row, horizontally.
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColums; j++) {
                if (canMove(i, j + checkInRow - 1)) {
                    // Check for "n" consecutive checkers of the same player in a row, horizontally.
                    int k = 0;
                    while (k < n && gameBoard[i][j + k] == player) {
                        k++;
                    }
                    // Check for "checkersInARow - n" consecutive checkers of the same player or empty tiles in a row, horizontally.
                    if (k == n) {
                        while (k < checkInRow && (gameBoard[i][j + k] == player || gameBoard[i][j + k] == constants.Empty_pos)) {
                            k++;
                        }
                        if (k == checkInRow) times++;
                    }
                }
            }
        }

        // Check for "checkersInARow" consecutive checkers of the same player or empty tiles in a row, vertically.
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColums; j++) {
                if (canMove(i - numOfColums + 1, j)) {
                    // Check for "N" consecutive checkers of the same player in a row, vertically.
                    int k = 0;
                    while (k < n && gameBoard[i - k][j] == player) {
                        k++;
                    }
                    // Check for "checkersInARow - N" consecutive checkers of the same player or empty tiles in a row, vertically.
                    if (k == checkInRow) {
                        while (k < checkInRow && (gameBoard[i - k][j] == player || gameBoard[i - k][j] == constants.Empty_pos)) {
                            k++;
                        }
                        if (k == checkInRow) times++;
                    }
                }
            }
        }

        // Check for "checkersInARow" consecutive checkers of the same player or empty tiles in a row, in descending diagonal.
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColums; j++) {
                if (canMove(i + checkInRow - 1, j + checkInRow - 1)) {
                    // Check for "N" consecutive checkers of the same player in a row, in descending diagonal.
                    int k = 0;
                    while (k < n && gameBoard[i + k][j + k] == player) {
                        k++;
                    }
                    // Check for "checkersInARow - N" consecutive checkers of the same player or empty tiles in a row, in descending diagonal.
                    if (k == checkInRow) {
                        while (k < checkInRow && (gameBoard[i + k][j + k] == player || gameBoard[i + k][j + k] == constants.Empty_pos)) {
                            k++;
                        }
                        if (k == checkInRow) times++;
                    }
                }
            }
        }

        // Check for "checkersInARow" consecutive checkers of the same player or empty tiles in a row, in ascending diagonal.
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColums; j++) {
                if (canMove(i - checkInRow + 1, j + checkInRow - 1)) {
                    // Check for "n" consecutive checkers of the same player in a row, in ascending diagonal.
                    int k = 0;
                    while (k < n && gameBoard[i - k][j + k] == player) {
                        k++;
                    }
                    // Check for "checkersInARow - N" consecutive checkers of the same player or empty tiles in a row, in ascending diagonal.
                    if (k == checkInRow) {
                        while (k < checkInRow && (gameBoard[i - k][j + k] == player || gameBoard[i - k][j + k] == constants.Empty_pos)) {
                            k++;
                        }
                        if (k == checkInRow) times++;
                    }
                }
            }
        }

        return times;
    }

 /*     Check for an empty cell, i.e. check to find if it is a draw.
     The game is in a draw state, if all cells are full
     and nobody has won the game.
    */
    public boolean checkForDraw() {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfColums; col++) {
                if (gameBoard[row][col] == constants.Empty_pos) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Terminal win check.
     * It checks whether somebody has won the game.
     */
    public boolean checkWinState() {
        int times4InARowPlayer1 = countNInARow(checkInRow, constants.player_1);
        if (times4InARowPlayer1 > 0) {
            setWinner(constants.Empty_pos);
            return true;
        }

        int times4InARowPlayer2 = countNInARow(checkInRow, constants.player_2);
        if (times4InARowPlayer2 > 0) {
            setWinner(constants.player_2);
            return true;
        }

        setWinner(constants.Empty_pos);  // set nobody as the winner
        return false;
    }

    public boolean checkForGameOver() {
        // Check if there is a winner.
        if (checkWinState()) {
            return true;
        }
        return checkForDraw();
    }
    
    /* +1 for each 2 pieces in a row by Player 1, -1 for each 2 pieces in a row by Player 2.
     * +10 for each 3 pieces in a row by Player 1, -10 for each 3 pieces in a row by Player 2.
     * ..
     * +10^i for each (i+2) pieces in a row by Player 1, -10^i for each (i+2) pieces in a row by Player 2.
     * +10^(checkersInARow-2) if "checkersInARow" pieces in a row by Player 1 exist,
     * -10^(checkersInARow-2) if "checkersInARow" pieces in a row by Player 2 exist. */
    public int evaluate() {
        int player1Score = 0;
        int player2Score = 0;

        if (checkWinState()) {
            if (winner == constants.player_1) {
                player1Score = (int) Math.pow(10, (checkInRow - 2));
            } else if (winner == constants.player_2) {
                player2Score = (int) Math.pow(10, (checkInRow - 2));
            }
        }

        for (int i = 0; i < checkInRow - 2; i++) {
            player1Score += countNInARow(i + 2, constants.player_1) * Math.pow(10, i);
            player2Score += countNInARow(i + 2, constants.player_2) * Math.pow(10, i);
        }

        // If the result is 0, then it's a draw.
        return player1Score - player2Score;
    }

        }
    

