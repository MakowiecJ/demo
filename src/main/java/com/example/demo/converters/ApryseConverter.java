package com.example.demo.converters;

import java.io.InputStream;
import java.io.OutputStream;

import com.pdftron.filters.Filter;
import com.pdftron.pdf.Convert;
import com.pdftron.pdf.PDFDoc;
import com.pdftron.pdf.PDFNet;
import com.pdftron.sdf.SDFDoc;

public class ApryseConverter implements Converter {


    static {
        PDFNet.initialize("demo:1716362602497:7fc2f6d703000000004352441dd3e824518e1ba22907f9a4474419ab88");
    }

    @Override
    public void convert(final InputStream inputStream, final OutputStream outputStream) {
    }

    @Override
    public void convert(final String inputFilePath, final String outputFilePath) {
        try {
            PDFDoc pdfdoc = new PDFDoc();
            Convert.officeToPdf(pdfdoc, inputFilePath, null);

            // save the result
            pdfdoc.save(outputFilePath, SDFDoc.SaveMode.INCREMENTAL, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Apryse Converter";
    }
}
