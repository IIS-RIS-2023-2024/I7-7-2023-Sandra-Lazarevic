package tests.command_tests;

import geometry.Circle;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.AddCircleCmd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddCircleCmdTest {

    private DrawingModel model;
    private Circle circle;
    private AddCircleCmd addCircleCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        circle = mock(Circle.class);
        addCircleCmdTest = new AddCircleCmd(circle, model);
    }

    @Test
    public void testExecute() {
        addCircleCmdTest.execute();
        verify(model).add(circle);
    }

    @Test
    public void testUnexecute() {
        addCircleCmdTest.unexecute();
        verify(model).remove(circle);
    }
    
    @Test
    public void testToString() {
        when(circle.toString()).thenReturn("TestCircle");
        assertEquals("Circle added->TestCircle", addCircleCmdTest.toString());
    }
}
