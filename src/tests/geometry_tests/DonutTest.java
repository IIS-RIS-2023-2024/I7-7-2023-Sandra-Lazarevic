package tests.geometry_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.Donut;
import geometry.Point;
import java.awt.Color;
import java.awt.Graphics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonutTest {

    private Donut donutTest;
    private Point centerTest;

    @BeforeEach
    void setUp() {
        centerTest = new Point(10, 10);
        donutTest = new Donut(centerTest, 10, 5, true, Color.RED, Color.BLUE);
    }

    @Test
    void testArea() {
        double expectedArea = (Math.PI * 10 * 10) - (Math.PI * 5 * 5);
        assertEquals(expectedArea, donutTest.area(), 0.001);
    }

    @Test
    void testDraw() {
        Graphics mockDraw = mock(Graphics.class);
        
        Donut donut = new Donut(new Point(10, 10), 10, 5);
        donut.draw(mockDraw);

        //verifikacija da se draw metod ponaša kako je očekivano
        verify(mockDraw).setColor(Color.RED);
        verify(mockDraw).fillOval(0, 0, 20, 20);
        
        //ako draw metod poziva fill
        verify(mockDraw).setColor(Color.WHITE);
        verify(mockDraw).fillOval(5, 5, 10, 10);
    }

    @Test
    void testFill() {
        Graphics mockFill = mock(Graphics.class);
        
        when(mockFill.getColor()).thenReturn(Color.RED);
        
        Donut donut = new Donut(new Point(10, 10), 10, 5);
        donut.fill(mockFill);
        
        verify(mockFill).setColor(Color.RED);
        verify(mockFill).fillOval(0, 0, 20, 20);
        
        verify(mockFill).setColor(Color.WHITE);
        verify(mockFill).fillOval(5, 5, 10, 10);
    }

    @Test
    void testEquals() {
        Donut donut1 = new Donut(new Point(10, 10), 10, 5, true, Color.RED, Color.WHITE);
        Donut donut2 = new Donut(new Point(10, 10), 10, 5, true, Color.RED, Color.WHITE);
        Donut donut3 = new Donut(new Point(20, 20), 10, 5, true, Color.RED, Color.WHITE);
        
        assertTrue(donut1.equals(donut2));
        assertFalse(donut1.equals(donut3));
        assertFalse(donut1.equals(null));
        assertFalse(donut1.equals(new Object()));
    }

    @Test
    void testCompareTo() {
        Donut donut1 = new Donut(new Point(10, 10), 10, 5, true, Color.RED, Color.WHITE);
        Donut donut2 = new Donut(new Point(10, 10), 10, 5, true, Color.RED, Color.WHITE);
        Donut donut3 = new Donut(new Point(10, 10), 15, 5, true, Color.RED, Color.WHITE);
        
        assertEquals(0, donut1.compareTo(donut2));
        assertTrue(donut1.compareTo(donut3) < 0);
        assertTrue(donut3.compareTo(donut1) > 0);
    }

    @Test
    void testContainsWithCoordinates() {
        Donut donut = new Donut(new Point(10, 10), 10, 5);
        
        //koordinate unutar donuta
        int xInside = 15;
        int yInside = 10;
        
        //koordinate van donuta
        int xOutside = 30;
        int yOutside = 10;

        assertTrue(donut.contains(xInside, yInside), "Point should be inside the donut");
        assertFalse(donut.contains(xOutside, yOutside), "Point should be outside the donut");
    }

    @Test
    void testContainsWithPoint() {
        Donut donut = new Donut(new Point(10, 10), 10, 5);
        Point pointInside = new Point(15, 10); //na ivici spoljnog kruga
        Point pointOutside = new Point(30, 10); //van donuta

        assertTrue(donut.contains(pointInside), "Point should be inside the donut");
        assertFalse(donut.contains(pointOutside), "Point should be outside the donut");
    }

    @Test
    void testSetInnerRadius() {
        donutTest.setInnerRadius(8);
        assertEquals(8, donutTest.getInnerRadius());
    }

    @Test
    void testToString() {
        String expectedString = "Center=(10, 10), radius=10, inner radius=5";
        assertEquals(expectedString, donutTest.toString());
    }
    
}
