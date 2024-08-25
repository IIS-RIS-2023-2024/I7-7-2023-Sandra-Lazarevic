package tests.mvc_tests;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.DlgRectangle;

public class DlgRectangleTest {

    private DlgRectangle dlgRectangleTest;
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtHeight;
    private JTextField txtWidth;
    private JButton btnInnerColor;
    private JButton btnOutlineColor;
    private JButton okButton;
    private JButton cancelButton;

    @BeforeEach
    public void setUp() {
        dlgRectangleTest = new DlgRectangle();
        txtX = dlgRectangleTest.getTxtX();
        txtY = dlgRectangleTest.getTxtY();
        txtHeight = dlgRectangleTest.getTxtHeight();
        txtWidth = dlgRectangleTest.getTxtWidth();
        btnInnerColor = dlgRectangleTest.getBtnInnerColor();
        btnOutlineColor = dlgRectangleTest.getBtnOutlineColor();
        
        JPanel buttonPane = (JPanel) dlgRectangleTest.getContentPane().getComponent(1);
        okButton = (JButton) buttonPane.getComponent(0);
        cancelButton = (JButton) buttonPane.getComponent(1);
    }

    @Test
    public void testValidInput() {
        txtX.setText("10");
        txtY.setText("20");
        txtHeight.setText("40");
        txtWidth.setText("40");
        btnInnerColor.setBackground(Color.GREEN);
        btnOutlineColor.setBackground(Color.RED);
        
        okButton.doClick();
        
        System.out.println("Actual Height: " + dlgRectangleTest.getRect().getHeight());
        System.out.println("Expected Height: 40");

        assertTrue(dlgRectangleTest.isConfirm());
        assertNotNull(dlgRectangleTest.getRect());
        assertEquals(10, dlgRectangleTest.getRect().getUpperLeftPoint().getX());
        assertEquals(20, dlgRectangleTest.getRect().getUpperLeftPoint().getY());
        assertEquals(40, dlgRectangleTest.getRect().getHeight(), "Height should be 40");
        assertEquals(40, dlgRectangleTest.getRect().getWidth(), "Width should be 40");
        assertEquals(Color.GREEN, dlgRectangleTest.getRect().getInnerColor(), "Inner color should be GREEN");
        assertEquals(Color.RED, dlgRectangleTest.getRect().getColor(), "Outline color should be RED");
    }



    @Test
    public void testEmptyInput() {
        txtX.setText("");
        txtY.setText("");
        txtHeight.setText("");
        txtWidth.setText("");
        
        okButton.doClick();
        
        assertFalse(dlgRectangleTest.isConfirm());
    }

    @Test
    public void testNegativeValues() {
        txtX.setText("-10");
        txtY.setText("20");
        txtHeight.setText("30");
        txtWidth.setText("-40");
        
        okButton.doClick();
        
        assertFalse(dlgRectangleTest.isConfirm());
    }

    @Test
    public void testNonNumericInput() {
        txtX.setText("abc");
        txtY.setText("def");
        txtHeight.setText("30");
        txtWidth.setText("xyz");
        
        okButton.doClick();
        
        assertFalse(dlgRectangleTest.isConfirm());
    }

    @Test
    public void testInnerColorSelection() {
        btnInnerColor.setBackground(Color.YELLOW);
        assertEquals(Color.YELLOW, dlgRectangleTest.getBtnInnerColor().getBackground());
    }

    @Test
    public void testOutlineColorSelection() {
        btnOutlineColor.setBackground(Color.BLUE);
        assertEquals(Color.BLUE, dlgRectangleTest.getBtnOutlineColor().getBackground());
    }
}
