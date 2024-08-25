package tests.mvc_tests;

import geometry.Circle;
import geometry.Point;
import mvc.DlgCircle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class DlgCircleTest {

    private DlgCircle dlgCircleTest;

    @BeforeEach
    public void setUp() {
        dlgCircleTest = new DlgCircle();
    }

    @Test
    public void testInitialValues() {
        assertFalse(dlgCircleTest.isConfirm());
        assertNull(dlgCircleTest.getCircle());
    }

    @Test
    public void testValidInput() {
        dlgCircleTest.getTxtX().setText("10");
        dlgCircleTest.getTxtY().setText("20");
        dlgCircleTest.getTxtR().setText("30");
        dlgCircleTest.getBtnOutlineColor().setBackground(Color.RED);

        dlgCircleTest.onOk();

        assertTrue(dlgCircleTest.isConfirm());
        Circle circle = dlgCircleTest.getCircle();
        assertNotNull(circle);
        assertEquals(new Point(10, 20), circle.getCenter());
        assertEquals(30, circle.getRadius());
        assertEquals(Color.RED, circle.getColor());
    }

    @Test
    public void testInvalidInputNegativeValues() {
        dlgCircleTest.getTxtX().setText("-10");
        dlgCircleTest.getTxtY().setText("20");
        dlgCircleTest.getTxtR().setText("-30");

        dlgCircleTest.onOk();

        assertFalse(dlgCircleTest.isConfirm());
        assertNull(dlgCircleTest.getCircle());
    }

    @Test
    public void testInvalidInputEmptyFields() {
        dlgCircleTest.getTxtX().setText("");
        dlgCircleTest.getTxtY().setText("20");
        dlgCircleTest.getTxtR().setText("");

        dlgCircleTest.onOk();

        assertFalse(dlgCircleTest.isConfirm());
        assertNull(dlgCircleTest.getCircle());
    }

    @Test
    public void testOutlineColorChange() {
        dlgCircleTest.getBtnOutlineColor().setBackground(Color.BLUE);
        assertEquals(Color.BLUE, dlgCircleTest.getBtnOutlineColor().getBackground());
    }
}
