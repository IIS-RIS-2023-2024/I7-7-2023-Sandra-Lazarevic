package tests.command_tests;

import geometry.Shape;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.SelectCmd;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SelectCmdTest {

    private DrawingModel model;
    private Shape shape;
    private SelectCmd selectCmdTest;

    @BeforeEach
    void setUp() {
        model = mock(DrawingModel.class);
        shape = mock(Shape.class);
        
        selectCmdTest = new SelectCmd(model, shape);
        
        when(model.getSelectedShapes()).thenReturn(new ArrayList<>());
    }

    @Test
    void testExecute() {
        selectCmdTest.execute();

        verify(shape, times(1)).setSelected(true);

        List<Shape> selectedShapes = model.getSelectedShapes();
        assertTrue(selectedShapes.contains(shape), "Shape should be added to the selected shapes list.");
    }

    @Test
    void testUnexecute() {
        selectCmdTest.execute();

        selectCmdTest.unexecute();

        verify(shape, times(1)).setSelected(false);

        List<Shape> selectedShapes = model.getSelectedShapes();
        assertTrue(!selectedShapes.contains(shape), "Shape should be removed from the selected shapes list.");
    }

    @Test
    void testToString() {
        String expectedString = "Selected->" + shape.toString();
        assertEquals(expectedString, selectCmdTest.toString(), "toString() should return the expected string.");
    }
}

