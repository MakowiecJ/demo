package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converters.ApryseConverter;
import com.example.demo.converters.AsposeConverter;
import com.example.demo.converters.Convertapi;
import com.example.demo.converters.FOConverter;
import com.example.demo.converters.LibreConverter;
import com.example.demo.converters.PdfOffice;
import com.example.demo.converters.SpireDocConverter;
import com.example.demo.converters.XWPFConverter;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/converters")
public class Controller {

//    private static final String INPUT_FILE = "C:\\Workspace\\test\\demo\\src\\main\\resources\\demo0.docx";
    private static final String INPUT_FILE = "C:\\Workspace\\test\\demo\\src\\main\\resources\\demo0.docx";

    private final FOConverter foConverter = new FOConverter();
    private final LibreConverter libreConverter = new LibreConverter();
    private final SpireDocConverter spireDocConverter = new SpireDocConverter();
    private final XWPFConverter xwpfConverter = new XWPFConverter();
    private final Convertapi convertapi = new Convertapi();
    private final AsposeConverter asposeConverter = new AsposeConverter();
    private final PdfOffice pdfOfficeConverter = new PdfOffice();
    private final ApryseConverter apryseConverter = new ApryseConverter();


    @PostMapping("/fo")
    public String convertFo() {
        long startTime = System.currentTimeMillis();
        foConverter.convert(INPUT_FILE, "foOutput.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Apache FOP: " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/libre")
    public String convertLibre() {
        long startTime = System.currentTimeMillis();
        libreConverter.convert(INPUT_FILE, "libreOutput.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "JodConverter (Libre Office): " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/xwpf")
    public String convertXWPF() {
        long startTime = System.currentTimeMillis();
        xwpfConverter.convert(INPUT_FILE, "xwpfOutput.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Apache XWPF: " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/spiredoc")
    public String convertSpireIce() {
        long startTime = System.currentTimeMillis();
        spireDocConverter.convert(INPUT_FILE, "spiredoc.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Spire.Doc (paid): " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/convertapi")
    public String convertConverapi() {
        long startTime = System.currentTimeMillis();
        convertapi.convert(INPUT_FILE, "convertapiOutput.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Convertapi (paid, cloud): " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/aspose")
    public String convertAspose() {
        long startTime = System.currentTimeMillis();
        asposeConverter.convert(INPUT_FILE, "aspose.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Aspose (paid): " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/apryse")
    public String convertWithApryse() {
        long startTime = System.currentTimeMillis();
        apryseConverter.convert(INPUT_FILE, "apryse.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Apryse PDFTron (paid): " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/pdfoffice")
    public String convertPdfOffice() {
        long startTime = System.currentTimeMillis();
        pdfOfficeConverter.convert(INPUT_FILE, "pdfoffice.pdf");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        var response = "Itext PdfOffice (paid): " + duration + "ms";
        System.out.println(response);
        return response;
    }

    @PostMapping("/convertLibre/shutdown")
    public Void shutDownLibre() {
        libreConverter.stopOffice();
        return null;
    }

    @PostMapping("/convertLibre/start")
    public Void startLibre() {
        libreConverter.startOffice();
        return null;
    }



}
