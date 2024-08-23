package tests.command_tests;

import adapter.HexagonAdapter;
import command.RemoveHexagonCmd;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class RemoveHexagonCmdTest {

    private DrawingModel model;
    private HexagonAdapter hexagon;
    private RemoveHexagonCmd removeHexagonCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        hexagon = mock(HexagonAdapter.class);
        removeHexagonCmdTest = new RemoveHexagonCmd(hexagon, model);
    }

    @Test
    public void testExecute() {
        removeHexagonCmdTest.execute();
        verify(model).remove(hexagon);
    }

    @Test
    public void testUnexecute() {
        removeHexagonCmdTest.unexecute();
        verify(model).add(hexagon);
    }
}
