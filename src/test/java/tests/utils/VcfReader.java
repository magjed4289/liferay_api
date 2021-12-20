package tests.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VcfReader {
    public static List<String> listFromVcfFile(String filePath){
        List<String> emailsList=new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    new File(filePath)));

            String line = null;
            while (true) {
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (line.indexOf("@") > 0) {
                    line = line.replaceAll(";", "");
                    if (line.indexOf(":") > 0) {
                        line = line.substring(line.indexOf(":") + 1);
                        emailsList.add(line);
                    }
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        java.util.Collections.sort(emailsList);
        return emailsList;
    }
}
