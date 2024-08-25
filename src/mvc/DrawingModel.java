package mvc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import command.Command;
import geometry.Point;
import geometry.Shape;

public class DrawingModel implements Serializable {

	private static final long serialVersionUID = 1L;
	public List<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	public Point startPoint;
	public Shape selectedShape;
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	public int undoCounter = 0;
	
	public void add(Shape shape) {
        shapes.add(shape);
    }
	
    public void addShapeAtIndex(Shape shape, int index) {
        shapes.add(index, shape);
    }
    
	public void remove(Shape shape) {
		shapes.remove(shape);
        selectedShapes.remove(shape);
	}
	
    public void addSelectedShape(Shape shape) {
        selectedShapes.add(shape);
    }
	
    public void deselectShape(Shape shape) {
        selectedShapes.remove(shape);
    }
    
    public void addCommandToUndoStack(Command command) {
        undoCounter++;
        undoStack.push(command);
    }

    public void undoLastCommand() {
        if (!undoStack.isEmpty()) {
            undoCounter--;
            undoStack.pop().unexecute();
        }
    }

    public void addCommandToRedoStack(Command command) {
        redoStack.push(command);
    }

    public void redoLastCommand() {
        if (!redoStack.isEmpty()) {
            redoStack.pop().execute();
        }
    }

    public void clearShapes() {
        shapes.clear();
    }

    public void addMultipleShapes(List<Shape> shapesToAdd) {
        shapes.addAll(shapesToAdd);
    }

    public int getIndexOf(Shape shape) {
        return shapes.indexOf(shape);
    }

    public Shape getShapeByIndex(int index) {
        return shapes.get(index);
    }
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public Stack<Command> getUndoStack() {
		return undoStack;
	}

	public void setUndoStack(Stack<Command> undoStack) {
		this.undoStack = undoStack;
	}

	public Stack<Command> getRedoStack() {
		return redoStack;
	}

	public void setRedoStack(Stack<Command> redoStack) {
		this.redoStack = redoStack;
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	
}