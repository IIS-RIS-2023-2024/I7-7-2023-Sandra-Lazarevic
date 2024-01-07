package command;

import geometry.Donut;
//import geometry.Point;

public class UpdateDonutCmd implements Command {

	private Donut oldState;
	private Donut newState;
	private Donut original;
	
	public UpdateDonutCmd(Donut oldState, Donut newState) {
		this.oldState=oldState;
		this.newState=newState;
		original=(Donut)oldState.clone();
	}

	@Override
	public void execute() {
		/*original.getCenter().setX(oldState.getCenter().getX());
		original.getCenter().setY(oldState.getCenter().getY());
		try {
			original.setR(oldState.getR());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			original.setR(oldState.getR());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		try {
			oldState.setRadius(newState.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldState.setInnerRadius(newState.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());


	}

	@Override
	public void unexecute() {
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		try {
			oldState.setRadius(original.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			oldState.setInnerRadius(original.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
	}
	
	@Override
	public String toString() {
		return "Updated->" + original.toString() + "->" + newState.toString();
	}


}

