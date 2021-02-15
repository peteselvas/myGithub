import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class EditorKeyboardHandler implements KeyboardHandler {

    private Editor editor;
    private Cell cursor;


    public EditorKeyboardHandler(Editor editor, Cell cursor) {
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
                editor.markCell(cursor.getCol(), cursor.getRow());
                break;
            case KeyboardEvent.KEY_C:
                editor.clear();
                break;
            case KeyboardEvent.KEY_S:
                editor.save();
                break;
            case KeyboardEvent.KEY_L:
                editor.load();
                break;



        }

        cursor.fill();
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
