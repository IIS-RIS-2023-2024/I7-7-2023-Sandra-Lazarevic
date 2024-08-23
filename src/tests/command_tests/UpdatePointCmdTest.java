package tests.command_tests;

import geometry.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.UpdatePointCmd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.Color;

public class UpdatePointCmdTest {

    private Point oldState;
    private Point newState;
    private UpdatePointCmd updatePointCmdTest;
    private Point original;

    @BeforeEach
    public void setUp() {
        oldState = mock(Point.class);
        newState = mock(Point.class);
        original = mock(Point.class);
        when(oldState.clone()).thenReturn(original);   
        updatePointCmdTest = new UpdatePointCmd(oldState, newState);
    }

    @Test
    public void testExecute() {
        when(newState.getX()).thenReturn(10);
        when(newState.getY()).thenReturn(20);
        when(newState.getColor()).thenReturn(Color.RED);
        updatePointCmdTest.execute();
        
        verify(oldState).setX(10);
        verify(oldState).setY(20);
        verify(oldState).setColor(Color.RED);
    }

    @Test
    public void testUnexecute() {
        when(original.getX()).thenReturn(5);
        when(original.getY()).thenReturn(15);
        when(original.getColor()).thenReturn(Color.BLUE);
        updatePointCmdTest.execute();
        
        when(oldState.getX()).thenReturn(10);
        when(oldState.getY()).thenReturn(20);
        when(oldState.getColor()).thenReturn(Color.RED);
        updatePointCmdTest.unexecute();

        verify(oldState).setX(5);
        verify(oldState).setY(15);
        verify(oldState).setColor(Color.BLUE);
    }

    @Test
    public void testToString() {
        when(original.toString()).thenReturn("OldPoint");
        when(newState.toString()).thenReturn("NewPoint");
        assertEquals("Point updated->OldPoint->NewPoint", updatePointCmdTest.toString());
    }
}