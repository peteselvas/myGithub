package action;

import action.Action;
import cell.Cell;

public class ClearCell extends Action {



    public ClearCell(Cell cell) {
        this.col = cell.getCol();
        this.row = cell.getRow();
    }

    @Override
    public void perform(Cell cell) {
        cell.unPaint();
    }

    @Override
    public void unPerform(Cell cell) {
        cell.paint();
    }
}
