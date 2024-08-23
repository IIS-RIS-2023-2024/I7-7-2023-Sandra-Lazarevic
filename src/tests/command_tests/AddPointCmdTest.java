package tests.command_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import geometry.Point;
import mvc.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.AddPointCmd;

class AddPointCmdTest {

    private AddPointCmd addPointCmdTest;
    private DrawingModel model;
    private Point point;

    @BeforeEach
    void setUp() {
        model = mock(DrawingModel.class);
        point = new Point(10, 20);
        addPointCmdTest = new AddPointCmd(point, model);
    }

    @Test
    void testExecute() {
        addPointCmdTest.execute();
        verify(model).add(point);
    }

    @Test
    void testUnexecute() {
        addPointCmdTest.unexecute();
        verify(model).remove(point);
    }

    @Test
    void testToString() {
        String expected = "Point added->" + point.toString();
        assertEquals(expected, addPointCmdTest.toString());
    }
}
