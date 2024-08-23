package tests.command_tests;

import geometry.Line;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.RemoveLineCmd;
import static org.mockito.Mockito.*;

public class RemoveLineCmdTest {

    private DrawingModel model;
    private Line line;
    private RemoveLineCmd removeLineCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        line = mock(Line.class);
        removeLineCmdTest = new RemoveLineCmd(line, model);
    }

    @Test
    public void testExecute() {
        removeLineCmdTest.execute();
        verify(model).remove(line);
    }

    @Test
    public void testUnexecute() {
        removeLineCmdTest.unexecute();
        verify(model).add(line);
    }
}
