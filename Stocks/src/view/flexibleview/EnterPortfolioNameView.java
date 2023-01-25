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
 * This is the class that implements the Enter Portfolio name
 * view and calls controller for all actions performs in this screen.
 */
public class EnterPortfolioNameView extends JFrame implements ViewGUI {
  private final JButton enterButton;
  private final JTextField input;

  /**
   * This is the constructor for the entering portfolio name view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public EnterPortfolioNameView(String caption) {
    super(caption);

    setSize(5500, 9500);
    setLocation(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    JLabel display = new JLabel("Enter the name of an existing portfolio");
    panel.add(display, c);

    c.gridy = 1;
    input = new JTextField();
    panel.add(input, c);

    c.gridy = 3;
    enterButton = new JButton("Go!");
    panel.add(enterButton, c);
    enterButton.setActionCommand("enterButton");

    this.getContentPane().add(panel);
    pack();
    setVisible(false);
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
    enterButton.addActionListener(f -> features.setPortfolioName(input.getText()));
  }

  @Override
  public void isVisible(boolean visible) {
    setVisible(visible);
  }

  @Override
  public void echoOutput(String output) {
    return;
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }


}
