package action;

import cell.Cell;

public abstract class Action {

    int col;
    int row;


    public void perform(Cell cell){};

    public void unPerform(Cell cell){};


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
