package tests.mvc_tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.DlgHexagon;

public class DlgHexagonTest {

    private DlgHexagon dlgHexagonTest;
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtR;
    private JButton btnBorderColor;
    private JButton btnInnerColor;

    @BeforeEach
    public void setUp() {
        dlgHexagonTest = new DlgHexagon();
        txtX = dlgHexagonTest.getTxtX();
        txtY = dlgHexagonTest.getTxtY();
        txtR = dlgHexagonTest.getTxtR();
        btnBorderColor = dlgHexagonTest.getBtnBorderColor();
        btnInnerColor = dlgHexagonTest.getBtnInnerColor();
    }

    @Test
    public void testValidInput() {
        txtX.setText("10");
        txtY.setText("20");
        txtR.setText("30");
        btnBorderColor.setBackground(Color.RED);
        btnInnerColor.setBackground(Color.BLUE);
        dlgHexagonTest.setConfirm(true);
        assertTrue(dlgHexagonTest.isConfirm());
        assertEquals(10, Integer.parseInt(txtX.getText()));
        assertEquals(20, Integer.parseInt(txtY.getText()));
        assertEquals(30, Integer.parseInt(txtR.getText()));
        assertEquals(Color.RED, dlgHexagonTest.getBorderColor());
        assertEquals(Color.BLUE, dlgHexagonTest.getInnerColor());
    }

    @Test
    public void testEmptyInput() {
        txtX.setText("");
        txtY.setText("");
        txtR.setText("");
        dlgHexagonTest.setConfirm(false);
        assertFalse(dlgHexagonTest.isConfirm());
    }

    @Test
    public void testNegativeInput() {
        txtX.setText("-10");
        txtY.setText("-20");
        txtR.setText("-30");
        dlgHexagonTest.setConfirm(false);
        assertFalse(dlgHexagonTest.isConfirm());
    }

    @Test
    public void testNonNumericInput() {
        txtX.setText("abc");
        txtY.setText("def");
        txtR.setText("ghi");
        dlgHexagonTest.setConfirm(false);
        assertFalse(dlgHexagonTest.isConfirm());
    }

    @Test
    public void testColorSelection() {
        btnBorderColor.setBackground(Color.GREEN);
        btnInnerColor.setBackground(Color.YELLOW);
        assertEquals(Color.GREEN, dlgHexagonTest.getBorderColor());
        assertEquals(Color.YELLOW, dlgHexagonTest.getInnerColor());
    }
}
