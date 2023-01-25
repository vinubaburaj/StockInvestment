package view.flexibleview;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import controller.FlexiblePortfolioFeatures;
import view.ViewGUI;

/**
 * This is the class that implements the commission view to set new commission value.
 */
public class CommissionView extends JFrame implements ViewGUI {
  private final JTextField input;
  private final JButton changeCommission;
  private final JButton backButton;
  private JLabel display;

  /**
   * This is the constructor for the commission view. It sets up the features of the view.
   *
   * @param caption the caption for the view.
   * @param comm    the commission value.
   */
  public CommissionView(String caption, float comm) {
    super(caption);

    setSize(5500, 5500);
    setLocation(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    display = new JLabel("Current commission is: " + comm);
    panel.add(display, c);

    c.gridx = 0;
    c.gridy = 2;
    display = new JLabel("Enter new commission value in number:");
    panel.add(display, c);

    c.gridx = 0;
    c.gridy = 3;
    input = new JTextField();
    panel.add(input, c);

    c.gridx = 0;
    c.gridy = 5;
    changeCommission = new JButton("Change commission");
    panel.add(changeCommission, c);
    changeCommission.setActionCommand("changeCommission");

    c.gridx = 0;
    c.gridy = 7;
    backButton = new JButton("Go back");
    panel.add(backButton, c);
    backButton.setActionCommand("backButton");

    this.getContentPane().add(panel);
    pack();
  }

  /**
   * This is the function for throwing a popup to let the user know about something.
   *
   * @param infoMessage the info message.
   * @param action      the infobox type.
   */
  public static void infoBox(String infoMessage, String action) {
    if (action.equalsIgnoreCase("info")) {
      JOptionPane.showMessageDialog(null,
              infoMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null,
              infoMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void addFeatures(FlexiblePortfolioFeatures features) {
    changeCommission.addActionListener(f -> features.changeCommission(input.getText()));
    backButton.addActionListener(f -> features.backToPortfolioOperations());
  }

  @Override
  public void isVisible(boolean visible) {
    setVisible(visible);
  }

  @Override
  public void echoOutput(String output) {
    display = new JLabel(output);
    this.add(display);
    pack();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }
}
