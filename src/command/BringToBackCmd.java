package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

    private final DrawingModel model;
    private final Shape shape;
    private final int index;

    public BringToBackCmd(DrawingModel model, Shape shape, int index) {
        this.model = model;
        this.shape = shape;
        this.index = index;
    }

    @Override
    public void execute() {
        moveShape(0);
    }

    @Override
    public void unexecute() {
        moveShape(index);
    }

    private void moveShape(int targetIndex) {
        model.getShapes().remove(shape);
        model.getShapes().add(targetIndex, shape);
    }

    @Override
    public String toString() {
        return "Bring to back -> " + shape;
    }
}
