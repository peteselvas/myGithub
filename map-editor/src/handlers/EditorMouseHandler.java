package handlers;

import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class EditorMouseHandler implements MouseHandler {
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("clicked " + mouseEvent.getX() + " " + mouseEvent.getY());

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }




}
