package tests.mvc_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.Command;
import geometry.Shape;
import mvc.DrawingModel;
import java.util.ArrayList;
import java.util.List;

class DrawingModelTest {

    private DrawingModel modelTest;
    private Shape shape;
    private Command command;

    @BeforeEach
    void setUp() {
        modelTest = new DrawingModel();
        shape = mock(Shape.class);
        command = mock(Command.class);
    }

    @Test
    void testAddShape() {
        modelTest.add(shape);
        assertTrue(modelTest.getShapes().contains(shape));
    }

    @Test
    void testAddShapeAtIndex() {
        Shape shape2 = mock(Shape.class);
        modelTest.add(shape);
        modelTest.addShapeAtIndex(shape2, 0);
        assertEquals(shape2, modelTest.getShapeByIndex(0));
        assertEquals(shape, modelTest.getShapeByIndex(1));
    }

    @Test
    void testRemoveShape() {
        modelTest.add(shape);
        modelTest.remove(shape);
        assertFalse(modelTest.getShapes().contains(shape));
        assertFalse(modelTest.getSelectedShapes().contains(shape));
    }

    @Test
    void testAddSelectedShape() {
        modelTest.addSelectedShape(shape);
        assertTrue(modelTest.getSelectedShapes().contains(shape));
    }

    @Test
    void testDeselectShape() {
        modelTest.addSelectedShape(shape);
        modelTest.deselectShape(shape);
        assertFalse(modelTest.getSelectedShapes().contains(shape));
    }

    @Test
    void testClearShapes() {
        modelTest.add(shape);
        modelTest.clearShapes();
        assertTrue(modelTest.getShapes().isEmpty());
    }

    @Test
    void testAddMultipleShapes() {
        Shape shape1 = mock(Shape.class);
        Shape shape2 = mock(Shape.class);
        List<Shape> shapes = new ArrayList<>();
        shapes.add(shape1);
        shapes.add(shape2);
        modelTest.addMultipleShapes(shapes);
        assertTrue(modelTest.getShapes().contains(shape1));
        assertTrue(modelTest.getShapes().contains(shape2));
    }

    @Test
    void testGetIndexOfShape() {
        modelTest.add(shape);
        int index = modelTest.getIndexOf(shape);
        assertEquals(0, index);
    }

    @Test
    void testGetShapeByIndex() {
        modelTest.add(shape);
        Shape retrievedShape = modelTest.getShapeByIndex(0);
        assertEquals(shape, retrievedShape);
    }

    @Test
    void testAddCommandToUndoStack() {
        modelTest.addCommandToUndoStack(command);
        assertEquals(1, modelTest.getUndoStack().size());
    }

    @Test
    void testUndoLastCommand() {
        modelTest.addCommandToUndoStack(command);
        modelTest.undoLastCommand();
        verify(command).unexecute();
        assertTrue(modelTest.getUndoStack().isEmpty());
    }

    @Test
    void testAddCommandToRedoStack() {
        modelTest.addCommandToRedoStack(command);
        assertEquals(1, modelTest.getRedoStack().size());
    }

    @Test
    void testRedoLastCommand() {
        modelTest.addCommandToRedoStack(command);
        modelTest.redoLastCommand();
        verify(command).execute();
        assertTrue(modelTest.getRedoStack().isEmpty());
    }
}

