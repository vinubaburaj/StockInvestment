package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.FlexiblePortfolioFeatures;

/**
 * This is the class that implements the main Menu of the view GUI.
 * and calls controller for all actions performed in this screen.
 */
public class JFrameSwingView extends JFrame implements ViewGUI {
  private final JButton exitButton;
  private final JButton newPortfolioButton;
  private final JButton existingPortfolioButton;

  /**
   * This is the constructor for the Main Menu view.
   * It sets up the features of the view.
   *
   * @param caption the caption for the view.
   */
  public JFrameSwingView(String caption) {
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
    JLabel display = new JLabel("Choose an operation you want to perform:");
    panel.add(display, c);

    c.gridy = 2;
    newPortfolioButton = new JButton("Create a new Portfolio");
    panel.add(newPortfolioButton, c);
    newPortfolioButton.setActionCommand("createNewPortfolio");

    c.gridy = 4;
    existingPortfolioButton = new JButton("Work with an existing Portfolio");
    panel.add(existingPortfolioButton, c);
    existingPortfolioButton.setActionCommand("existingPortfolio");

    c.gridy = 6;
    exitButton = new JButton("Exit");
    panel.add(exitButton, c);
    exitButton.setActionCommand("exitButton");

    this.getContentPane().add(panel);
    pack();
    setVisible(true);

  }

  @Override
  public void addFeatures(FlexiblePortfolioFeatures features) {
    newPortfolioButton.addActionListener(f -> features.createPortfolio());
    existingPortfolioButton.addActionListener(f -> features.existingPortfolios());
    exitButton.addActionListener(f -> features.exitProgram());

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
