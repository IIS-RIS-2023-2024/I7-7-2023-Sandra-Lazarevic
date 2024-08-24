package command;

import geometry.Shape;
import mvc.DrawingModel;
import java.util.List;

public class SelectCmd implements Command {

    private final DrawingModel model;
    private final Shape shape;

    public SelectCmd(DrawingModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape.setSelected(true);
        getSelectedShapes().add(shape);
    }

    @Override
    public void unexecute() {
        shape.setSelected(false);
        getSelectedShapes().remove(shape);
    }

    @Override
    public String toString() {
        return "Selected->" + shape.toString();
    }

    private List<Shape> getSelectedShapes() {
        return model.getSelectedShapes();
    }
}
