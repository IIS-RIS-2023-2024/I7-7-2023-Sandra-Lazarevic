package command;

import geometry.Point;

public class UpdatePointCmd implements Command {
	
	private final Point oldState;
	private final Point newState;
	private final Point original;
	
	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState=oldState;
		this.newState=newState;
		original = clonePoint(oldState);
	}


	@Override
	public void execute() {
		updatePoint(oldState, newState);
	}

	@Override
	public void unexecute() {
        updatePoint(oldState, original);

	}
	
    private void updatePoint(Point targetPoint, Point sourcePoint) {
        targetPoint.setX(sourcePoint.getX());
        targetPoint.setY(sourcePoint.getY());
        targetPoint.setColor(sourcePoint.getColor());
    }
    
    private Point clonePoint(Point point) {
        return (Point) point.clone();
    }
    
	@Override
	public String toString() {
		return "Point updated->" + original.toString() + "->" + newState.toString();
	}


}
