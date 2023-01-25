package view.flexibleview;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.FlexiblePortfolioFeatures;
import view.ViewGUI;

/**
 * This is the class that implements the Flexible portfolio Options
 * view and calls controller for all actions performs in this screen.
 */
public class FlexiblePortfolioOptionsView extends JFrame implements ViewGUI {
  private final JButton buyButton;
  private final JButton sellButton;
  private final JButton strategyButton;
  private final JButton compositionButton;
  private final JButton totalValueButton;
  private final JButton costBasisButton;

  private final JButton homeButton;
  private final JButton commissionValueButton;

  /**
   * This is the constructor for the Flexible portfolio Options view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public FlexiblePortfolioOptionsView(String caption) {
    super(caption);

    setSize(100000, 10000);
    setLocation(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    JLabel display = new JLabel("Choose an operation:");
    panel.add(display, c);

    c.gridy = 2;
    buyButton = new JButton("Buy Stocks");
    panel.add(buyButton, c);
    buyButton.setActionCommand("Buy Stocks");

    c.gridy = 4;
    sellButton = new JButton("Sell Stocks");
    panel.add(sellButton, c);
    sellButton.setActionCommand("Sell Stocks");

    c.gridy = 6;
    strategyButton = new JButton("Create a new investment strategy");
    panel.add(strategyButton, c);
    strategyButton.setActionCommand("Create a new investment strategy");

    c.gridy = 8;
    compositionButton = new JButton("View composition");
    panel.add(compositionButton, c);
    compositionButton.setActionCommand("View composition");

    c.gridy = 10;
    totalValueButton = new JButton("Total value of portfolio");
    panel.add(totalValueButton, c);
    totalValueButton.setActionCommand("Total value of portfolio");

    c.gridy = 12;
    costBasisButton = new JButton("Cost Basis of portfolio");
    panel.add(costBasisButton, c);
    costBasisButton.setActionCommand("Cost Basis of portfolio");

    c.gridy = 14;
    commissionValueButton = new JButton("Change Commission");
    panel.add(commissionValueButton, c);
    commissionValueButton.setActionCommand("Change Commission");

    c.gridy = 16;
    homeButton = new JButton("Back to Main Menu");
    panel.add(homeButton, c);
    homeButton.setActionCommand("homeButton");

    this.getContentPane().add(panel);
    this.pack();

    setVisible(false);
  }

  @Override
  public void addFeatures(FlexiblePortfolioFeatures features) {
    compositionButton.addActionListener(f -> features.examinePortfolio());
    totalValueButton.addActionListener(f -> features.totalValueDate());
    costBasisButton.addActionListener(f -> features.costBasisDate());
    commissionValueButton.addActionListener(f -> features.getCommission());
    buyButton.addActionListener(f -> features.purchaseStocks());
    sellButton.addActionListener(f -> features.sellShares());
    strategyButton.addActionListener(f -> features.investmentStrategy());
    homeButton.addActionListener(f -> features.backToHomeMenu());
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
    return;
  }
}
