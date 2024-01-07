package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	private DrawingModel model;
	private int index;
	private Shape shape;

	public ToFrontCmd(DrawingModel model, int index, Shape shape) {
		this.model = model;
		this.index = index;
		this.shape = shape;
	}

	@Override
	public void execute() {
		if(index!=model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), index+1, index);
		}
	}

	@Override
	public void unexecute() {
		if(index!=model.getShapes().size()-1) {
			Collections.swap(model.getShapes(), index, index+1);
		}
	}
	
	@Override
	public String toString() {
		return "To front->" + shape.toString();
	}

}
