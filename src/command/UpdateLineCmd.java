package command;

import geometry.Line;

public class UpdateLineCmd implements Command {

    private final Line oldState;
    private final Line newState;
    private final Line original;

    public UpdateLineCmd(Line oldState, Line newState) {
        this.oldState = oldState;
        this.newState = newState;
        this.original = (Line) oldState.clone();
    }

    @Override
    public void execute() {
    	updateLine(oldState, newState);
    }

    @Override
    public void unexecute() {
    	updateLine(oldState, original);
    }

    private void updateLine(Line targetLine, Line sourceLine) {
    	targetLine.getStartPoint().setX(sourceLine.getStartPoint().getX());
    	targetLine.getStartPoint().setY(sourceLine.getStartPoint().getY());
    	targetLine.getEndPoint().setX(sourceLine.getEndPoint().getX());
    	targetLine.getEndPoint().setY(sourceLine.getEndPoint().getY());
    	targetLine.getStartPoint().setColor(sourceLine.getStartPoint().getColor());
    	targetLine.getEndPoint().setColor(sourceLine.getEndPoint().getColor());
    }

    @Override
    public String toString() {
        return "Line updated->" + original.toString() + "->" + newState.toString();
    }
}
