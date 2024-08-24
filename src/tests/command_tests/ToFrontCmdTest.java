package tests.command_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import geometry.Shape;
import mvc.DrawingModel;
import command.ToFrontCmd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ToFrontCmdTest {

    private DrawingModel model;
    private List<Shape> shapes;
    private Shape testShape1;
    private Shape testShape2;
    private Shape testShape3;

    @BeforeEach
    void setUp() {
    	testShape1 = mock(Shape.class);
    	testShape2 = mock(Shape.class);
    	testShape3 = mock(Shape.class);

        shapes = new ArrayList<>();
        shapes.add(testShape1);
        shapes.add(testShape2);
        shapes.add(testShape3);

        model = mock(DrawingModel.class);
        when(model.getShapes()).thenReturn(shapes);
    }

    @Test
    void testExecute() {
        ToFrontCmd cmd = new ToFrontCmd(model, 1, testShape2);
        cmd.execute();

        verify(model, times(2)).getShapes();
        assertEquals(testShape1, shapes.get(0));
        assertEquals(testShape3, shapes.get(1));
        assertEquals(testShape2, shapes.get(2));
    }

    @Test
    void testUnexecute() {
        ToFrontCmd cmd = new ToFrontCmd(model, 1, testShape2);

        cmd.execute();
        cmd.unexecute();

        verify(model, atLeastOnce()).getShapes(); // or use 'times(n)' if you know the exact number
        assertEquals(testShape1, shapes.get(0));
        assertEquals(testShape2, shapes.get(1));
        assertEquals(testShape3, shapes.get(2));
    }


    @Test
    void testToString() {
        ToFrontCmd cmd = new ToFrontCmd(model, 1, testShape2);
        when(testShape2.toString()).thenReturn("Shape2");
        assertEquals("To front->Shape2", cmd.toString());
    }
}
