package tests.command_tests;

import geometry.Rectangle;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.RemoveRectangleCmd;
import static org.mockito.Mockito.*;

public class RemoveRectangleCmdTest {

    private DrawingModel model;
    private Rectangle rectangle;
    private RemoveRectangleCmd removeRectangleCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        rectangle = mock(Rectangle.class);
        removeRectangleCmdTest = new RemoveRectangleCmd(rectangle, model);
    }

    @Test
    public void testExecute() {
        removeRectangleCmdTest.execute();
        verify(model).remove(rectangle);
    }

    @Test
    public void testUnexecute() {
        removeRectangleCmdTest.unexecute();
        verify(model).add(rectangle);
    }
}
