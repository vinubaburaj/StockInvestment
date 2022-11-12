package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface WorkWithFileTypes {

  void create(ArrayList<HashMap<String, String>> stockData);

  List<String[]> read();
}