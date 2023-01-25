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
 * This is the class that implements the create portfolio view.
 */
public class CreatePortfolioView extends JFrame implements ViewGUI {
  private final JButton createButton;
  private final JTextField portfolioName;
  private final JTextField creationDate;

  /**
   * This is the constructor for the create portfolio view. It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public CreatePortfolioView(String caption) {
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
    JLabel display = new JLabel("Enter name of portfolio you wish to create:");
    panel.add(display, c);

    c.gridx = 0;
    c.gridy = 1;
    portfolioName = new JTextField();
    panel.add(portfolioName, c);

    c.gridx = 0;
    c.gridy = 3;
    display = new JLabel("Enter creation date of portfolio in YYYY-MM-DD format:");
    panel.add(display, c);

    c.gridx = 0;
    c.gridy = 4;
    creationDate = new JTextField();
    panel.add(creationDate, c);

    c.gridx = 0;
    c.gridy = 6;
    createButton = new JButton("Create portfolio");
    panel.add(createButton, c);
    createButton.setActionCommand("createButton");

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
    createButton.addActionListener(f -> features.createPortfolioToModel(portfolioName.getText(),
            creationDate.getText()));
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
    portfolioName.setText("");
    creationDate.setText("");
  }
}
