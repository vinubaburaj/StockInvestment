package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ReadCSVs is an Utility class that can be used from anywhere in the program to read any CSV
 * in the path provided. The methods are kept public to allow access from all classes in the
 * project.
 */
public class ReadCSVs extends AbsGetDataFromSrc {
  private final String path;

  /**
   * The ReadCSVs constructor initializes the path on which it needs to read the file.
   *
   * @param path the path to read the file from
   */
  public ReadCSVs(String path) {
    this.path = path;
  }

  /**
   * The getDataByDate function accepts a date String and checks if there is any data
   * against that particular date, and returns a string array of the data or empty array
   * if the data was not found.
   *
   * @param date the date to get data from
   */
  public String[] getDataByDate(String date) throws IOException {
    BufferedReader reader = null;
    reader = new BufferedReader(new FileReader(path));
    return absGetDataByDate(reader, date);
  }
}
