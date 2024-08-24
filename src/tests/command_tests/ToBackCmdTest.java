package tests.command_tests;

import geometry.Shape;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.ToBackCmd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ToBackCmdTest {

    private DrawingModel model;
    private List<Shape> shapes;
    private Shape shape;
    private ToBackCmd toBackCmdTest;

    @BeforeEach
    void setUp() {
        model = mock(DrawingModel.class);
        shapes = new ArrayList<>();
        shape = mock(Shape.class);
        //dodavanje oblika u listu
        for (int i = 0; i < 3; i++) {
            shapes.add(mock(Shape.class));
        }

        shapes.add(shape); //oblik za pomeranje
        when(model.getShapes()).thenReturn(shapes);
        toBackCmdTest = new ToBackCmd(model, 2, shape);
    }

    @Test
    void testExecute() {
        List<Shape> expectedShapes = new ArrayList<>(shapes);
        Collections.swap(expectedShapes, 1, 2);

        toBackCmdTest.execute();
        verify(model).getShapes();
        assertEquals(expectedShapes, shapes, "Shapes list should match expected state after execute.");
    }

    @Test
    void testUnexecute() {
        toBackCmdTest.execute();

        List<Shape> expectedShapes = new ArrayList<>(shapes);
        Collections.swap(expectedShapes, 1, 2);

        toBackCmdTest.unexecute();
        verify(model, times(2)).getShapes();
        assertEquals(expectedShapes, shapes, "Shapes list should match expected state after unexecute.");
    }

    @Test
    void testToString() {
        String expected = "To back->" + shape.toString();
        assertEquals(expected, toBackCmdTest.toString());
    }
}
