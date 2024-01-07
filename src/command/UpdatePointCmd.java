package command;

import geometry.Point;
//import java.awt.Color;

public class UpdatePointCmd implements Command {
	
	private Point oldState;
	private Point newState;
	private Point original;
	
	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState=oldState;
		this.newState=newState;
		original = (Point) oldState.clone();
		}


	@Override
	public void execute() {
		/*original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setColor(oldState.getColor());*/
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());

	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
	}
	
	@Override
	public String toString() {
		return "Updated->" + original.toString() + "->" + newState.toString();
	}


}
