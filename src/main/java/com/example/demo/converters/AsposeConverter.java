package com.example.demo.converters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.itextpdf.xmp.impl.Base64;

public class AsposeConverter implements Converter {

    private static final String KEY_64 = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48TGljZW5zZT48RGF0YT48TGljZW5zZWRUbz5Db21hcmNoIFMuQS48L0xpY2Vuc2VkVG8+PEVtYWlsVG8+YXJ0dXIuYmFyYW5AY29tYXJjaC5wbDwvRW1haWxUbz48TGljZW5zZVR5cGU+RGV2ZWxvcGVyIE9FTTwvTGljZW5zZVR5cGU+PExpY2Vuc2VOb3RlPkxpbWl0ZWQgdG8gMSBkZXZlbG9wZXIsIHVubGltaXRlZCBwaHlzaWNhbCBsb2NhdGlvbnM8L0xpY2Vuc2VOb3RlPjxPcmRlcklEPjE2MDMwODA4MjUwNDwvT3JkZXJJRD48VXNlcklEPjgxODQyPC9Vc2VySUQ+PE9FTT5UaGlzIGlzIGEgcmVkaXN0cmlidXRhYmxlIGxpY2Vuc2U8L09FTT48UHJvZHVjdHM+PFByb2R1Y3Q+QXNwb3NlLlRvdGFsIGZvciBKYXZhPC9Qcm9kdWN0PjwvUHJvZHVjdHM+PEVkaXRpb25UeXBlPkVudGVycHJpc2U8L0VkaXRpb25UeXBlPjxTZXJpYWxOdW1iZXI+MmVhNmQwMjUtNzkxNS00NTAxLTgzZTEtM2ZiMzgzZDg5NGQzPC9TZXJpYWxOdW1iZXI+PFN1YnNjcmlwdGlvbkV4cGlyeT4yMDE3MDMwODwvU3Vic2NyaXB0aW9uRXhwaXJ5PjxMaWNlbnNlVmVyc2lvbj4zLjA8L0xpY2Vuc2VWZXJzaW9uPjxMaWNlbnNlSW5zdHJ1Y3Rpb25zPmh0dHA6Ly93d3cuYXNwb3NlLmNvbS9jb3Jwb3JhdGUvcHVyY2hhc2UvbGljZW5zZS1pbnN0cnVjdGlvbnMuYXNweDwvTGljZW5zZUluc3RydWN0aW9ucz48L0RhdGE+PFNpZ25hdHVyZT5tT3JPWVZZbHM5T3kxUXVKRGVTSnZzVmtQd0UwV3Qra3h6ZGtVb2ZzWXp5M2VadnIxbkQzMFhGUHliN3BLRll6STFXLzNoZCtUWi9YN211MUY4WkR4M3lpVDVxYVc0cTRwVXR5Y1hzeXd1SUVPdGJVclN2Nmw1aXZpMDZJZi9BZ2VVbFE4RUh0bGUybUVQZm8wM2NnamZqTHJ2WnAzMXpiT0poQmFVMW9GSEE9PC9TaWduYXR1cmU+PC9MaWNlbnNlPg==";

    static {
        var wordsLicense = new com.aspose.words.License();
        var lic = IOUtils.toInputStream(Base64.decode(KEY_64), Charset.defaultCharset());
        try {
            wordsLicense.setLicense(lic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void convert(final InputStream inputStream, final OutputStream outputStream) {
        try {
            Document document  = new Document(inputStream);
            document.save("aspose.pdf", SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void convert(final String inputFilePath, final String outputFilePath) {
        try {
            FileInputStream inputStream = new FileInputStream(inputFilePath);
            OutputStream outputStream = new FileOutputStream(outputFilePath);

            convert(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Aspose Converter";
    }
}
