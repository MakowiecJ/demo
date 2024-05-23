package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.example.demo.converters.AsposeConverter;
import com.example.demo.converters.Convertapi;
import com.example.demo.converters.Converter;
import com.example.demo.converters.FOConverter;
import com.example.demo.converters.LibreConverter;
import com.example.demo.converters.PdfOffice;
import com.example.demo.converters.SpireDocConverter;
import com.example.demo.converters.XWPFConverter;

public class PdfBattleMultiThread {

    public static void main(String[] args) {
        PdfBattleMultiThread pdfBattle = new PdfBattleMultiThread();
        FOConverter foConverter = new FOConverter();
        LibreConverter libreConverter = new LibreConverter();
        XWPFConverter xWPFConverter = new XWPFConverter();
        SpireDocConverter spireIveConverter = new SpireDocConverter();
        Convertapi convertapiConverter = new Convertapi();
        AsposeConverter asposeConverter = new AsposeConverter();
        PdfOffice pdfOffice = new PdfOffice();
        final int sampleSize = 7;

        long[] foTimes = pdfBattle.measureConverterTimes(foConverter, sampleSize);
        long[] libreTimes = pdfBattle.measureConverterTimes(libreConverter, sampleSize);
        libreConverter.stopOffice();
        long[] xwpfTimes = pdfBattle.measureConverterTimes(xWPFConverter, sampleSize);
        long[] spireTimes = pdfBattle.measureConverterTimes(spireIveConverter, sampleSize);
        long[] convertapiTimes = pdfBattle.measureConverterTimes(convertapiConverter, sampleSize);
        long[] asposeTimes = pdfBattle.measureConverterTimes(asposeConverter, sampleSize);
        long[] pdfOfficeTimes = pdfBattle.measureConverterTimes(pdfOffice, sampleSize);

        printResults(foConverter.getName(), foTimes);
        printResults(libreConverter.getName(), libreTimes);
        printResults(xWPFConverter.getName(), xwpfTimes);
        printResults(spireIveConverter.getName(), spireTimes);
        printResults(convertapiConverter.getName(), convertapiTimes);
        printResults(asposeConverter.getName(), asposeTimes);
        printResults(pdfOffice.getName(), pdfOfficeTimes);
    }

    private long[] measureConverterTimes(final Converter converter, final int sampleSize) {
        long[] times = new long[sampleSize];
        ExecutorService executorService = Executors.newFixedThreadPool(sampleSize); // Create a thread pool with a number of threads equal to sample size
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < sampleSize; i++) {
            final int index = i;
            Callable<Long> task = () -> {
                long startTime = System.currentTimeMillis();
                converter.convert("C:\\Workspace\\test\\docx4j-test\\src\\main\\resources\\docxs\\demo" + index + ".docx",
                        "C:\\Workspace\\test\\docx4j-test\\pdfbattle\\" + converter.getName() + index + ".pdf");
                long endTime = System.currentTimeMillis();
                return endTime - startTime;
            };
            futures.add(executorService.submit(task));
        }

        for (int i = 0; i < sampleSize; i++) {
            try {
                times[i] = futures.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown(); // Shut down the executor service
        return times;
    }

    private static void printResults(final String converterName, final long[] results) {
        for (int i = 0; i < results.length; i++) {
            System.out.println(converterName + i + ": " + results[i] + "ms");
        }
    }
}
