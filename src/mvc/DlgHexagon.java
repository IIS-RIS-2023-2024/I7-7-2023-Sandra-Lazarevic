package mvc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgHexagon extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtR;
    private boolean isOk;
    private JButton btnBorderColor;
    private JButton btnInnerColor;

    public static void main(String[] args) {
        try {
            DlgHexagon dialog = new DlgHexagon();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgHexagon() {
        setTitle("Add or Modify Hexagon");
        setModal(true);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.PINK);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setupLayout();
        setupButtons();
    }

    private void setupLayout() {
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        addLabel("Coordinate X:", 2, 1);
        addLabel("Coordinate Y:", 8, 1);
        txtX = addTextField(2, 2);
        txtY = addTextField(8, 2);
        btnBorderColor = addColorButton("Border color", 8, 4);
        btnInnerColor = addColorButton("Inner color", 8, 5);
        addLabel("Radius:", 4, 6);
        txtR = addTextField(4, 7);
    }

    private void setupButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setConfirm(true);
                setVisible(false);
            }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }

    private void addLabel(String text, int gridx, int gridy) {
        JLabel label = new JLabel(text);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 5, 5);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        contentPanel.add(label, gbc);
    }

    private JTextField addTextField(int gridx, int gridy) {
        JTextField textField = new JTextField();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        contentPanel.add(textField, gbc);
        textField.setColumns(10);
        return textField;
    }

    private JButton addColorButton(String text, int gridx, int gridy) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose color", button.getBackground());
                if (color != null) {
                    button.setBackground(color);
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        contentPanel.add(button, gbc);
        return button;
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

    public boolean isConfirm() {
        return isOk;
    }

    public void setConfirm(boolean isOk) {
        this.isOk = isOk;
    }

    public JButton getBtnBorderColor() {
        return btnBorderColor;
    }

    public Color getBorderColor() {
        return btnBorderColor.getBackground();
    }

    public JButton getBtnInnerColor() {
        return btnInnerColor;
    }

    public Color getInnerColor() {
        return btnInnerColor.getBackground();
    }
}
