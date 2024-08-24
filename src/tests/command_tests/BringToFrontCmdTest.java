package tests.command_tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import command.BringToFrontCmd;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmdTest {

    private DrawingModel model;
    private Shape shape;
    private BringToFrontCmd bringToFrontCmdTest;
    private LinkedList<Shape> shapes;

    @BeforeEach
    void setUp() {
        //kreiranje mock objekata
        model = mock(DrawingModel.class);
        shape = mock(Shape.class);
        shapes = new LinkedList<>();

        shapes.add(mock(Shape.class));
        shapes.add(mock(Shape.class));
        shapes.add(shape);

        when(model.getShapes()).thenReturn(shapes);
        when(model.getIndexOf(shape)).thenReturn(2);

        bringToFrontCmdTest = new BringToFrontCmd(model, shape);
    }

    @Test
    void testExecute() {
        bringToFrontCmdTest.execute();
        assertEquals(shape, shapes.getLast(), "Shape should be moved to the front of the list");
    }

    @Test
    void testUnexecute() {
        bringToFrontCmdTest.execute();
        bringToFrontCmdTest.unexecute();
        assertEquals(shape, shapes.get(2), "Shape should be returned to its original position");
    }

}
