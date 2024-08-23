package tests.command_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import adapter.HexagonAdapter;
import command.UpdateHexagonCmd;
import java.awt.Color;

public class UpdateHexagonCmdTest {

    private HexagonAdapter oldState;
    private HexagonAdapter newState;
    private UpdateHexagonCmd updateHexagonCmdTest;

    @BeforeEach
    void setUp() {
    	HexagonAdapter oldHexagon = new HexagonAdapter(10, 20, 30, Color.BLACK, Color.WHITE);
    	HexagonAdapter newHexagon = new HexagonAdapter(15, 25, 35, Color.RED, Color.YELLOW);
        
        oldState = oldHexagon;
        newState = newHexagon;
        
        updateHexagonCmdTest = new UpdateHexagonCmd(oldState, newState);
    }

    @Test
    void testExecute() {
        updateHexagonCmdTest.execute();

        assertEquals(15, oldState.getHexagon().getX());
        assertEquals(25, oldState.getHexagon().getY());
        assertEquals(35, oldState.getHexagon().getR());
        assertEquals(Color.RED, oldState.getHexagon().getBorderColor());
        assertEquals(Color.YELLOW, oldState.getHexagon().getAreaColor());
    }

    @Test
    void testUnexecute() {
        updateHexagonCmdTest.execute(); 
        updateHexagonCmdTest.unexecute(); 

        assertEquals(10, oldState.getHexagon().getX());
        assertEquals(20, oldState.getHexagon().getY());
        assertEquals(30, oldState.getHexagon().getR());
        assertEquals(Color.BLACK, oldState.getHexagon().getBorderColor());
        assertEquals(Color.WHITE, oldState.getHexagon().getAreaColor());
    }
}
