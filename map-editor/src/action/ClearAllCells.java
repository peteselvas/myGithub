package action;

import Editor.Editor;
import cell.Cell;

public class ClearAllCells extends Action {

    private Cell[][] grid;
    private Cell[][] backupGrid;

    public ClearAllCells(Cell[][] grid){
        this.grid = grid;

        backupGrid = new Cell[Editor.MAX_COLS][Editor.MAX_ROWS];
        for (int i = 0; i < Editor.MAX_COLS; i++) {
            for (int j = 0; j < Editor.MAX_ROWS; j++) {
                backupGrid[i][j] = new Cell(i, j);
            }
        }
    }


    @Override
    public void perform(Cell cell) {

        Cell currentCell;

        for (int i = 0; i < Editor.MAX_COLS; i++) {
            for (int j = 0; j < Editor.MAX_ROWS; j++) {
                currentCell = grid[i][j];
                if (currentCell.isPainted()){
                    currentCell.unPaint();
                    backupGrid[i][j].setPainted(true);
                }
            }
        }
    }

    @Override
    public void unPerform(Cell cell) {
        for (int i = 0; i < Editor.MAX_COLS; i++) {
            for (int j = 0; j < Editor.MAX_ROWS; j++) {
                if (backupGrid[i][j].isPainted()){
                    grid[i][j].paint();
                }
            }
        }
    }
}
