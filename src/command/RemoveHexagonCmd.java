package command;

import adapter.HexagonAdapter;
import mvc.DrawingModel;

public class RemoveHexagonCmd implements Command {

	final HexagonAdapter hexagon;
	final DrawingModel model;
	
	public RemoveHexagonCmd(HexagonAdapter hexagon, DrawingModel model) {
		this.hexagon=hexagon;
		this.model=model;
	}

	@Override
	public void execute() {
		model.remove(hexagon);
	}

	@Override
	public void unexecute() {
		model.add(hexagon);
	}
}

