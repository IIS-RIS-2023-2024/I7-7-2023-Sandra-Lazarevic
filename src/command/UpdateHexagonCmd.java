package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements Command {

    private final HexagonAdapter oldState;
    private final HexagonAdapter newState;
    private final HexagonAdapter original;

    public UpdateHexagonCmd(HexagonAdapter oldState, HexagonAdapter newState) {
        this.oldState = oldState;
        this.newState = newState;
        this.original = (HexagonAdapter) oldState.clone();
    }

    @Override
    public void execute() {
        updateHexagonState(oldState, newState);
    }

    @Override
    public void unexecute() {
        updateHexagonState(oldState, original);
    }

    private void updateHexagonState(HexagonAdapter targetHexagon, HexagonAdapter sourceHexagon) {
    	targetHexagon.getHexagon().setX(sourceHexagon.getHexagon().getX());
    	targetHexagon.getHexagon().setY(sourceHexagon.getHexagon().getY());
        try {
        	targetHexagon.getHexagon().setR(sourceHexagon.getHexagon().getR());
        } catch (Exception e) {
            e.printStackTrace();
        }
        targetHexagon.getHexagon().setBorderColor(sourceHexagon.getHexagon().getBorderColor());
        targetHexagon.getHexagon().setAreaColor(sourceHexagon.getHexagon().getAreaColor());
    }

    @Override
    public String toString() {
        return "Hexagon updated->" + original.toString() + "->" + newState.toString();
    }
}
