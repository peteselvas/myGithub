package cell;

import cell.Cell;
import Editor.Editor;


public class Cursor extends Cell {


    public Cursor(int col, int row) {
        super(col, row);
    }


    public void moveUP() {
        if (row == 0) {       // top border
            return;
        }
        row--;
        rectangle.translate(0, -Editor.CELLSIZE);
    }

    public void moveDown() {
        if (row == Editor.MAX_ROWS - 1) {     // bottom border
            return;
        }
        row++;
        rectangle.translate(0, Editor.CELLSIZE);
    }

    public void moveLeft() {
        if (col == 0) {       // left border
            return;
        }
        col--;
        rectangle.translate(-Editor.CELLSIZE, 0);
    }

    public void moveRight() {
        if (col == Editor.MAX_COLS - 1) {     // right border
            return;
        }
        col++;
        rectangle.translate(Editor.CELLSIZE, 0);
    }



}
