package command;

import geometry.Line;
import mvc.DrawingModel;

public class AddLineCmd implements Command {

	final Line line;
	final DrawingModel model;
	
	public AddLineCmd(Line line, DrawingModel model) {
		this.line = line;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(line);
	}

	@Override
	public void unexecute() {
		model.remove(line);
	}
	
	@Override
	public String toString() {
		return "Line added->" + line.toString();
	}
}
