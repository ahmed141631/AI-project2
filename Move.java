package Connect_4;

public class Move {
    private int row;
    private int column;
    private int value;

    
    public Move(){}


    // 3  override constructors to different initlaize
    public Move(int row, int col) {
        this.row = row;
        this.column = col;
    }
    public Move(int value) {
        this.value = value;
    }
      public Move(int row, int col, int value) {
        this.row = row;
        this.column = col;
        this.value = value;
    }

    // getter and setter for each variable in the class

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int col) {
        this.column = col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    
}
