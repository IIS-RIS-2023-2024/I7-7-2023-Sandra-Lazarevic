package command;

import geometry.Circle;
import mvc.DrawingModel;

public class RemoveCircleCmd implements Command {

	final Circle circle;
	final DrawingModel model;
	
	public RemoveCircleCmd(Circle circle, DrawingModel model) {
		this.circle = circle;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(circle);
	}

	@Override
	public void unexecute() {
		model.add(circle);
	}
}