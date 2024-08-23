package tests.command_tests;

import geometry.Circle;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.RemoveCircleCmd;
import static org.mockito.Mockito.*;

public class RemoveCircleCmdTest {

    private DrawingModel model;
    private Circle circle;
    private RemoveCircleCmd removeCircleCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        circle = mock(Circle.class);
        removeCircleCmdTest = new RemoveCircleCmd(circle, model);
    }

    @Test
    public void testExecute() {
        removeCircleCmdTest.execute();
        verify(model).remove(circle);
    }

    @Test
    public void testUnexecute() {
        removeCircleCmdTest.unexecute();
        verify(model).add(circle);
    }
}
