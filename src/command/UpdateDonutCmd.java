package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {

    private final Donut oldState;
    private final Donut newState;
    private final Donut original;
    
    public UpdateDonutCmd(Donut oldState, Donut newState) {
        this.oldState = oldState;
        this.newState = newState;
        this.original = (Donut) oldState.clone();
    }

    @Override
    public void execute() {
        updateDonut(oldState, newState);
    }

    @Override
    public void unexecute() {
        updateDonut(oldState, original);
    }

    private void updateDonut(Donut targetDonut, Donut sourceDonut) {
        targetDonut.getCenter().setX(sourceDonut.getCenter().getX());
        targetDonut.getCenter().setY(sourceDonut.getCenter().getY());
        try {
			targetDonut.setRadius(sourceDonut.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
        targetDonut.setInnerRadius(sourceDonut.getInnerRadius());
        targetDonut.setColor(sourceDonut.getColor());
        targetDonut.setInnerColor(sourceDonut.getInnerColor());
    }
    
    @Override
    public String toString() {
        return "Donut updated->" + original.toString() + "->" + newState.toString();
    }
}
