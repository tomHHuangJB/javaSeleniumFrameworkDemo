package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.FilesPage;
import java.net.URL;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilesTests extends BaseTest {

    @Test
    void uploadAndDownloadActions() {
        openPath("/files");
        FilesPage files = new FilesPage(driver);

        URL resource = getClass().getClassLoader().getResource("fixtures/sample.txt");
        assertNotNull(resource);
        try {
            files.uploadFile(Path.of(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fixture file", e);
        }

        files.advanceUpload();
        files.downloadCsv();
        files.downloadPdf();
        files.retryDownload();
    }
}
