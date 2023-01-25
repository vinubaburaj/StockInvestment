package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

/**
 * The ReadFromAlphaVantage is an Utility class that can be used from anywhere in
 * the program to get data from the alphavantage api in the path provided.
 * The methods are kept public to allow access from all classes in the
 * project.
 */
public class ReadFromAlphaVantage extends AbsGetDataFromSrc {

  private static final String apiKey = "KFKR6IY1AUVCVWVV";
  private static URL url;

  /**
   * The ReadFromAlphaVantage constructor initializes the api url.
   */
  public ReadFromAlphaVantage(String stockSymbol, String timeSeries) {

    if ("TIME_SERIES_DAILY".equals(timeSeries)) {
      try {
        url = new URL("https://www.alphavantage"
                + ".co/query?function=TIME_SERIES_DAILY"
                + "&outputsize=full"
                + "&symbol"
                + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
      } catch (MalformedURLException e) {
        throw new RuntimeException("the alphavantage API has either changed or "
                + "no longer works");
      }
    }
  }

  /**
   * This method checks if the date is a holiday and returns next date if true.
   */
  public static LocalDate checkifDatePresent(LocalDate date) throws IOException {
    url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_DAILY"
            + "&outputsize=full"
            + "&symbol"
            + "=IBM&apikey=" + apiKey + "&datatype=csv");
    BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
    String line = "";
    LocalDate correctDate = LocalDate.parse("2300-01-01");
    while ((line = in.readLine()) != null) {
      String[] lineArray = line.split(",");
      if (UtilityClass.checkDateFormat(lineArray[0])) {
        LocalDate dateFromSrc = LocalDate.parse(lineArray[0]);
        if (dateFromSrc.compareTo(date) >= 0) {
          correctDate = dateFromSrc;
        } else {
          return correctDate;
        }
      }
    }
    return correctDate;
  }

  @Override
  public String[] getDataByDate(String date) throws IOException {
    BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
    return absGetDataByDate(in, date);
  }
}
