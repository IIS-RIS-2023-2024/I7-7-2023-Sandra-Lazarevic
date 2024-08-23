package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command {

    private final Circle oldState;
    private final Circle newState;
    private final Circle original;

    public UpdateCircleCmd(Circle oldState, Circle newState) {
        this.oldState = oldState;
        this.newState = newState;
        this.original = (Circle) oldState.clone();
    }

    @Override
    public void execute() {
        updateCircle(oldState, newState);
    }

    @Override
    public void unexecute() {
        updateCircle(oldState, original);
    }

    private void updateCircle(Circle targetCircle, Circle sourceCircle) {
        targetCircle.getCenter().setX(sourceCircle.getCenter().getX());
        targetCircle.getCenter().setY(sourceCircle.getCenter().getY());
        try {
			targetCircle.setRadius(sourceCircle.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		} 
        targetCircle.setColor(sourceCircle.getColor());
        targetCircle.setInnerColor(sourceCircle.getInnerColor());
    }

    @Override
    public String toString() {
        return "Circle updated->" + original.toString() + "->" + newState.toString();
    }
}
