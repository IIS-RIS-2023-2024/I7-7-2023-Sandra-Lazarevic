package tests.command_tests;

import geometry.Shape;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.RemoveShapeCmd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveShapeCmdTest {

    private DrawingModel model;
    private Shape shape;
    private RemoveShapeCmd removeShapeCmdTest;
    private int index;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        shape = mock(Shape.class);
        index = 0;

        removeShapeCmdTest = new RemoveShapeCmd(model, shape, index);
    }

    @Test
    public void testExecute() {
        removeShapeCmdTest.execute();
        
        verify(model).remove(shape);
        verify(model).deselectShape(shape);
    }

    @Test
    public void testUnexecute() {
        removeShapeCmdTest.unexecute();
        verify(model).addShapeAtIndex(shape, index);
    }

    @Test
    public void testToString() {
        when(shape.toString()).thenReturn("TestShape");
        assertEquals("Shape deleted->TestShape", removeShapeCmdTest.toString());
    }
}
