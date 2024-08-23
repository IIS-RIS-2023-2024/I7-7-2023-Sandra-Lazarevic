package command;

import geometry.Donut;
import mvc.DrawingModel;

public class AddDonutCmd implements Command {

	final Donut donut;
	final DrawingModel model;
	
	public AddDonutCmd(Donut donut, DrawingModel model) {
		this.donut = donut;
		this.model = model;
	}

	@Override
	public void execute() {
		model.add(donut);
	}

	@Override
	public void unexecute() {
		model.remove(donut);
	}
	
	@Override
	public String toString() {
		return "Donut added->" + donut.toString();
	}
}
