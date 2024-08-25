package tests.mvc_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.swing.*;
import java.awt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mvc.DlgPoint;

public class DlgPointTest {

    private DlgPoint dlgPointTest;
    private JTextField txtX;
    private JTextField txtY;
    private JButton btnColor;

    @BeforeEach
    public void setUp() {
        dlgPointTest = new DlgPoint();
        txtX = dlgPointTest.getTxtX();
        txtY = dlgPointTest.getTxtY();
        btnColor = dlgPointTest.getBtnColor();
    }

    @Test
    public void testValidInput() {
        txtX.setText("10");
        txtY.setText("20");
        dlgPointTest.getBtnColor().setBackground(Color.RED);
        dlgPointTest.onOk();
        assertTrue(dlgPointTest.isConfirm());
        assertEquals(10, dlgPointTest.getP().getX());
        assertEquals(20, dlgPointTest.getP().getY());
        assertEquals(Color.RED, dlgPointTest.getC(), "Color should be RED");
    }

    @Test
    public void testEmptyInput() {
        txtX.setText("");
        txtY.setText("");
        dlgPointTest.onOk();
        assertFalse(dlgPointTest.isConfirm());
    }

    @Test
    public void testNegativeInput() {
        txtX.setText("-10");
        txtY.setText("-20");
        dlgPointTest.onOk();
        assertFalse(dlgPointTest.isConfirm());
    }

    @Test
    public void testNonNumericInput() {
        txtX.setText("abc");
        txtY.setText("def");
        dlgPointTest.onOk();
        assertFalse(dlgPointTest.isConfirm());
    }

    @Test
    public void testColorSelection() {
        btnColor.setBackground(Color.BLUE);
        assertEquals(Color.BLUE, dlgPointTest.getBtnColor().getBackground());
    }
}
