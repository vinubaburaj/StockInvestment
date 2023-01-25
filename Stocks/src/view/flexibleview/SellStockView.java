package view.flexibleview;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controller.FlexiblePortfolioFeatures;
import enums.stockTicker;
import view.ViewGUI;

/**
 * This is the class that implements the Sell Stocks
 * view and calls controller for all actions performs in this screen.
 */
public class SellStockView extends JFrame implements ViewGUI {
  private final JButton backButton;
  private final JTextField shareAmount;
  private final JTextField date;
  private final JComboBox stockOptions;
  private final JButton sellButton;

  /**
   * This is the constructor for the Sell Stocks view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public SellStockView(String caption) {
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
    JLabel display = new JLabel("Enter a stock from the drop down below");
    panel.add(display, c);

    String[] stockChoices = new String[20];
    int i = 0;

    for (stockTicker st : stockTicker.values()) {
      stockChoices[i++] = st.toString();
    }

    c.gridy = 2;
    stockOptions = new JComboBox<String>(stockChoices);
    panel.add(stockOptions, c);

    c.gridy = 4;
    display = new JLabel("Enter whole number amount of shares");
    panel.add(display, c);

    c.gridy = 5;
    shareAmount = new JTextField();
    panel.add(shareAmount, c);

    c.gridy = 7;
    display = new JLabel("Enter date in YYYY-MM-DD format");
    panel.add(display, c);

    c.gridy = 8;
    date = new JTextField();
    panel.add(date, c);

    c.gridy = 10;
    sellButton = new JButton("Sell stock");
    panel.add(sellButton, c);
    sellButton.setActionCommand("sellButton");

    c.gridy = 12;
    backButton = new JButton("Go Back");
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
    sellButton.addActionListener(f -> features.performSellShare((String) stockOptions
            .getSelectedItem(), shareAmount.getText(), date.getText()));
    backButton.addActionListener(f -> features.backToPortfolioOperations());
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
    shareAmount.setText("");
    date.setText("");
  }
}
