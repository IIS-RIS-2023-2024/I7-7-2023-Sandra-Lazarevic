package tests.geometry_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.Point;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private Point pointTest;

    @BeforeEach
    void setUp() {
        pointTest = new Point(5, 5, true, Color.RED);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(5, pointTest.getX());
        assertEquals(5, pointTest.getY());
        assertTrue(pointTest.isSelected());
        assertEquals(Color.RED, pointTest.getColor());
    }

    @Test
    void testSetters() {
        pointTest.setX(10);
        pointTest.setY(10);
        assertEquals(10, pointTest.getX());
        assertEquals(10, pointTest.getY());
    }

    @Test
    void testDistance() {
        Point otherPoint = new Point(10, 10);
        double distance = pointTest.distance(otherPoint.getX(), otherPoint.getY());
        assertEquals(Math.sqrt(50), distance, 0.001);
    }

    @Test
    void testEquals() {
        Point samePoints = new Point(5, 5, true, Color.RED);
        Point differentPoints = new Point(10, 10);
        assertTrue(pointTest.equals(samePoints));
        assertFalse(pointTest.equals(differentPoints));
    }

    @Test
    void testContains() {
        assertTrue(pointTest.contains(5, 5));
        assertFalse(pointTest.contains(10, 10));
    }

    @Test
    void testMoveTo() {
        pointTest.moveTo(10, 10);
        assertEquals(10, pointTest.getX());
        assertEquals(10, pointTest.getY());
    }

    @Test
    void testMoveBy() {
        pointTest.moveBy(5, 5);
        assertEquals(10, pointTest.getX());
        assertEquals(10, pointTest.getY());
    }

    @Test
    void testToString() {
        assertEquals("(5, 5)", pointTest.toString());
    }

    @Test
    void testClone() {
        Point clonedPoint = pointTest.clone();
        assertEquals(pointTest, clonedPoint);
        assertNotSame(pointTest, clonedPoint);
    }
}

