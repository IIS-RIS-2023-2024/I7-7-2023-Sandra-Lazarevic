package command;

import java.util.List;
import geometry.Shape;
import mvc.DrawingModel;

public class DeselectCmd implements Command {

    private final DrawingModel model;
    private final Shape shape;

    public DeselectCmd(DrawingModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape.setSelected(false);
        getSelectedShapes().remove(shape);
    }

    @Override
    public void unexecute() {
        shape.setSelected(true);
        getSelectedShapes().add(shape);
    }

    @Override
    public String toString() {
        return "Deselected->" + shape.toString();
    }

    private List<Shape> getSelectedShapes() {
        return model.getSelectedShapes();
    }
}
