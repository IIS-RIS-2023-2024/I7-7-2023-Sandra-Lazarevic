package mvc;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import geometry.Point;

public class DlgPoint extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtX;
    private JTextField txtY;
    private Point p;
    private boolean confirm;
    private JButton btnColor;
    private Color color = Color.RED;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DlgPoint dialog = new DlgPoint();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DlgPoint() {
        initialize();
    }

    private void initialize() {
        setTitle("Modify Point");
        setModal(true);
        setResizable(false);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        addLabel("Coordinate X", 1, 1);
        txtX = addTextField(4, 1);

        addLabel("Coordinate Y", 1, 3);
        txtY = addTextField(4, 3);

        btnColor = new JButton("COLOR");
        btnColor.setBackground(Color.WHITE);
        btnColor.addActionListener(e -> chooseColor());
        GridBagConstraints gbc_btnColor = new GridBagConstraints();
        gbc_btnColor.insets = new Insets(0, 0, 0, 5);
        gbc_btnColor.gridx = 3;
        gbc_btnColor.gridy = 5;
        contentPanel.add(btnColor, gbc_btnColor);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOk());
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    private void addLabel(String text, int gridx, int gridy) {
        JLabel label = new JLabel(text);
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = gridx;
        gbc_label.gridy = gridy;
        contentPanel.add(label, gbc_label);
    }

    private JTextField addTextField(int gridx, int gridy) {
        JTextField textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = gridx;
        gbc_textField.gridy = gridy;
        contentPanel.add(textField, gbc_textField);
        textField.setColumns(10);
        return textField;
    }

    private void chooseColor() {
        Color newColor = JColorChooser.showDialog(this, "Choose outline color", color);
        if (newColor != null) {
            color = newColor;
            btnColor.setBackground(color);
        }
    }

    public void onOk() {
        if (isInputValid()) {
            p = new Point(Integer.parseInt(txtX.getText().trim()), Integer.parseInt(txtY.getText().trim()), false, color);
            confirm = true;
            setVisible(false);
        }
    }

    private boolean isInputValid() {
        if (txtX.getText().trim().isEmpty() || txtY.getText().trim().isEmpty()) {
            showMessage("All fields are required!", "ERROR");
            return false;
        }

        try {
            int x = Integer.parseInt(txtX.getText().trim());
            int y = Integer.parseInt(txtY.getText().trim());

            if (x < 0 || y < 0) {
                showMessage("Insert values greater than 0!", "ERROR");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessage("Enter numbers only!", "ERROR");
            return false;
        }
        return true;
    }

    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
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

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public JButton getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(JButton btnColor) {
        this.btnColor = btnColor;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public Color getC() {
        return color;
    }

    public void setC(Color color) {
        this.color = color;
    }
}
