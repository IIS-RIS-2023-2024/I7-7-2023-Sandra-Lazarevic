package tests.command_tests;

import geometry.Shape;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.BringToBackCmd;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BringToBackCmdTest {

    private DrawingModel model;
    private Shape shape;
    private BringToBackCmd bringToBackCmdTest;
    private List<Shape> shapes;

    @BeforeEach
    void setUp() {
        model = mock(DrawingModel.class);
        shape = mock(Shape.class);
        shapes = new ArrayList<>();
        bringToBackCmdTest = new BringToBackCmd(model, shape, 1);

        //model vraca listu shapes
        when(model.getShapes()).thenReturn(shapes);
    }

    @Test
    void testExecute() {
        shapes.add(mock(Shape.class)); 
        shapes.add(shape); 
        shapes.add(mock(Shape.class));

        bringToBackCmdTest.execute();
        assertEquals(shape, shapes.get(0), "Shape should be at index 0 after execute.");
    }

    @Test
    void testUnexecute() {
        shapes.add(mock(Shape.class));
        shapes.add(shape);

        bringToBackCmdTest.execute();
        bringToBackCmdTest.unexecute();
        assertEquals(shape, shapes.get(1), "Shape should be at index 1 after unexecute.");
    }

    @Test
    void testToString() {
        when(shape.toString()).thenReturn("ShapeDetails");
        String expected = "Bring to back -> ShapeDetails";
        String actual = bringToBackCmdTest.toString();
        assertEquals(expected, actual, "toString() should return the correct string representation.");
    }
}

