package simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleQuery {
    public List<Map<String,String>> processData() throws IOException {
        List<Map<String,String>> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/data/person.csv")))) {
            String[] headers = br.readLine().split(";");

            String row;

            long lines = 0;
            do {
                if (lines >= 10) {
                    break;
                }

                row = br.readLine();

                System.out.println("Row:"+row);

                if (row != null && !"".equals(row.trim())) {
                    String[] values = row.split(";");

                    long id = Long.parseLong(values[0]);

                    if (id > 20) {
                        Map<String, String> resultMap = new HashMap<>();

                        for (int i=0;i<headers.length;i++) {
                            resultMap.put(headers[i], values[i]);
                        }

                        result.add(resultMap);

                        lines++;
                    }
                }
            } while (row != null);
        }

        return result;
    }
}
