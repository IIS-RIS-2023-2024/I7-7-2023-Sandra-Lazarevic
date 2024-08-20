package tests.adapter_tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import adapter.HexagonAdapter;

public class HexagonAdapterTest {

    private HexagonAdapter hexagonTest;

    @BeforeEach
    public void setUp() {
    	hexagonTest = new HexagonAdapter(10, 20, 30, Color.BLACK, Color.WHITE);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, hexagonTest.getHexagon().getX());
        assertEquals(20, hexagonTest.getHexagon().getY());
        assertEquals(30, hexagonTest.getHexagon().getR());
        assertEquals(Color.BLACK, hexagonTest.getHexagon().getBorderColor());
        assertEquals(Color.WHITE, hexagonTest.getHexagon().getAreaColor());
    }

    @Test
    public void testMoveTo() {
    	hexagonTest.moveTo(15, 25);
        assertEquals(15, hexagonTest.getHexagon().getX());
        assertEquals(25, hexagonTest.getHexagon().getY());
    }

    @Test
    public void testMoveBy() {
    	hexagonTest.moveBy(5, 10);
        assertEquals(15, hexagonTest.getHexagon().getX());
        assertEquals(30, hexagonTest.getHexagon().getY());
    }

    @Test
    public void testArea() {
        double expectedArea = (3 * Math.sqrt(3) * Math.pow(30, 2)) / 2;
        assertEquals(expectedArea, hexagonTest.area(), 0.01);
    }

    @Test
    public void testContains() {
        assertTrue(hexagonTest.contains(10, 20));
        assertFalse(hexagonTest.contains(100, 200));
    }

    @Test
    public void testEquals() {
        HexagonAdapter anotherHexagon = new HexagonAdapter(10, 20, 30, Color.BLACK, Color.WHITE);
        assertTrue(hexagonTest.equals(anotherHexagon));
        
        HexagonAdapter anotherHexagon3 = new HexagonAdapter(20, 20, 30, Color.BLACK, Color.WHITE);
        assertFalse(hexagonTest.equals(anotherHexagon3));
    }

    @Test
    public void testToString() {
        String expectedString = "Hexagon:10,20,30,0,0,0,255,255,255,false";
        assertEquals(expectedString, hexagonTest.toString());
    }

    @Test
    public void testSetSelected() {
    	hexagonTest.setSelected(true);
        assertTrue(hexagonTest.isSelected());
    }

    @Test
    public void testClone() {
        HexagonAdapter clonedHexagon = hexagonTest.clone();
        assertEquals(hexagonTest, clonedHexagon);
        assertNotSame(hexagonTest, clonedHexagon);
    }
}
