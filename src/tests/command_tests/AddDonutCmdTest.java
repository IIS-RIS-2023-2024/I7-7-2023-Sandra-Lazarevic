package tests.command_tests;

import geometry.Donut;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.AddDonutCmd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddDonutCmdTest {

    private DrawingModel model;
    private Donut donut;
    private AddDonutCmd addDonutCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        donut = mock(Donut.class);
        addDonutCmdTest = new AddDonutCmd(donut, model);
    }

    @Test
    public void testExecute() {
        addDonutCmdTest.execute();
        verify(model).add(donut);
    }

    @Test
    public void testUnexecute() {
        addDonutCmdTest.unexecute();
        verify(model).remove(donut);
    }

    @Test
    public void testToString() {
        when(donut.toString()).thenReturn("TestDonut");
        assertEquals("Donut added->TestDonut", addDonutCmdTest.toString());
    }
}
