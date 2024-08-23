package tests.command_tests;

import geometry.Rectangle;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.AddRectangleCmd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddRectangleCmdTest {

    private DrawingModel model;
    private Rectangle rectangle;
    private AddRectangleCmd addRectangleCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        rectangle = mock(Rectangle.class);
        addRectangleCmdTest = new AddRectangleCmd(rectangle, model);
    }

    @Test
    public void testExecute() {
        addRectangleCmdTest.execute();
        verify(model).add(rectangle);
    }

    @Test
    public void testUnexecute() {
        addRectangleCmdTest.unexecute();
        verify(model).remove(rectangle);
    }

    @Test
    public void testToString() {
        when(rectangle.toString()).thenReturn("TestRectangle");
        assertEquals("Rectangle added->TestRectangle", addRectangleCmdTest.toString());
    }
}
