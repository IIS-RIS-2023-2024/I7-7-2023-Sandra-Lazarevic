package tests.geometry_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import geometry.Circle;
import geometry.Point;
import java.awt.Color;
import java.awt.Graphics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CircleTest {

    private Circle circleTest;
    private Point centerTest;

    @BeforeEach
    void setUp() {
    	centerTest = new Point(10, 10);
        circleTest = new Circle(centerTest, 5, true, Color.RED, Color.BLUE);
    }

    @Test
    void testArea() {
        double expectedArea = Math.PI * 5 * 5;
        assertEquals(expectedArea, circleTest.area(), 0.001);
    }

    @Test
    void testFill() {
        Graphics mockFill = Mockito.mock(Graphics.class);
        circleTest.fill(mockFill);
        verify(mockFill).setColor(Color.BLUE);
        verify(mockFill).fillOval(10 - 5, 10 - 5, 5 * 2, 5 * 2);
    }

    @Test
    public void testDraw() {
        Graphics mockDraw = mock(Graphics.class);
        Circle circle = new Circle(new Point(10, 10), 5, true, Color.RED, Color.GREEN);
        circle.draw(mockDraw);

        InOrder inOrder = inOrder(mockDraw);
        inOrder.verify(mockDraw).setColor(Color.RED);
        inOrder.verify(mockDraw).drawOval(5, 5, 10, 10);
        inOrder.verify(mockDraw).setColor(Color.GREEN);
        inOrder.verify(mockDraw).fillOval(5, 5, 10, 10);
    }

    @Test
    void testEquals() {
        Circle sameCircles = new Circle(new Point(10, 10), 5, true, Color.RED, Color.BLUE);
        assertTrue(circleTest.equals(sameCircles));

        Circle differentCircles = new Circle(new Point(15, 15), 10, false, Color.GREEN, Color.YELLOW);
        assertFalse(circleTest.equals(differentCircles));
    }

    @Test
    void testContainsWithCoordinates() {
        assertTrue(circleTest.contains(10, 10));
        assertFalse(circleTest.contains(20, 20));
    }

    @Test
    void testContainsWithPoint() {
        Point pointInside = new Point(12, 12);
        Point pointOutside = new Point(20, 20);
        assertTrue(circleTest.contains(pointInside));
        assertFalse(circleTest.contains(pointOutside));
    }

    @Test
    void testSetRadius() throws Exception {
    	circleTest.setRadius(10);
        assertEquals(10, circleTest.getRadius());
    }

    @Test
    void testSetRadiusThrowsException() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
        	circleTest.setRadius(-1);
        });
        assertEquals("Radius has to be a value greater then 0!", exception.getMessage());
    }

    @Test
    void testClone() {
        Circle clonedCircle = circleTest.clone();
        assertNotSame(circleTest, clonedCircle);
        assertEquals(circleTest.getCenter().getX(), clonedCircle.getCenter().getX());
        assertEquals(circleTest.getCenter().getY(), clonedCircle.getCenter().getY());
        assertEquals(circleTest.getRadius(), clonedCircle.getRadius());
        assertEquals(circleTest.getColor(), clonedCircle.getColor());
        assertEquals(circleTest.getInnerColor(), clonedCircle.getInnerColor());
    }
    
    @Test
    void testSetCenter() {
        Point newCenter = new Point(20, 20);
        circleTest.setCenter(newCenter);
        assertEquals(newCenter, circleTest.getCenter());
        
        circleTest.setCenter(null);  //testiranje sa null vrednošću
        assertNotNull(circleTest.getCenter());  //trebalo bi da bude default Point
    }
}
