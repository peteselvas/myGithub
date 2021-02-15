import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

import java.io.*;

public class Editor {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 10;
    public static final int MAX_ROWS = 80;       //80;
    public static final int MAX_COLS = 100;   //100;
    private Cell[][] coordenates;
    private Cell cursor;
    private EditorKeyboardHandler editorKeyboardHandler;
    private Keyboard keyboard;
    private String path = "/Users/codecadet/myGithub/mapeditor/myDraw";


    public Editor() {

        coordenates = new Cell[MAX_COLS][MAX_ROWS];

        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                coordenates[i][j] = new Cell(i, j);
            }
        }

        cursor = new Cell((int) (Math.floor(MAX_COLS / 2)), (int) (Math.floor(MAX_ROWS / 2)));

        editorKeyboardHandler = new EditorKeyboardHandler(this, cursor);
        keyboard = new Keyboard(editorKeyboardHandler);

    }

    public void init() {

        // draw grid
        draw();

        // init cursor
        cursor.setColor(Color.BLUE);
        cursor.fill();

        keyboard.addEventListener(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_C, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_S, KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(KeyboardEvent.KEY_L, KeyboardEventType.KEY_PRESSED);
    }

    public void draw(){
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                coordenates[i][j].draw();
            }
        }
    }

    public void fill(){
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                if (coordenates[i][j].isMarked()) {
                    coordenates[i][j].fill();
                }
            }
        }
    }
    public void markCell(int col, int row) {
        coordenates[col][row].mark();
    }

    public void clear() {
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                if (coordenates[i][j].isMarked()) {
                    coordenates[i][j].draw();
                }
            }
        }
    }

    public void save() {
        String line = "";

        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int j = 0; j < MAX_ROWS; j++) {
                for (int i = 0; i < MAX_COLS; i++) {
                    line = coordenates[i][j].isMarked() ? line + "1" : line + "0";
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

    public void load(){

        String line;

        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            clearAll();

            for (int j = 0; j < MAX_ROWS; j++) {
                line = bufferedReader.readLine();
                for (int i = 0; i < MAX_COLS; i++) {
                    if (line.charAt(i) == '1'){
                        coordenates[i][j].mark();
                    }
                }
            }

            fill();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        for (int i = 0; i < MAX_COLS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                coordenates[i][j].unMark();
            }
        }
    }


}
