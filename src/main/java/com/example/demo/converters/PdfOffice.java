package com.example.demo.converters;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.licensing.base.LicenseKey;
import com.itextpdf.pdfoffice.OfficeConverter;
import com.itextpdf.pdfoffice.OfficeDocumentConverterProperties;


public class PdfOffice implements Converter {
    @Override
    public String getName() {
        return "PdfOffice (itext)(payed)";
    }

    @Override
    public void convert(final String inputFilePath, final String outputFilePath) {
        try {
            LicenseKey.loadLicenseFile(new File("C:\\Workspace\\test\\demo\\src\\main\\resources\\itext_key.json"));

            File demoFile = new File(inputFilePath);
            File pdfOutFile = new File(outputFilePath);
            // converts pages between 1 to 8
            var properties = new OfficeDocumentConverterProperties();
            OfficeConverter.convertOfficeDocumentToPdf(demoFile, pdfOutFile, properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void convert(final InputStream inputStream, final OutputStream outputStream) {

    }
}
