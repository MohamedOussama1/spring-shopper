package uiass.eia.ecomapi.utils;

import java.io.*;
import java.util.Objects;

public class FileUtils {
    private static String readFromInputStream(InputStream inputStream) {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultStringBuilder.toString();
    }

    public static String readFile(String filePath){
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = null;
        try {
            File file = new File(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());
            inputStream = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return readFromInputStream(inputStream);
    }
}
