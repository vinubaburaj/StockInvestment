package utility;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import enums.stockTicker;

/**
 * This class contains all data validation functions that are used by the Stocks project.
 * The methods are kept public to allow access from all classes in the project.
 */
public final class UtilityClass {

  private static final LocalDate dateToday = LocalDate.parse("2022-11-02");
  private static final LocalDate lastHistoricDate = LocalDate.parse("2022-06-13");

  /**
   * This function checks if a string is an integer or a quitter string (A quitter
   * string is entered by the user when he does not want to proceed).
   *
   * @param num     the number to check
   * @param quitter the quitter string
   */
  public static int checkIfInteger(String num, String quitter) {
    try {
      Integer.parseInt(num);
    } catch (Exception e) {
      if (num.equalsIgnoreCase(quitter)) {
        return 2;
      }
      return 0;
    }
    return 1;
  }

  /**
   * This function checks if a string is a valid ticker or a quitter string (A quitter
   * string is entered by the user when he does not want to proceed).
   *
   * @param s       the ticker to check
   * @param quitter the quitter string
   */
  public static int checkValidStock(String s, String quitter) {
    try {
      stockTicker.valueOf(s);
    } catch (Exception e) {
      if (s.equalsIgnoreCase(quitter)) {
        return 2;
      } else {
        return 0;
      }
    }
    return 1;
  }

  /**
   * This function checks if a portfolio file exists in the user portfolio folder.
   *
   * @param portfolioName the file to check
   */
  public static boolean checkFileExists(String portfolioName) {
    File filePath = new File("src/allUserPortfolios/user1_portfolios/"
            + portfolioName + ".xml");
    return filePath.exists();
  }

  /**
   * This function checks if the date provided is a valid date and falls in our list of dates
   * that are allowed.
   *
   * @param date the date to check
   */
  public static boolean checkDateValidity(String date) {
    String pathToFile = "src/dates/dates.csv";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    try {
      format.parse(date);
    } catch (ParseException e) {
      return false;
    }
    LocalDate dateCur = LocalDate.parse(date);
    int compareDateToToday = dateCur.compareTo(dateToday);
    int compareDateToLast = dateCur.compareTo(lastHistoricDate);

    if (compareDateToLast > 0 && compareDateToToday <= 0) {
      ReadCSVs r = new ReadCSVs(pathToFile);
      String[] data = r.getDataByDate(date);

      return data.length > 0;
    }
    return false;
  }

  /**
   * This function checks if the String number is an integer that falls
   * inside the lower and higher limit (used in cases where the user enters
   * the serial number of the option he wants to choose).
   *
   * @param num         the number string to check
   * @param lowerLimit  the lower limit to check from
   * @param higherLimit the higher limit to check from
   */
  public static boolean checkValidNumberOption(String num, int lowerLimit, int higherLimit) {
    int n;
    try {
      n = Integer.parseInt(num);
    } catch (Exception e) {
      return false;
    }
    return n >= lowerLimit && n <= higherLimit;
  }
}
