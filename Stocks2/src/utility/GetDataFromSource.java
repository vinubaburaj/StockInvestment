package utility;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GetDataFromSource {

  String[] getDataByDate(String date) throws IOException;
}
