package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

public class DlgDonut extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtR;
    private JTextField txtInnerR;
    private boolean confirmed;
    private Donut donut;
    private JButton btnInnerColor;
    private JButton btnOutlineColor;

    public static void main(String[] args) {
        try {
            DlgDonut dialog = new DlgDonut();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgDonut() {
        setupDialog();
        setupContentPanel();
        setupButtons();
    }

    private void setupDialog() {
        setTitle("Add or Modify Donut");
        setBackground(Color.WHITE);
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
    }

    private void setupContentPanel() {
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout layout = new GridBagLayout();
        layout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(layout);

        addLabel("CENTER OF DONUT", 2, 0);
        addLabel("Coordinate X", 2, 1);
        txtX = addTextField(4, 1);

        addLabel("Coordinate Y", 2, 2);
        txtY = addTextField(4, 2);

        addLabel("Radius", 2, 4);
        txtR= addTextField(4, 4);

        addLabel("Inner Radius", 2, 5);
        txtInnerR = addTextField(4, 5);

        btnInnerColor = createColorButton("Inner Color", Color.WHITE, 2, 6);
        btnOutlineColor = createColorButton("Outline Color", Color.RED, 2, 7);
    }

    private JLabel addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        contentPanel.add(label, gbc);
        return label;
    }

    private JTextField addTextField(int x, int y) {
        JTextField textField = new JTextField();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = x;
        gbc.gridy = y;
        contentPanel.add(textField, gbc);
        textField.setColumns(10);
        return textField;
    }

    private JButton createColorButton(String text, Color color, int x, int y) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color chosenColor = JColorChooser.showDialog(null, "Choose color", button.getBackground());
                if (chosenColor != null) {
                    button.setBackground(chosenColor);
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        contentPanel.add(button, gbc);
        return button;
    }

    private void setupButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOkAction();
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    private void handleOkAction() {
        if (isInputValid()) {
            try {
                int x = Integer.parseInt(txtX.getText().trim());
                int y = Integer.parseInt(txtY.getText().trim());
                int radius = Integer.parseInt(txtR.getText().trim());
                int innerRadius = Integer.parseInt(txtInnerR.getText().trim());

                if (innerRadius >= radius) {
                    showError("Inner radius must be less than outer radius!");
                } else {
                    donut = new Donut(new Point(x, y), radius, innerRadius, false,
                            btnOutlineColor.getBackground(), btnInnerColor.getBackground());
                    confirmed = true;
                    setVisible(false);
                }
            } catch (NumberFormatException ex) {
                showError("Enter valid numbers for all fields!");
            }
        } else {
            showError("All values are required and must be greater than 0!");
        }
    }

    private boolean isInputValid() {
        return !txtX.getText().trim().isEmpty() &&
               !txtY.getText().trim().isEmpty() &&
               !txtR.getText().trim().isEmpty() &&
               !txtInnerR.getText().trim().isEmpty() &&
               Integer.parseInt(txtX.getText().trim()) >= 0 &&
               Integer.parseInt(txtY.getText().trim()) >= 0 &&
               Integer.parseInt(txtR.getText().trim()) > 0 &&
               Integer.parseInt(txtInnerR.getText().trim()) > 0;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
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

	public void setTxtRadius(JTextField txtR) {
		this.txtR= txtR;
	}

	public JTextField getTxtInnerR() {
		return txtInnerR;
	}

	public void setTxtInnerRadius(JTextField txtInnerR) {
		this.txtInnerR = txtInnerR;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
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

}
