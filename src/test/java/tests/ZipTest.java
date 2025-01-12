package tests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipTest {

    private ClassLoader cl = ZipTest.class.getClassLoader();

    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }




}
