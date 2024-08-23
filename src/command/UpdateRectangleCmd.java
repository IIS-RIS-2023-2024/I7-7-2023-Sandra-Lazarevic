package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {

    private Rectangle oldState;
    private Rectangle newState;
    private Rectangle original;
    
    public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {
        this.oldState = oldState;
        this.newState = newState;
        this.original = (Rectangle) oldState.clone();
    }

    @Override
    public void execute() {
        applyChanges(newState);
    }

    @Override
    public void unexecute() {
        applyChanges(original);
    }

    private void applyChanges(Rectangle rectangle) {
        oldState.getUpperLeftPoint().setX(rectangle.getUpperLeftPoint().getX());
        oldState.getUpperLeftPoint().setY(rectangle.getUpperLeftPoint().getY());
        oldState.setHeight(rectangle.getHeight());
        oldState.setWidth(rectangle.getWidth());
        oldState.setColor(rectangle.getColor());
        oldState.setInnerColor(rectangle.getInnerColor());
    }

    @Override
    public String toString() {
        return "Rectangle updated->" + original.toString() + "->" + newState.toString();
    }
}
