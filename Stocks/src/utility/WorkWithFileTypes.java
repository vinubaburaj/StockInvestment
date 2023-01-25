package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This interface represents the input/output operations that the program carries
 * out on different file types.
 */
public interface WorkWithFileTypes {

  /**
   * This function is used to create a any portfolio.
   */
  void create(ArrayList<HashMap<String, String>> stockData, String creationDate);

  /**
   * This function is used to read data from a portfolio.
   */
  List<HashMap<String, String>> read();

  /**
   * This function is used to read the creation date of the file.
   */
  String getFileCreationDate();

  /**
   * This function is used to update data on a portfolio file.
   */
  boolean update(List<HashMap<String, String>> stocks);

  /**
   * This function reads the dca.
   */
  HashMap<String, String> readDCA();

  /**
   * This function reads the dca start date.
   */
  String readDCAStart();

  /**
   * This function reads the dca end date.
   */
  String readDCAEnd();

  /**
   * This function reads the last updates date.
   */
  String readLastUpdated();

  /**
   * This function reads the DCA frequency.
   */
  String readDCAFreq();

  /**
   * This function reads if the DCA updation is finished.
   */
  String readFinished();

  /**
   * This function sets the dca last updated date.
   *
   * @param date the last updated date to set.
   */
  void setDCALastUpdated(String date);

  /**
   * This function sets the finished.
   *
   * @param finished boolean to indicate if the updation is finished.
   */
  void setFinished(boolean finished);

  /**
   * This function creates a DCA strategy for the current portfolio.
   *
   * @param total       the total amount that the user wants to invest.
   * @param proportions the proportions for each stock.
   * @param start       the start date
   * @param end         the end date for the strategy.
   * @param days        the frequency in days.
   */
  void createDCAFile(int total, HashMap<String, Integer> proportions, String start,
                     String end, int days);
}
