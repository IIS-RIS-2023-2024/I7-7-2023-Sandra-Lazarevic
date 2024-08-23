package tests.command_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import geometry.Line;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import command.AddLineCmd;

class AddLineCmdTest {

    private AddLineCmd addLineCmdTest;
    private Line mockLine;
    private DrawingModel mockModel;

    @BeforeEach
    void setUp() {
        mockLine = mock(Line.class);
        mockModel = mock(DrawingModel.class);
        addLineCmdTest = new AddLineCmd(mockLine, mockModel);
    }

    @Test
    void testExecute() {
        addLineCmdTest.execute();
        verify(mockModel).add(mockLine);
    }

    @Test
    void testUnexecute() {
        addLineCmdTest.unexecute();
        verify(mockModel).remove(mockLine);
    }

    @Test
    void testToString() {
        when(mockLine.toString()).thenReturn("(0,0) to (1,1)");
        String result = addLineCmdTest.toString();
        assertEquals("Line added->(0,0) to (1,1)", result);
    }
}
