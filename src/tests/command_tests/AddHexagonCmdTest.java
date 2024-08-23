package tests.command_tests;

import adapter.HexagonAdapter;
import command.AddHexagonCmd;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddHexagonCmdTest {

    private DrawingModel model;
    private HexagonAdapter hexagon;
    private AddHexagonCmd addHexagonCmdTest;

    @BeforeEach
    public void setUp() {
        model = mock(DrawingModel.class);
        hexagon = mock(HexagonAdapter.class);
        addHexagonCmdTest = new AddHexagonCmd(hexagon, model);
    }

    @Test
    public void testExecute() {
        addHexagonCmdTest.execute();
        verify(model).add(hexagon);
    }

    @Test
    public void testUnexecute() {
        addHexagonCmdTest.unexecute();
        verify(model).remove(hexagon);
    }

    @Test
    public void testToString() {
        when(hexagon.toString()).thenReturn("TestHexagon");
        assertEquals("Hexagon added->TestHexagon", addHexagonCmdTest.toString());
    }
}
