package tests.command_tests;

import command.UpdateRectangleCmd;
import geometry.Point;
import geometry.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

public class UpdateRectangleCmdTest {

    private Rectangle oldRectangle;
    private Rectangle newRectangle;
    private Rectangle originalRectangle;
    private UpdateRectangleCmd updateRectangleCmdTest;

    @BeforeEach
    void setUp() {
        oldRectangle = new Rectangle(new Point(0, 0), 10, 20, Color.RED, Color.GREEN);
        newRectangle = new Rectangle(new Point(5, 5), 15, 25, Color.BLUE, Color.YELLOW);
        originalRectangle = (Rectangle) oldRectangle.clone();
        
        updateRectangleCmdTest = new UpdateRectangleCmd(oldRectangle, newRectangle);
    }

    @Test
    void testExecute() {
        updateRectangleCmdTest.execute();
        
        assertEquals(newRectangle.getUpperLeftPoint().getX(), oldRectangle.getUpperLeftPoint().getX());
        assertEquals(newRectangle.getUpperLeftPoint().getY(), oldRectangle.getUpperLeftPoint().getY());
        assertEquals(newRectangle.getHeight(), oldRectangle.getHeight());
        assertEquals(newRectangle.getWidth(), oldRectangle.getWidth());
        assertEquals(newRectangle.getColor(), oldRectangle.getColor());
        assertEquals(newRectangle.getInnerColor(), oldRectangle.getInnerColor());
    }

    @Test
    void testUnexecute() {
        updateRectangleCmdTest.execute();
        
        updateRectangleCmdTest.unexecute();
        
        assertEquals(originalRectangle.getUpperLeftPoint().getX(), oldRectangle.getUpperLeftPoint().getX());
        assertEquals(originalRectangle.getUpperLeftPoint().getY(), oldRectangle.getUpperLeftPoint().getY());
        assertEquals(originalRectangle.getHeight(), oldRectangle.getHeight());
        assertEquals(originalRectangle.getWidth(), oldRectangle.getWidth());
        assertEquals(originalRectangle.getColor(), oldRectangle.getColor());
        assertEquals(originalRectangle.getInnerColor(), oldRectangle.getInnerColor());
    }

    @Test
    void testToString() {
        String expected = "Rectangle updated->" + originalRectangle.toString() + "->" + newRectangle.toString();
        assertEquals(expected, updateRectangleCmdTest.toString());
    }
}
