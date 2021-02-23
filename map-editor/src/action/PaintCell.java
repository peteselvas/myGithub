package action;

import action.Action;
import cell.Cell;

public class PaintCell extends Action {



    public PaintCell(Cell cell) {
        this.col = cell.getCol();
        this.row = cell.getRow();
    }

    @Override
    public void perform(Cell cell) {
        cell.paint();
    }

    @Override
    public void unPerform(Cell cell) {
        cell.unPaint();
    }

}
