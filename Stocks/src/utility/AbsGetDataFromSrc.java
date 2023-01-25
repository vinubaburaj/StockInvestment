package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

/**
 * This is the abstract class that contains all the common functionalities to get data from
 * any source.
 */
abstract class AbsGetDataFromSrc implements GetDataFromSource {

  /**
   * This is the function to get data from source by date.
   *
   * @param reader     the bufferedreader.
   * @param dateString the date to search by.
   */
  String[] absGetDataByDate(BufferedReader reader, String dateString) throws IOException {
    String line = "";
    LocalDate dateCur = LocalDate.parse(dateString);

    while ((line = reader.readLine()) != null) {
      String[] lineArray = line.split(",");
      if (UtilityClass.checkDateFormat(lineArray[0])) {
        LocalDate dateFromSrc = LocalDate.parse(lineArray[0]);
        if (dateFromSrc.compareTo(dateCur) <= 0) {
          return lineArray;
        }
      }
    }
    return new String[0];
  }
}
