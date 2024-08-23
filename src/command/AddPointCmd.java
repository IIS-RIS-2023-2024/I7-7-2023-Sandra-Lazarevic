package command;

import geometry.Point;
import mvc.DrawingModel;

public class AddPointCmd implements Command {
	
	final Point point;
	final DrawingModel model;
	
	public AddPointCmd(Point point, DrawingModel model) {
		this.point=point;
		this.model=model;
	}

	@Override
	public void execute() {
		model.add(point);
	}

	@Override
	public void unexecute() {
		model.remove(point);
	}
	
	@Override
	public String toString() {
		return "Point added->"+point.toString();
	}

}
