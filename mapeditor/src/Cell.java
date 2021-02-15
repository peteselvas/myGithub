import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    private int row;
    private int col;
    private Rectangle rectangle;
    private boolean isMarked;

    public Cell(int col, int row){
        this.col = col;
        this.row = row;
        this.rectangle = new Rectangle(col * Editor.CELLSIZE + Editor.PADDING,
                row * Editor.CELLSIZE + Editor.PADDING,
                Editor.CELLSIZE
                , Editor.CELLSIZE);
        this.isMarked = false;
    }


    public void draw(){
        this.rectangle.draw();
    }

    public void fill(){
        this.rectangle.fill();
    }

    public void delete(){
        this.rectangle.delete();
    }

    public void setColor(Color color){
        this.rectangle.setColor(color);
    }

    public Color getColor(){
        return this.rectangle.getColor();
    }


    public void moveUP(){
        if(row == 0){       // top border
            return;
        }
        row--;
        rectangle.translate(0, -Editor.CELLSIZE);
    }

    public void moveDown(){
        if(row == Editor.MAX_ROWS - 1){     // bottom border
            return;
        }
        row++;
        rectangle.translate(0, Editor.CELLSIZE);
    }

    public void moveLeft(){
        if(col == 0){       // left border
            return;
        }
        col--;
        rectangle.translate(-Editor.CELLSIZE, 0);
    }

    public void moveRight(){
        if(col == Editor.MAX_COLS - 1){     // right border
            return;
        }
        col++;
        rectangle.translate(Editor.CELLSIZE, 0);
    }






    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void mark() {
        isMarked = true;
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
    }

    public void unMark(){
        isMarked = false;
    }

}
