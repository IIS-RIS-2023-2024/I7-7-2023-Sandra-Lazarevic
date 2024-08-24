package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

    private final DrawingModel model;
    private final Shape shape;
    private int originalIndex;

    public BringToFrontCmd(DrawingModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        saveOriginalIndex();
        moveShapeToFront();
    }

    @Override
    public void unexecute() {
        removeShapeFromFront();
        restoreShapeToOriginalIndex();
    }

    private void saveOriginalIndex() {
        originalIndex = model.getIndexOf(shape);
    }

    private void moveShapeToFront() {
        model.getShapes().remove(shape);
        model.getShapes().add(shape);
    }

    private void removeShapeFromFront() {
        model.getShapes().remove(shape);
    }

    private void restoreShapeToOriginalIndex() {
        model.getShapes().add(originalIndex, shape);
    }

    @Override
    public String toString() {
        return "Bring to front -> " + shape;
    }
}
