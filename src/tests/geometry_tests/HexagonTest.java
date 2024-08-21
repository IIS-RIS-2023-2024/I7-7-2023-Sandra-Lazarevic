package tests.geometry_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geometry.Hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HexagonTest {

    private Hexagon hexagonTest;

    @BeforeEach
    public void setUp() {
        hexagonTest = new Hexagon(50, 50, 30, Color.RED, Color.GREEN);
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(hexagonTest);
        assertEquals(50, hexagonTest.getX());
        assertEquals(50, hexagonTest.getY());
        assertEquals(30, hexagonTest.getR());
        assertEquals(Color.RED, hexagonTest.getBorderColor());
        assertEquals(Color.GREEN, hexagonTest.getAreaColor());
        assertFalse(hexagonTest.isSelected());
    }

    @Test
    public void testConstructorWithSelection() {
        Hexagon selectedHexagon = new Hexagon(50, 50, 30, true, Color.RED, Color.GREEN);
        assertTrue(selectedHexagon.isSelected());
    }

    @Test
    public void testSetters() {
        hexagonTest.setX(100);
        hexagonTest.setY(100);
        hexagonTest.setR(50);
        hexagonTest.setBorderColor(Color.BLUE);
        hexagonTest.setAreaColor(Color.YELLOW);
        hexagonTest.setSelected(true);

        assertEquals(100, hexagonTest.getX());
        assertEquals(100, hexagonTest.getY());
        assertEquals(50, hexagonTest.getR());
        assertEquals(Color.BLUE, hexagonTest.getBorderColor());
        assertEquals(Color.YELLOW, hexagonTest.getAreaColor());
        assertTrue(hexagonTest.isSelected());
    }

    @Test
    public void testPaint() {
        Graphics mockPaint = mock(Graphics.class);
        hexagonTest.paint(mockPaint);
    }

    @Test
    public void testDoesContain() {
        assertTrue(hexagonTest.doesContain(50, 50)); //centar
        assertFalse(hexagonTest.doesContain(0, 0)); //van
    }

}
