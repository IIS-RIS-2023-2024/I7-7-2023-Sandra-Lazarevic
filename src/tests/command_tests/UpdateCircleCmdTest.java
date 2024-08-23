package tests.command_tests;

import command.UpdateCircleCmd;
import geometry.Circle;
import geometry.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class UpdateCircleCmdTest {

    private Circle oldCircle;
    private Circle newCircle;
    private Circle originalCircle;
    private UpdateCircleCmd updateCircleCmdTest;

    @BeforeEach
    void setUp() {
        oldCircle = new Circle(new Point(0, 0), 10, Color.RED, Color.GREEN);
        newCircle = new Circle(new Point(5, 5), 20, Color.BLUE, Color.YELLOW);
        originalCircle = (Circle) oldCircle.clone();
        updateCircleCmdTest = new UpdateCircleCmd(oldCircle, newCircle);
    }

    @Test
    void testExecute() {
        updateCircleCmdTest.execute();
        
        assertEquals(newCircle.getCenter().getX(), oldCircle.getCenter().getX());
        assertEquals(newCircle.getCenter().getY(), oldCircle.getCenter().getY());
        assertEquals(newCircle.getRadius(), oldCircle.getRadius());
        assertEquals(newCircle.getColor(), oldCircle.getColor());
        assertEquals(newCircle.getInnerColor(), oldCircle.getInnerColor());
    }

    @Test
    void testUnexecute() {
        updateCircleCmdTest.execute();
        updateCircleCmdTest.unexecute();
        
        assertEquals(originalCircle.getCenter().getX(), oldCircle.getCenter().getX());
        assertEquals(originalCircle.getCenter().getY(), oldCircle.getCenter().getY());
        assertEquals(originalCircle.getRadius(), oldCircle.getRadius());
        assertEquals(originalCircle.getColor(), oldCircle.getColor());
        assertEquals(originalCircle.getInnerColor(), oldCircle.getInnerColor());
    }

    @Test
    void testToString() {
        String expected = "Circle updated->" + originalCircle.toString() + "->" + newCircle.toString();
        assertEquals(expected, updateCircleCmdTest.toString());
    }
}
