package Editor;

import action.Action;
import action.ClearAllCells;
import action.ClearCell;
import action.PaintCell;
import cell.Cell;
import cell.Cursor;
import handlers.EditorKeyboardHandler;
import handlers.EditorMouseHandler;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;

import java.io.*;
import java.util.ArrayList;

public class Editor {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 4;
    public static final int MAX_ROWS = 80;       //80;
    public static final int MAX_COLS = 100;   //100;
    private Rectangle outerRectangle;
    private Cell[][] grid;
    private ArrayList<Action> actions;
    private int actionCounter;
    private Cursor cursor;
    private EditorKeyboardHandler editorKeyboardHandler;
    private Keyboard keyboard;
    private EditorMouseHandler editorMouseHandler;
    private Mouse mouse;

    public static Color GRID_COLOR = Color.WHITE;
    private String path = "/Users/codecadet/myGithub/map-editor/myDraw.txt";



    public Editor() {

        grid = new Cell[MAX_COLS][MAX_ROWS];
        outerRectangle = new Rectangle(PADDING, PADDING, MAX_COLS * CELLSIZE + PADDING, MAX_ROWS * CELLSIZE + PADDING);

        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        cursor = new Cursor((int) (Math.floor(MAX_COLS / 2)), (int) (Math.floor(MAX_ROWS / 2)));

        editorKeyboardHandler = new EditorKeyboardHandler(this, cursor);
        keyboard = new Keyboard(editorKeyboardHandler);

        editorMouseHandler = new EditorMouseHandler();
        mouse = new Mouse(editorMouseHandler);

        actions = new ArrayList<>();
        actionCounter = 0;
    }

    public void init() {

        // draw grid
        drawInitialGrid();
        outerRectangle.draw();

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
        keyboard.addEventListener(KeyboardEvent.KEY_U, KeyboardEventType.KEY_PRESSED);      // undo delete
        keyboard.addEventListener(KeyboardEvent.KEY_R, KeyboardEventType.KEY_PRESSED);      // reDo delete

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    public void drawInitialGrid(){
        if (GRID_COLOR == Color.WHITE){
            return;
        }

        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                grid[i][j].setColor(GRID_COLOR);
                grid[i][j].draw();
            }
        }
    }


    public void clearAllCells() {

        ClearAllCells clearAllCells = new ClearAllCells(grid);
        clearAllCells.perform(cursor);

        if(actionCounter != actions.size()){
            actions.subList(actionCounter, actions.size()).clear();
        }
        actions.add(actionCounter, clearAllCells);
        actionCounter++;

        outerRectangle.delete();
        outerRectangle.draw();
    }

    public void saveDrawing() {
        String line = "";

        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int j = 0; j < MAX_ROWS; j++) {
                for (int i = 0; i < MAX_COLS; i++) {
                    line = grid[i][j].isPainted() ? line + "1" : line + "0";
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line= "";
            }
            bufferedWriter.flush();
            bufferedWriter.close();

            //reset undo action
            actionCounter = 0;
            actions.clear();
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
                        grid[i][j].paint();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void perform() {
        Cell currentCell = grid[cursor.getCol()][cursor.getRow()];
        Action action = currentCell.isPainted() ? new ClearCell(currentCell) : new PaintCell(currentCell);
        action.perform(currentCell);
        if(actionCounter != actions.size()){
            actions.subList(actionCounter, actions.size()).clear();
        }
        actions.add(actionCounter, action);
        actionCounter++;
    }

    public void unPerform(){
        if(actionCounter <= 0){
            return;
        }
        Action action = actions.get(actionCounter - 1);
        action.unPerform(grid[action.getCol()][action.getRow()]);
        actionCounter--;
    }

    public void rePerform(){
        if(actionCounter < 0 || actionCounter == actions.size()){
            return;
        }
        Action lastAction = actions.get(actionCounter);
        lastAction.perform(grid[lastAction.getCol()][lastAction.getRow()]);
        actionCounter++;
    }



}
