package command;

import geometry.Line;
//import geometry.Point;

//import java.awt.Color;

public class UpdateLineCmd implements Command {

	private Line oldState;
	private Line newState;
	private Line original;
	
	
	public UpdateLineCmd(Line oldState, Line newState) {
		this.oldState=oldState;
		this.newState=newState;
		 original= (Line) oldState.clone();
	}

	@Override
	public void execute() {
		
		/*System.out.println(original);
		original.getStartPoint().setX(oldState.getStartPoint().getX());
		original.getStartPoint().setY(oldState.getStartPoint().getY());
		original.getEndPoint().setX(oldState.getEndPoint().getX());
		original.getEndPoint().setY(oldState.getEndPoint().getY());
		original.setColor(oldState.getColor());*/
		
		oldState.getStartPoint().setX(newState.getStartPoint().getX());;
		oldState.getStartPoint().setY(newState.getStartPoint().getY());
		oldState.getEndPoint().setX(newState.getEndPoint().getX());
		oldState.getEndPoint().setY(newState.getEndPoint().getY());
		oldState.setColor(newState.getColor());

	}

	@Override
	public void unexecute() {
		oldState.getStartPoint().setX(original.getStartPoint().getX());		
		oldState.getStartPoint().setY(original.getStartPoint().getY());
		oldState.getEndPoint().setX(original.getEndPoint().getX());
		oldState.getEndPoint().setY(original.getEndPoint().getY());
		oldState.setColor(original.getColor());
	}
	
	@Override
	public String toString() {
		return "Updated->" + original.toString() + "->" + newState.toString();
	}


}