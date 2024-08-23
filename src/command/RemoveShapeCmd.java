package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	private final DrawingModel model;
	private final Shape shape;
	private final int index;

	public RemoveShapeCmd(DrawingModel model, Shape shape, int index) {
		this.model = model;
		this.shape = shape;
		this.index = index;
	}

	@Override
	public void execute() {
		model.remove(shape);
		model.deselectShape(shape);
	}

    @Override
    public void unexecute() {
        model.addShapeAtIndex(shape, index);
    }

	@Override
	public String toString() {
		return "Shape deleted->" + shape.toString();
	}
}