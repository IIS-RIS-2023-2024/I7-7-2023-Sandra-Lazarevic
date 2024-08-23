package tests.command_tests;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import command.RemovePointCmd;
import geometry.Point;
import mvc.DrawingModel;

class RemovePointCmdTest {

    private RemovePointCmd removePointCmdTest;
    private DrawingModel model;
    private Point point;

    @BeforeEach
    void setUp() {
        model = mock(DrawingModel.class);
        point = new Point(10, 20);
        removePointCmdTest = new RemovePointCmd(point, model);
    }

    @Test
    void testExecute() {
        removePointCmdTest.execute();
        verify(model).remove(point);
    }

    @Test
    void testUnexecute() {
        removePointCmdTest.unexecute();
        verify(model).add(point);
    }
}
