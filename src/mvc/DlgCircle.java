package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

public class DlgCircle extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtR;
    private Circle circle;
    private boolean confirm;
    private JButton btnInnerColor;
    private JButton btnOutlineColor;
    private JButton btnOk;
    private JButton btnCancel;

    public DlgCircle() {
        setTitle("Add new or modify existing circle");
        setResizable(false);
        setModal(true);
        initializeUI();
    }

    private void initializeUI() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setupContentPanel();
        setupButtonPane();
    }

    private void setupContentPanel() {
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        contentPanel.setLayout(gbl_contentPanel);

        addLabelToPanel("CENTER OF CIRCLE", 2, 0);
        addLabelToPanel("coordinate X", 2, 1);
        txtX = addTextFieldToPanel(4, 1);
        addLabelToPanel("coordinate Y", 2, 2);
        txtY = addTextFieldToPanel(4, 2);
        addLabelToPanel("Radius", 2, 5);
        txtR = addTextFieldToPanel(4, 5);

        btnInnerColor = addColorButtonToPanel("Inner Color", Color.LIGHT_GRAY, 2, 6);
        btnOutlineColor = addColorButtonToPanel("Outline Color", Color.BLACK, 2, 7);
    }

    private void setupButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOk());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    private void addLabelToPanel(String text, int gridx, int gridy) {
        JLabel label = new JLabel(text);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        contentPanel.add(label, gbc);
    }

    private JTextField addTextFieldToPanel(int gridx, int gridy) {
        JTextField textField = new JTextField();
        textField.setTransferHandler(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        contentPanel.add(textField, gbc);
        textField.setColumns(10);
        return textField;
    }

    private JButton addColorButtonToPanel(String text, Color initialColor, int gridx, int gridy) {
        JButton button = new JButton(text);
        button.setBackground(initialColor);
        button.addActionListener(e -> chooseColor(button));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        contentPanel.add(button, gbc);
        return button;
    }

    private void chooseColor(JButton button) {
        Color chosenColor = JColorChooser.showDialog(null, "Choose color", button.getBackground());
        if (chosenColor != null) {
            button.setBackground(chosenColor);
        }
    }

    public void onOk() {
        if (areFieldsEmpty()) {
            displayErrorMessage("All fields are required!");
            setConfirm(false);
        } else {
            try {
                validateAndCreateCircle();
            } catch (NumberFormatException e) {
                displayErrorMessage("Enter numbers only");
            }
        }
    }

    private boolean areFieldsEmpty() {
        return txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty() || txtR.getText().trim().isEmpty();
    }

    private void validateAndCreateCircle() {
        int x = Integer.parseInt(txtX.getText());
        int y = Integer.parseInt(txtY.getText());
        int radius = Integer.parseInt(txtR.getText());

        if (x < 0 || y < 0 || radius <= 0) {
            displayErrorMessage("Insert values greater than 0!");
        } else {
            circle = new Circle(new Point(x, y), radius, false, btnOutlineColor.getBackground(), btnInnerColor.getBackground());
            setConfirm(true);
            setVisible(false);
        }
    }

    private void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public JTextField getTxtX() {
        return txtX;
    }

    public void setTxtX(JTextField txtX) {
        this.txtX = txtX;
    }

    public JTextField getTxtY() {
        return txtY;
    }

    public void setTxtY(JTextField txtY) {
        this.txtY = txtY;
    }

    public JTextField getTxtR() {
        return txtR;
    }

    public void setTxtR(JTextField txtR) {
        this.txtR = txtR;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public JButton getBtnInnerColor() {
        return btnInnerColor;
    }

    public void setBtnInnerColor(JButton btnInnerColor) {
        this.btnInnerColor = btnInnerColor;
    }

    public JButton getBtnOutlineColor() {
        return btnOutlineColor;
    }

    public void setBtnOutlineColor(JButton btnOutlineColor) {
        this.btnOutlineColor = btnOutlineColor;
    }

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
    
}
