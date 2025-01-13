package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ZipTest {

    private ClassLoader cl = ZipTest.class.getClassLoader();

    @Test
    void zipTestPdf() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/test.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("test.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().contains(".pdf")) {
                PDF pdf = new PDF(is);
                assertThat(pdf.text).contains ("Пример файла PDF ");
            }
        }
    }

    @Test
    void zipTestXlsx() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/test.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("test.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().contains(".xls")) {
                XLS xls = new XLS(is);
                String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                Assertions.assertTrue(actualValue.contains("Внешний идентификатор для импорта"));
            }
        }
    }

    @Test
    void zipTestCsv() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/test.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("test.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().contains(".csv")) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(is));
                List<String[]> data = csvReader.readAll();
                Assertions.assertEquals(5, data.size());
            }
        }
    }

}

