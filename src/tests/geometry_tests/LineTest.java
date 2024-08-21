package tests.geometry_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Line;
import geometry.Point;

public class LineTest {

    private Line lineTest;
    private Point startPointTest;
    private Point endPointTest;
    
    @BeforeEach
    public void setUp() {
    	startPointTest = new Point(1, 1);
    	endPointTest = new Point(4, 5);
        lineTest = new Line(startPointTest, endPointTest, Color.RED);
    }
    
    @Test
    public void testLength() {
        double expectedLength = startPointTest.distance(endPointTest.getX(), endPointTest.getY());
        assertEquals(expectedLength, lineTest.length(), "Length should be correctly calculated.");
    }

    @Test
    public void testMoveBy() {
        int byX = 2;
        int byY = 3;
        lineTest.moveBy(byX, byY);

        assertEquals(3, lineTest.getStartPoint().getX());
        assertEquals(4, lineTest.getStartPoint().getY());
        assertEquals(6, lineTest.getEndPoint().getX());
        assertEquals(8, lineTest.getEndPoint().getY());
    }

    @Test
    public void testContains() {
        assertTrue(lineTest.contains(2, 2), "Line should contain point (2, 2)");
        assertFalse(lineTest.contains(0, 0), "Line should not contain point (0, 0)");
    }

    @Test
    public void testClone() {
        Line clonedLine = lineTest.clone();
        assertNotSame(lineTest, clonedLine, "Cloned line should not be the same object as the original line.");
        assertEquals(lineTest, clonedLine, "Cloned line should be equal to the original line.");
        assertEquals(lineTest.getColor(), clonedLine.getColor(), "Cloned line should have the same color as the original line.");
    }
    
    @Test
    public void testMiddleOfLine() {
        Point expectedMiddle = new Point((startPointTest.getX() + endPointTest.getX()) / 2,
                                         (startPointTest.getY() + endPointTest.getY()) / 2);
        Point actualMiddle = lineTest.getMiddlePointOfLine();
        assertEquals(expectedMiddle, actualMiddle);
    }

    @Test
    public void testDraw() {
        Graphics mockDraw = mock(Graphics.class);

        lineTest.draw(mockDraw);
        verify(mockDraw).setColor(Color.RED);
        verify(mockDraw).drawLine(1, 1, 4, 5);

        lineTest.setSelected(true);
        lineTest.draw(mockDraw);

        verify(mockDraw).setColor(Color.BLUE);

        verify(mockDraw, times(1)).drawRect(startPointTest.getX() - 3, startPointTest.getY() - 3, 6, 6);
        verify(mockDraw, times(1)).drawRect(endPointTest.getX() - 3, endPointTest.getY() - 3, 6, 6);
        verify(mockDraw, times(1)).drawRect((startPointTest.getX() + endPointTest.getX()) / 2 - 3,
                                            (startPointTest.getY() + endPointTest.getY()) / 2 - 3, 6, 6); //sredina linije
    }

    @Test
    public void testEquals() {
        Line sameLine = new Line(new Point(1, 1), new Point(4, 5), Color.RED);
        Line differentLine = new Line(new Point(2, 2), new Point(5, 6), Color.BLUE);

        assertTrue(lineTest.equals(sameLine), "Lines with the same start and end points should be equal.");
        assertFalse(lineTest.equals(differentLine), "Lines with different start or end points should not be equal.");
    }
    
    @Test
    public void testToString() {
        assertEquals("(1, 1)-->(4, 5)", lineTest.toString());
    }
}