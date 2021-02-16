import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

import java.io.*;

public class Editor {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 4;
    public static final int MAX_ROWS = 80;       //80;
    public static final int MAX_COLS = 100;   //100;
    private Cell[][] grid;
    private Cell cursor;
    private EditorKeyboardHandler editorKeyboardHandler;
    private Keyboard keyboard;
    private String path = "/Users/codecadet/myGithub/mapeditor/myDraw.txt";
    private Color gridColor = Color.WHITE;

    public Editor() {

        grid = new Cell[MAX_COLS][MAX_ROWS];

        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        cursor = new Cell((int) (Math.floor(MAX_COLS / 2)), (int) (Math.floor(MAX_ROWS / 2)));

        editorKeyboardHandler = new EditorKeyboardHandler(this, cursor);
        keyboard = new Keyboard(editorKeyboardHandler);
    }

    public void init() {

        // draw grid
        drawInitialGrid();

        // init cursor
        cursor.setColor(Color.BLUE);
        cursor.fill();

        keyboard.addEventListener(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED);  // mark cell
        keyboard.addEventListener(KeyboardEvent.KEY_C, KeyboardEventType.KEY_PRESSED);      // clear drawing
        keyboard.addEventListener(KeyboardEvent.KEY_S, KeyboardEventType.KEY_PRESSED);      // save drawing
        keyboard.addEventListener(KeyboardEvent.KEY_L, KeyboardEventType.KEY_PRESSED);      // load drawing
    }

    public void drawInitialGrid(){
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                grid[i][j].setColor(gridColor);
                grid[i][j].draw();
            }
        }
    }

    public void drawDrawing(){
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                if (grid[i][j].isMarked()) {
                    grid[i][j].fill();
                }
            }
        }
    }


    public void clearAllCells() {
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                if (grid[i][j].isMarked()){
                    unMarkCell(i, j);
                    grid[i][j].setColor(gridColor);
                    grid[i][j].draw();
                }
            }
        }
    }

    public void saveDrawing() {
        String line = "";

        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int j = 0; j < MAX_ROWS; j++) {
                for (int i = 0; i < MAX_COLS; i++) {
                    line = grid[i][j].isMarked() ? line + "1" : line + "0";
                }

                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line= "";
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDrawing(){

        String line;

        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            clearAllCells();

            for (int j = 0; j < MAX_ROWS; j++) {
                line = bufferedReader.readLine();
                for (int i = 0; i < MAX_COLS; i++) {
                    if (line.charAt(i) == '1'){
                        markCell(i, j);
                    }
                }
            }

            drawDrawing();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void markCell(int col, int row) {
        grid[col][row].mark();
    }

    public void unMarkCell(int col, int row) {
        grid[col][row].mark();
    }

}
