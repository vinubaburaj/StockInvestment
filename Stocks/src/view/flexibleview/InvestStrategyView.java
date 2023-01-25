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
 * This is the class that implements the Investment Strategy
 * view and calls controller for all actions performs in this screen.
 */
public class InvestStrategyView extends JFrame implements ViewGUI {
  private final JButton createButton;
  private final JTextField strategyName;
  private final JTextField investmentAmount;

  private final JTextField startingDate;

  private final JTextField recurringDays;

  private final JButton backButton;

  private final JTextField endingDate;

  /**
   * This is the constructor for the Investment Strategy View.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public InvestStrategyView(String caption) {
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
    JLabel display = new JLabel("Enter a name for your strategy:");
    panel.add(display, c);

    c.gridy = 1;
    strategyName = new JTextField();
    panel.add(strategyName, c);

    c.gridy = 3;
    display = new JLabel("Enter the total amount you want to invest in this strategy");
    panel.add(display, c);

    c.gridy = 4;
    investmentAmount = new JTextField();
    panel.add(investmentAmount, c);

    c.gridy = 6;
    display = new JLabel("Enter the starting date of your " +
            "investment strategy in YYYY-MM-DD format");
    panel.add(display, c);

    c.gridy = 8;
    startingDate = new JTextField();
    panel.add(startingDate, c);

    c.gridy = 10;
    display = new JLabel("Enter the ending date of your " +
            "investment strategy in YYYY-MM-DD format(Enter 'None' if you do not wish " +
            "to specify an end date)");
    panel.add(display, c);

    c.gridy = 12;
    endingDate = new JTextField();
    panel.add(endingDate, c);

    c.gridy = 14;
    display = new JLabel("How often do you want the investment to occur? " +
            "(Enter in number of days)");
    panel.add(display, c);

    c.gridy = 16;
    recurringDays = new JTextField();
    panel.add(recurringDays, c);

    c.gridy = 18;
    createButton = new JButton("Create strategy");
    panel.add(createButton, c);
    createButton.setActionCommand("createButton");

    c.gridy = 20;
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
      JOptionPane.showMessageDialog(null, infoMessage,
              "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, infoMessage,
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void addFeatures(FlexiblePortfolioFeatures features) {
    backButton.addActionListener(f -> features.backToPortfolioOperations());
    createButton.addActionListener(f -> features.getInvestmentStrategyDetailsFromView(
            strategyName.getText(), investmentAmount.getText(), startingDate.getText(),
            endingDate.getText(), recurringDays.getText()));


  }

  @Override
  public void isVisible(boolean visible) {
    this.setVisible(visible);
  }

  @Override
  public void echoOutput(String output) {
    return;
  }

  @Override
  public void clearInputString() {
    strategyName.setText("");
    investmentAmount.setText("");
    startingDate.setText("");
    recurringDays.setText("");
    endingDate.setText("");
  }
}
