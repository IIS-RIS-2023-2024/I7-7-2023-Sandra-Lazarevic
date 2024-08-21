package tests.geometry_tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.awt.Color;
import java.awt.Graphics;
import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import geometry.Rectangle;

public class RectangleTest {

    private Point upperLeftPointTest;
    private Rectangle rectangleTest;

    @Before
    public void setUp() {
        upperLeftPointTest = new Point(10, 20);
        rectangleTest = new Rectangle(upperLeftPointTest, 30, 40, true, Color.RED, Color.GREEN);
    }

    @Test
    public void testConstructor() {
        assertEquals(upperLeftPointTest, rectangleTest.getUpperLeftPoint());
        assertEquals(30, rectangleTest.getHeight());
        assertEquals(40, rectangleTest.getWidth());
        assertTrue(rectangleTest.isSelected());
        assertEquals(Color.RED, rectangleTest.getColor());
        assertEquals(Color.GREEN, rectangleTest.getInnerColor());
    }

    @Test
    public void testEquals() {
        Rectangle other = new Rectangle(new Point(10, 20), 30, 40, true, Color.RED, Color.GREEN);
        assertTrue(rectangleTest.equals(other));
        
        Rectangle different = new Rectangle(new Point(0, 0), 10, 20, false, Color.BLUE, Color.YELLOW);
        assertFalse(rectangleTest.equals(different));
    }

    @Test
    public void testArea() {
        assertEquals(1200, rectangleTest.area(), 0.01);
    }

    @Test
    public void testMoveTo() {
        rectangleTest.moveTo(50, 60);
        assertEquals(new Point(50, 60), rectangleTest.getUpperLeftPoint());
    }

    @Test
    public void testMoveBy() {
        rectangleTest.moveBy(5, -10);
        assertEquals(new Point(15, 10), rectangleTest.getUpperLeftPoint());
    }

    @Test
    public void testDraw() {
        Graphics mockDraw = mock(Graphics.class);
        rectangleTest.draw(mockDraw);
    }
    
    @Test
    public void testFill() {
        Graphics mockDraw = mock(Graphics.class);
        rectangleTest.fill(mockDraw);
    }
    
    @Test
    public void testContainsWithCoordinates() {
        assertTrue(rectangleTest.contains(20, 30)); // Inside the rectangle
        assertFalse(rectangleTest.contains(5, 10));  // Outside the rectangle
    }

    @Test
    public void testContainsWithPoint() {
        Point insidePoint = new Point(20, 30);
        Point outsidePoint = new Point(5, 10);
        
        assertTrue(rectangleTest.contains(insidePoint));
        assertFalse(rectangleTest.contains(outsidePoint));
    }

    @Test
    public void testToString() {
        assertEquals("Upper left point=(10, 20), width=40, height=30", rectangleTest.toString());
    }

    @Test
    public void testClone() {
        Rectangle clonedRectangle = rectangleTest.clone();
        assertNotSame(rectangleTest, clonedRectangle);
        assertEquals(rectangleTest, clonedRectangle);
    }
}
