package com.example.demo.converters;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;

public class Convertapi implements Converter {
    @Override
    public String getName() {
        return "Convertapi (cloud)(payed)";
    }

    @Override
    public void convert(final String inputFilePath, final String outputFilePath) {
        try {
//            FileInputStream inputStream = new FileInputStream(inputFilePath);
//            OutputStream outputStream = new FileOutputStream(outputFilePath);

            // Code snippet is using the ConvertAPI Java Client: https://github.com/ConvertAPI/convertapi-java

            Config.setDefaultSecret("YbpJDTk4hoQiFX6d");
            ConvertApi.convert("docx", "pdf",
                    new Param("File", Paths.get(inputFilePath))
            ).get().saveFile(Path.of(outputFilePath));
//                    .saveFilesSync(Paths.get(outputFilePath));

//            inputStream.close();
//            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void convert(final InputStream inputStream, final OutputStream outputStream) {

    }
}
