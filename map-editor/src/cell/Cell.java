package cell;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import Editor.Editor;

public class Cell {

    protected int row;
    protected int col;
    protected Rectangle rectangle;
    protected boolean painted;

    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
        this.rectangle = new Rectangle(col * Editor.CELLSIZE + Editor.PADDING,
                row * Editor.CELLSIZE + Editor.PADDING,
                Editor.CELLSIZE
                , Editor.CELLSIZE);
        this.painted = false;
    }


    public void draw() {
        this.rectangle.draw();
    }

    public void fill() {
        this.rectangle.fill();
    }

    public void setColor(Color color) {
        this.rectangle.setColor(color);
    }



    public boolean isPainted() {
        return painted;
    }

    public void paint() {
        painted = true;
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
    }

    public void unPaint() {
        painted = false;
        rectangle.setColor(Color.WHITE);
        rectangle.fill();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public void setPainted(boolean painted) {
        this.painted = painted;
    }
}
