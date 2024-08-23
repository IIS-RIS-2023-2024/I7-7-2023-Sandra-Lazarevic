package tests.command_tests;

import geometry.Donut;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.RemoveDonutCmd;
import static org.mockito.Mockito.*;

public class RemoveDonutCmdTest {

    private DrawingModel model;
    private Donut donut;
    private RemoveDonutCmd removeDonutCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        donut = mock(Donut.class);
        removeDonutCmdTest = new RemoveDonutCmd(donut, model);
    }

    @Test
    public void testExecute() {
        removeDonutCmdTest.execute();
        verify(model).remove(donut);
    }

    @Test
    public void testUnexecute() {
        removeDonutCmdTest.unexecute();
        verify(model).add(donut);
    }
}
