package command;

import geometry.Donut;
import mvc.DrawingModel;

public class RemoveDonutCmd implements Command {

	final Donut donut;
	final DrawingModel model;
	
	public RemoveDonutCmd(Donut donut, DrawingModel model) {
		this.donut = donut;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(donut);
	}

	@Override
	public void unexecute() {
		model.add(donut);
	}
}