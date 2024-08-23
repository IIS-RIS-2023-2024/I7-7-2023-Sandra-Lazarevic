package command;

import geometry.Point;
import mvc.DrawingModel;

public class RemovePointCmd implements Command {

	final Point point;
	final DrawingModel model;
	
	public RemovePointCmd(Point point, DrawingModel model) {
		this.point=point;
		this.model=model;
	}
	
	@Override
	public void execute() {
		model.remove(point);
	}

	@Override
	public void unexecute() {
		model.add(point);
	}
}