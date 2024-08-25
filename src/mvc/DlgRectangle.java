package mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

public class DlgRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX, txtY, txtHeight, txtWidth;
	private JButton btnInnerColor, btnOutlineColor;
	private boolean confirm;
	private Rectangle rect;

	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRectangle() {
		setupDialog();
		setupContentPanel();
		setupButtonPanel();
	}

	private void setupDialog() {
		setTitle("Add or Modify Rectangle");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

	private void setupContentPanel() {
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(createGridBagLayout());

		addLabel("UPPER LEFT POINT", 0);
		addLabel("Coordinate X", 1);
		txtX = addTextField(1);
		addLabel("Coordinate Y", 2);
		txtY = addTextField(2);
		addLabel("Height", 4);
		txtHeight = addTextField(4);
		addLabel("Width", 5);
		txtWidth = addTextField(5);

		btnInnerColor = addColorButton("Inner Color", 7, Color.GREEN);
		btnOutlineColor = addColorButton("Outline Color", 8, Color.GREEN);
	}

	private void setupButtonPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		addButton("OK", buttonPane, this::handleOkAction);
		addButton("Cancel", buttonPane, e -> dispose());
	}

	private GridBagLayout createGridBagLayout() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		return gbl;
	}

	private JLabel addLabel(String text, int row) {
		JLabel label = new JLabel(text);
		GridBagConstraints gbc = createGbc(2, row);
		contentPanel.add(label, gbc);
		return label;
	}

	private JTextField addTextField(int row) {
		JTextField textField = new JTextField();
		GridBagConstraints gbc = createGbc(4, row);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(textField, gbc);
		textField.setColumns(10);
		return textField;
	}

	private JButton addColorButton(String text, int row, Color initialColor) {
		JButton button = new JButton(text);
		button.setBackground(initialColor);
		button.addActionListener(e -> chooseColor(button));
		GridBagConstraints gbc = createGbc(3, row);
		gbc.anchor = GridBagConstraints.SOUTH;
		contentPanel.add(button, gbc);
		return button;
	}

	private void addButton(String text, JPanel panel, ActionListener actionListener) {
		JButton button = new JButton(text);
		button.addActionListener(actionListener);
		button.setActionCommand(text);
		panel.add(button);
		if (text.equals("OK")) {
			getRootPane().setDefaultButton(button);
		}
	}

	private GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		return gbc;
	}

	public void chooseColor(JButton button) {
		Color color = JColorChooser.showDialog(null, "Choose Color", button.getBackground());
		if (color != null) {
			button.setBackground(color);
		}
	}

	public void handleOkAction(ActionEvent e) {
		try {
			if (areFieldsValid()) {
				rect = createRectangle();
				setConfirm(true);
				setVisible(false);
			} else {
				showErrorMessage("All values are required or must be greater than 0!");
				setConfirm(false);
			}
		} catch (NumberFormatException ex) {
			showErrorMessage("Enter numbers only!");
		}
	}

	public boolean areFieldsValid() {
		return !txtX.getText().trim().isEmpty() && !txtY.getText().trim().isEmpty()
				&& !txtHeight.getText().trim().isEmpty() && !txtWidth.getText().trim().isEmpty()
				&& Integer.parseInt(txtWidth.getText()) > 0 && Integer.parseInt(txtHeight.getText()) > 0
				&& Integer.parseInt(txtX.getText()) >= 0 && Integer.parseInt(txtY.getText()) >= 0;
	}

	private Rectangle createRectangle() {
		return new Rectangle(new Point(Integer.parseInt(txtX.getText()), Integer.parseInt(txtY.getText())),
				Integer.parseInt(txtWidth.getText()), Integer.parseInt(txtHeight.getText()), false,
				btnOutlineColor.getBackground(), btnInnerColor.getBackground());
	}

	private void showErrorMessage(String message) {
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

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
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
