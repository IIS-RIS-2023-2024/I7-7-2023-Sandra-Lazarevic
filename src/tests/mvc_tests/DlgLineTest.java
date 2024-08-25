package tests.mvc_tests;

import geometry.Line;
import geometry.Point;
import mvc.DlgLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class DlgLineTest {

    private DlgLine dlgLineTest;

    @BeforeEach
    public void setUp() {
        dlgLineTest = new DlgLine();
    }

    @Test
    public void testInitialValues() {
        assertFalse(dlgLineTest.isConfirm());
        assertNull(dlgLineTest.getLine());
    }

    @Test
    public void testValidInput() {
        dlgLineTest.getTxtSPX().setText("10");
        dlgLineTest.getTxtSPY().setText("20");
        dlgLineTest.getTxtEPX().setText("30");
        dlgLineTest.getTxtEPY().setText("40");
        dlgLineTest.getBtnOutlineColor().setBackground(Color.RED);

        dlgLineTest.onOk();

        assertTrue(dlgLineTest.isConfirm());
        Line line = dlgLineTest.getLine();
        assertNotNull(line);
        assertEquals(new Point(10, 20), line.getStartPoint());
        assertEquals(new Point(30, 40), line.getEndPoint());
        assertEquals(Color.RED, line.getColor());
    }

    @Test
    public void testInvalidInputNegativeValues() {
        dlgLineTest.getTxtSPX().setText("-10");
        dlgLineTest.getTxtSPY().setText("20");
        dlgLineTest.getTxtEPX().setText("30");
        dlgLineTest.getTxtEPY().setText("-40");

        dlgLineTest.onOk();

        assertFalse(dlgLineTest.isConfirm());
        assertNull(dlgLineTest.getLine());
    }

    @Test
    public void testInvalidInputEmptyFields() {
        dlgLineTest.getTxtSPX().setText("");
        dlgLineTest.getTxtSPY().setText("20");
        dlgLineTest.getTxtEPX().setText("30");
        dlgLineTest.getTxtEPY().setText("");

        dlgLineTest.onOk();

        assertFalse(dlgLineTest.isConfirm());
        assertNull(dlgLineTest.getLine());
    }

    @Test
    public void testOutlineColorChange() {
        dlgLineTest.getBtnOutlineColor().setBackground(Color.BLUE);
        assertEquals(Color.BLUE, dlgLineTest.getBtnOutlineColor().getBackground());
    }
}
