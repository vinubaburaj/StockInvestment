package view.flexibleview;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.FlexiblePortfolioFeatures;
import view.ViewGUI;

/**
 * This is the class that implements the Get Cost Basis
 * view and calls controller for all actions performs in this screen.
 */
public class GetCostBasisView extends JFrame implements ViewGUI {
  private final JButton backButton;
  private final JButton enterButton;
  private final JTextField input;
  private final JPanel panel;
  private JLabel display;

  /**
   * This is the constructor for the Get Cost Basis view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public GetCostBasisView(String caption) {
    super(caption);

    setSize(5500, 9500);
    setLocation(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    display = new JLabel("Input date in YYYY-MM-DD format on which you" +
            " want to find the cost basis");
    panel.add(display, c);

    c.gridy = 1;
    input = new JTextField();
    panel.add(input, c);

    c.gridy = 3;
    enterButton = new JButton("Get value");
    panel.add(enterButton, c);
    enterButton.setActionCommand("enterButton");

    c.gridy = 5;
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
    enterButton.addActionListener(f -> features.getCostBasis(input.getText()));
    backButton.addActionListener(f -> features.backToPortfolioOperations());
  }

  @Override
  public void isVisible(boolean visible) {
    setVisible(visible);
  }

  @Override
  public void echoOutput(String output) {

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 7;
    display = new JLabel(output);
    panel.add(display, c);

    this.getContentPane().add(panel);
    pack();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }
}
