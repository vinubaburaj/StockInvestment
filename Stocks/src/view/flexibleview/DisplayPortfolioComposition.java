package view.flexibleview;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.util.Formatter;

import java.util.HashMap;
import java.util.Map;

import controller.FlexiblePortfolioFeatures;
import view.ViewGUI;

/**
 * This is the class that implements display portfolio composition
 * view and calls controller for all actions performs in this screen.
 */
public class DisplayPortfolioComposition extends JFrame implements ViewGUI {
  private final JButton backButton;
  private final JPanel panel;
  private JLabel display;

  /**
   * This is the constructor for the display portfolio composition view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   * @param stocks  the stocks data to display.
   */
  public DisplayPortfolioComposition(String caption, HashMap<String, Double> stocks) {
    super(caption);

    setSize(5500, 10000);
    setLocation(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    display = new JLabel("Composition of this portfolio:");
    panel.add(display, c);

    int i = 2;

    for (Map.Entry<String, Double> element : stocks.entrySet()) {
      String stockName = element.getKey();
      Double shares = element.getValue();
      Formatter formatter = new Formatter();
      formatter.format("%.2f", shares);
      c.gridy = i;
      display = new JLabel(stockName + " : " + formatter.toString() + " stocks");
      panel.add(display, c);
      i++;
    }

    i++;

    c.gridy = i;
    backButton = new JButton("Go Back");
    panel.add(backButton, c);
    backButton.setActionCommand("Go Back");

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
    c.gridy = 2;
    display = new JLabel(output);
    panel.add(display, c);

    this.getContentPane().add(panel);
    pack();
  }

  @Override
  public void clearInputString() {
    return;
  }
}
