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

import java.util.HashMap;
import java.util.List;

import controller.FlexiblePortfolioFeatures;
import enums.stockTicker;
import view.ViewGUI;

/**
 * This is the class that implements the Purchase Stocks
 * view and calls controller for all actions performs in this screen.
 */
public class PurchaseStocksView extends JFrame implements ViewGUI {
  private final JButton backButton;
  private final JTextField shareAmount;
  private final JTextField date;
  private final JComboBox stockOptions;
  private final JButton purchaseButton;
  private final JButton finishButton;
  private final List<HashMap<String, String>> stockList;

  /**
   * This is the constructor for the Purchase Stocks view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   * @param stocks  the stocks to buy.
   */
  public PurchaseStocksView(String caption, List<HashMap<String, String>> stocks) {
    super(caption);

    stockList = stocks;

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
    display = new JLabel("Click Purchase to add this stock, or " +
            "Finished if you are done buying stocks");
    panel.add(display, c);

    c.gridy = 12;
    purchaseButton = new JButton("Purchase this stock");
    panel.add(purchaseButton, c);

    c.gridy = 14;
    finishButton = new JButton("Finished buying");
    panel.add(finishButton, c);

    c.gridy = 16;
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

    backButton.addActionListener(f -> features.backToPortfolioOperations());
    purchaseButton.addActionListener(f -> features.getPurchasedStocksFromView(stockList,
            (String) stockOptions.getSelectedItem(), shareAmount.getText(), date.getText(),
            false));
    finishButton.addActionListener(f -> features.getPurchasedStocksFromView(stockList,
            (String) stockOptions.getSelectedItem(), shareAmount.getText(), date.getText(),
            true));
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
    shareAmount.setText("");
    date.setText("");
  }
}
