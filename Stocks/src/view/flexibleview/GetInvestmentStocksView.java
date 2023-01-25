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

import controller.FlexiblePortfolioFeatures;
import enums.stockTicker;
import view.ViewGUI;

/**
 * This is the class that implements the Get investment stocks
 * view and calls controller for all actions performs in this screen.
 */
public class GetInvestmentStocksView extends JFrame implements ViewGUI {
  private final JTextField stockWeightage;
  private final JComboBox stockOptions;
  private final JButton addButton;
  private final JButton finishButton;
  private final String name;
  private final String amount;
  private final String startDate;
  private final String endDate;
  private final String recurrDays;
  private final HashMap<String, Integer> stockList;
  private JButton backButton;

  /**
   * This is the constructor for the Get Investments Screen.
   * It sets up the features of the view.
   *
   * @param caption          the caption for the view.
   * @param strategyName     the DCA strategy name.
   * @param stocks           the proportions for each stock.
   * @param startingDate     the start date
   * @param endingDate       the end date for the strategy.
   * @param recurringDays    the frequency in days.
   * @param investmentAmount the frequency in days.
   */
  public GetInvestmentStocksView(String caption, HashMap<String, Integer> stocks,
                                 String strategyName, String investmentAmount, String startingDate,
                                 String endingDate, String recurringDays) {
    super(caption);
    stockList = stocks;
    name = strategyName;
    amount = investmentAmount;
    startDate = startingDate;
    endDate = endingDate;
    recurrDays = recurringDays;

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

    c.gridy = 6;
    display = new JLabel("Enter whole number percentage of weightage you want to give " +
            "to this share(out of 100)");
    panel.add(display, c);

    c.gridy = 7;
    display = new JLabel("Note: The total of all weightage you enter " +
            "should sum up to 100. ");
    panel.add(display, c);

    c.gridy = 9;
    stockWeightage = new JTextField();
    panel.add(stockWeightage, c);

    c.gridy = 11;
    addButton = new JButton("Add stock");
    panel.add(addButton, c);
    addButton.setActionCommand("addButton");

    c.gridy = 14;
    display = new JLabel("If you are done adding stocks and would like " +
            "to finish, hit Finish");
    panel.add(display, c);

    c.gridy = 15;
    finishButton = new JButton("Finished adding");
    panel.add(finishButton, c);
    finishButton.setActionCommand("finishButton");

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
    addButton.addActionListener(f -> features.processInvestmentStrategy(stockList, name, amount,
            startDate, endDate, recurrDays, (String) stockOptions.getSelectedItem(),
            stockWeightage.getText(), false));
    finishButton.addActionListener(f -> features.processInvestmentStrategy(stockList, name, amount,
            startDate, endDate, recurrDays, (String) stockOptions.getSelectedItem(),
            stockWeightage.getText(), true));

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
    stockWeightage.setText("");
  }
}
