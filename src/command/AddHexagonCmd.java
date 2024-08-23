package command;

import adapter.HexagonAdapter;
import mvc.DrawingModel;

public class AddHexagonCmd implements Command {
	
	final HexagonAdapter hexagon;
	final DrawingModel model;
	
	public AddHexagonCmd(HexagonAdapter hexagon, DrawingModel model) {
		this.hexagon=hexagon;
		this.model=model;
	}

	@Override
	public void execute() {
		model.add(hexagon);
	}

	@Override
	public void unexecute() {
		model.remove(hexagon);
	}
	
	@Override
	public String toString() {
		return "Hexagon added->" + hexagon.toString();
	}
}
