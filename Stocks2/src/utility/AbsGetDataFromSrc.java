package utility;

import java.io.BufferedReader;
import java.io.IOException;

abstract class AbsGetDataFromSrc implements GetDataFromSource{

  String[] absGetDataByDate(BufferedReader reader, String date) throws IOException {
    System.out.println("I'm at abs getdata");
    String line = "";
    while ((line = reader.readLine()) != null) {
      String[] lineArray = line.split(",");
      if (lineArray[0].equals(date)) {
        System.out.println(lineArray[0]);

        return lineArray;
      }
    }
    return new String[0];
  }
}
