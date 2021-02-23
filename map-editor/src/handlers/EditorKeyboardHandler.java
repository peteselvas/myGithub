package handlers;

import Editor.Editor;
import cell.Cursor;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class EditorKeyboardHandler implements KeyboardHandler {

    private Editor editor;
    private Cursor cursor;


    public EditorKeyboardHandler(Editor editor, Cursor cursor) {
        this.editor = editor;
        this.cursor = cursor;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()){

            case KeyboardEvent.KEY_UP:
                cursor.moveUP();
                break;

            case KeyboardEvent.KEY_DOWN:
                cursor.moveDown();
                break;
            case KeyboardEvent.KEY_LEFT:
                cursor.moveLeft();
                break;

            case KeyboardEvent.KEY_RIGHT:
                cursor.moveRight();
                break;
            case KeyboardEvent.KEY_SPACE:
                editor.perform();
                break;
            case KeyboardEvent.KEY_U:
                editor.unPerform();
                break;
            case KeyboardEvent.KEY_R:
                editor.rePerform();
                break;
            case KeyboardEvent.KEY_C:
                editor.clearAllCells();
                break;
            case KeyboardEvent.KEY_S:
                editor.saveDrawing();
                break;
            case KeyboardEvent.KEY_L:
                editor.loadDrawing();
                break;
        }

        cursor.fill();
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
