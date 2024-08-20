package tests.adapter_tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import adapter.HexagonAdapter;

public class HexagonAdapterTest {

    private HexagonAdapter hexagon;

    @BeforeEach
    public void setUp() {
        hexagon = new HexagonAdapter(10, 20, 30, Color.BLACK, Color.WHITE);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, hexagon.getHexagon().getX());
        assertEquals(20, hexagon.getHexagon().getY());
        assertEquals(30, hexagon.getHexagon().getR());
        assertEquals(Color.BLACK, hexagon.getHexagon().getBorderColor());
        assertEquals(Color.WHITE, hexagon.getHexagon().getAreaColor());
    }

    @Test
    public void testMoveTo() {
        hexagon.moveTo(15, 25);
        assertEquals(15, hexagon.getHexagon().getX());
        assertEquals(25, hexagon.getHexagon().getY());
    }

    @Test
    public void testMoveBy() {
        hexagon.moveBy(5, 10);
        assertEquals(15, hexagon.getHexagon().getX());
        assertEquals(30, hexagon.getHexagon().getY());
    }

    @Test
    public void testArea() {
        double expectedArea = (3 * Math.sqrt(3) * Math.pow(30, 2)) / 2;
        assertEquals(expectedArea, hexagon.area(), 0.01);
    }

    @Test
    public void testContains() {
        assertTrue(hexagon.contains(10, 20));
        assertFalse(hexagon.contains(100, 200));
    }

    @Test
    public void testEquals() {
        HexagonAdapter anotherHexagon = new HexagonAdapter(10, 20, 30, Color.BLACK, Color.WHITE);
        assertTrue(hexagon.equals(anotherHexagon));
    }

    @Test
    public void testToString() {
        String expectedString = "Hexagon:10,20,30,0,0,0,255,255,255,false";
        assertEquals(expectedString, hexagon.toString());
    }

    @Test
    public void testSetSelected() {
        hexagon.setSelected(true);
        assertTrue(hexagon.isSelected());
    }

    @Test
    public void testClone() {
        HexagonAdapter clonedHexagon = hexagon.clone();
        assertEquals(hexagon, clonedHexagon);
    }
}
