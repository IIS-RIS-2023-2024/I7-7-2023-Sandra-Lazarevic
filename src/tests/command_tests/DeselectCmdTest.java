package tests.command_tests;

import geometry.Shape;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.DeselectCmd;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DeselectCmdTest {

    private DrawingModel model;
    private Shape shape;
    private DeselectCmd deselectCmdTest;

    @BeforeEach
    void setUp() {
        model = mock(DrawingModel.class);
        shape = mock(Shape.class);
        deselectCmdTest = new DeselectCmd(model, shape);

        when(model.getSelectedShapes()).thenReturn(new ArrayList<>());
    }

    @Test
    void testExecute() {
        List<Shape> selectedShapes = model.getSelectedShapes();
        selectedShapes.add(shape);

        deselectCmdTest.execute();

        verify(shape, times(1)).setSelected(false);

        assertTrue(!selectedShapes.contains(shape), "Shape should be removed from the selected shapes list.");
    }

    @Test
    void testUnexecute() {
        List<Shape> selectedShapes = model.getSelectedShapes();
        deselectCmdTest.execute(); 
        deselectCmdTest.unexecute();

        verify(shape, times(1)).setSelected(true);

        assertTrue(selectedShapes.contains(shape), "Shape should be added back to the selected shapes list.");
    }

    @Test
    void testToString() {
        String expectedString = "Deselected->" + shape.toString();
        assertEquals(expectedString, deselectCmdTest.toString(), "toString() should return the expected string.");
    }
}
