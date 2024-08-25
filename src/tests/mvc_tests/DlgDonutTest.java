package tests.mvc_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.swing.*;
import java.awt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.DlgDonut;
import geometry.Donut;
import geometry.Point;

public class DlgDonutTest {

    private DlgDonut dlgDonutTest;
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtR;
    private JTextField txtInnerR;
    private JButton btnInnerColor;
    private JButton btnOutlineColor;
    private JButton okButton;

    @BeforeEach
    public void setUp() {
        dlgDonutTest = new DlgDonut();
        txtX = dlgDonutTest.getTxtX();
        txtY = dlgDonutTest.getTxtY();
        txtR = dlgDonutTest.getTxtR();
        txtInnerR = dlgDonutTest.getTxtInnerR();
        btnInnerColor = dlgDonutTest.getBtnInnerColor();
        btnOutlineColor = dlgDonutTest.getBtnOutlineColor();
        okButton = findOkButton(dlgDonutTest);
    }

    @Test
    public void testValidInput() {
        txtX.setText("10");
        txtY.setText("20");
        txtR.setText("30");
        txtInnerR.setText("15");
        btnInnerColor.setBackground(Color.GREEN);
        btnOutlineColor.setBackground(Color.BLUE);

        okButton.doClick();

        assertTrue(dlgDonutTest.isConfirmed());
        Donut donut = dlgDonutTest.getDonut();
        assertNotNull(donut);
        assertEquals(new Point(10, 20), donut.getCenter());
        assertEquals(30, donut.getRadius());
        assertEquals(15, donut.getInnerRadius());
        assertEquals(Color.BLUE, donut.getColor());
        assertEquals(Color.GREEN, donut.getInnerColor());
    }

    @Test
    public void testEmptyInput() {
        txtX.setText("");
        txtY.setText("");
        txtR.setText("");
        txtInnerR.setText("");

        okButton.doClick();

        assertFalse(dlgDonutTest.isConfirmed());
    }

    @Test
    public void testInvalidRadius() {
        txtX.setText("10");
        txtY.setText("20");
        txtR.setText("15");
        txtInnerR.setText("30");

        okButton.doClick();

        assertFalse(dlgDonutTest.isConfirmed());
    }

    @Test
    public void testColorSelection() {
        btnInnerColor.setBackground(Color.YELLOW);
        btnOutlineColor.setBackground(Color.RED);

        assertEquals(Color.YELLOW, dlgDonutTest.getBtnInnerColor().getBackground());
        assertEquals(Color.RED, dlgDonutTest.getBtnOutlineColor().getBackground());
    }

    private JButton findOkButton(DlgDonut dlgDonut) {
        try {
            for (Component comp : dlgDonut.getContentPane().getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel panel = (JPanel) comp;
                    for (Component buttonComp : panel.getComponents()) {
                        if (buttonComp instanceof JButton) {
                            JButton button = (JButton) buttonComp;
                            if ("OK".equals(button.getActionCommand())) {
                                return button;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
