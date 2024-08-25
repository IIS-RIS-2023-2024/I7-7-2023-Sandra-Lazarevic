package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import geometry.Line;
import geometry.Point;

public class DlgLine extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private boolean confirm;
    private JTextField txtSPX;
    private JTextField txtSPY;
    private JTextField txtEPX;
    private JTextField txtEPY;
    private JButton btnOutlineColor;
    private Line line;

    public DlgLine() {
        setupDialog();
        setupContentPanel();
        setupButtonPanel();
    }

    private void setupDialog() {
        setModal(true);
        setTitle("Modify Line");
        setBackground(Color.WHITE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
    }

    private void setupContentPanel() {
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(createContentPanelLayout());
        addLabelsAndFields();
        setupOutlineColorButton();
    }

    private GridBagLayout createContentPanelLayout() {
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        layout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        return layout;
    }

    private void addLabelsAndFields() {
        addLabel("START POINT", 0, 2);
        addLabel("coordinate X", 1, 2);
        txtSPX = addTextField(1, 5);
        addLabel("coordinate Y", 2, 2);
        txtSPY = addTextField(2, 5);

        addLabel("END POINT", 4, 2);
        addLabel("coordinate X", 5, 2);
        txtEPX = addTextField(5, 5);
        addLabel("coordinate Y", 6, 2);
        txtEPY = addTextField(6, 5);
    }

    private void addLabel(String text, int row, int column) {
        JLabel label = new JLabel(text);
        GridBagConstraints gbc = createGbc(column, row);
        contentPanel.add(label, gbc);
    }

    private JTextField addTextField(int row, int column) {
        JTextField textField = new JTextField(10);
        GridBagConstraints gbc = createGbc(column, row);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(textField, gbc);
        return textField;
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    private void setupOutlineColorButton() {
        btnOutlineColor = new JButton("OUTLINE COLOR");
        btnOutlineColor.addActionListener(this::chooseOutlineColor);
        GridBagConstraints gbc = createGbc(2, 8);
        contentPanel.add(btnOutlineColor, gbc);
    }

    private void chooseOutlineColor(ActionEvent e) {
        Color outlineColor = JColorChooser.showDialog(null, "Choose outline color", btnOutlineColor.getBackground());
        if (outlineColor != null) {
            btnOutlineColor.setBackground(outlineColor);
        }
    }

    private void setupButtonPanel() {
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = createButton("OK", e -> onOk());
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = createButton("Cancel", e -> dispose());
        buttonPane.add(cancelButton);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    public void onOk() {
        if (validateInput()) {
            line = new Line(
                new Point(Integer.parseInt(txtSPX.getText()), Integer.parseInt(txtSPY.getText())),
                new Point(Integer.parseInt(txtEPX.getText()), Integer.parseInt(txtEPY.getText())),
                false, btnOutlineColor.getBackground()
            );
            confirm = true;
            setVisible(false);
        }
    }

    private boolean validateInput() {
        if (isAnyFieldEmpty()) {
            showErrorMessage("All fields are required!");
            return false;
        }
        if (isAnyFieldNegative()) {
            showErrorMessage("Insert values greater than 0!");
            return false;
        }
        return true;
    }

    private boolean isAnyFieldEmpty() {
        return txtSPX.getText().trim().isEmpty() ||
               txtSPY.getText().trim().isEmpty() ||
               txtEPX.getText().trim().isEmpty() ||
               txtEPY.getText().trim().isEmpty();
    }

    private boolean isAnyFieldNegative() {
        try {
            return Integer.parseInt(txtSPX.getText()) < 0 ||
                   Integer.parseInt(txtSPY.getText()) < 0 ||
                   Integer.parseInt(txtEPX.getText()) < 0 ||
                   Integer.parseInt(txtEPY.getText()) < 0;
        } catch (NumberFormatException e) {
            showErrorMessage("Enter numbers only!");
            return true;
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        confirm = false;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public Line getLine() {
        return line;
    }

    public static void main(String[] args) {
        DlgLine dialog = new DlgLine();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

	public JTextField getTxtSPX() {
		return txtSPX;
	}

	public void setTxtSPX(JTextField txtSPX) {
		this.txtSPX = txtSPX;
	}

	public JTextField getTxtSPY() {
		return txtSPY;
	}

	public void setTxtSPY(JTextField txtSPY) {
		this.txtSPY = txtSPY;
	}

	public JTextField getTxtEPX() {
		return txtEPX;
	}

	public void setTxtEPX(JTextField txtEPX) {
		this.txtEPX = txtEPX;
	}

	public JTextField getTxtEPY() {
		return txtEPY;
	}

	public void setTxtEPY(JTextField txtEPY) {
		this.txtEPY = txtEPY;
	}

	public JButton getBtnOutlineColor() {
		return btnOutlineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnOutlineColor = btnOutlineColor;
	}

}
