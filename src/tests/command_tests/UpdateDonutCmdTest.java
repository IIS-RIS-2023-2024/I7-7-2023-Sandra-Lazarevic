package tests.command_tests;

import geometry.Donut;
import geometry.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import command.UpdateDonutCmd;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDonutCmdTest {

    private Donut oldDonut;
    private Donut newDonut;
    private Donut clonedDonut;
    private UpdateDonutCmd updateDonutCmdTest;

    @BeforeEach
    void setUp() {
        oldDonut = new Donut(new Point(1, 1), 5, 2, false, Color.RED, Color.BLUE);
        newDonut = new Donut(new Point(2, 2), 10, 4, false, Color.GREEN, Color.YELLOW);

        clonedDonut = (Donut) oldDonut.clone();
        updateDonutCmdTest = new UpdateDonutCmd(oldDonut, newDonut);
    }


    @Test
    void testExecute() {
    	updateDonutCmdTest.execute();
        
        assertEquals(newDonut.getCenter().getX(), oldDonut.getCenter().getX());
        assertEquals(newDonut.getCenter().getY(), oldDonut.getCenter().getY());
        assertEquals(newDonut.getRadius(), oldDonut.getRadius());
        assertEquals(newDonut.getInnerRadius(), oldDonut.getInnerRadius());
        assertEquals(newDonut.getColor(), oldDonut.getColor());
        assertEquals(newDonut.getInnerColor(), oldDonut.getInnerColor());
    }

    @Test
    void testUnexecute() {
    	updateDonutCmdTest.execute();
        updateDonutCmdTest.unexecute();
        
        assertEquals(clonedDonut.getCenter().getX(), oldDonut.getCenter().getX());
        assertEquals(clonedDonut.getCenter().getY(), oldDonut.getCenter().getY());
        assertEquals(clonedDonut.getRadius(), oldDonut.getRadius());
        assertEquals(clonedDonut.getInnerRadius(), oldDonut.getInnerRadius());
        assertEquals(clonedDonut.getColor(), oldDonut.getColor());
        assertEquals(clonedDonut.getInnerColor(), oldDonut.getInnerColor());
    }

    @Test
    void testToString() {
        String expected = "Donut updated->" + clonedDonut.toString() + "->" + newDonut.toString();
        assertEquals(expected, updateDonutCmdTest.toString());
    }
}


