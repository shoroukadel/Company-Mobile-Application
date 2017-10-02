package commyc.example.blu_ray91111.timslinesoluation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Blu-Ray on 08/03/2017.
 */

public class readfile {
    public  String readfile(String filePath)throws IOException{


            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = null;

            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } finally {
                reader.close();
            }

            String returnStr = stringBuilder.toString();
            return returnStr;

    }
}
