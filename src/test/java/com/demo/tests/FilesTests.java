package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.FilesPage;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilesTests extends BaseTest {

    @Test
    void uploadAndDownloadActions() {
        openPath("/files");
        FilesPage files = new FilesPage(driver);

        Path uploadFile = null;
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream("fixtures/sample.txt")) {
            assertNotNull(resource);
            uploadFile = Files.createTempFile("sample-fixture-", ".txt");
            Files.copy(resource, uploadFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            uploadFile.toFile().deleteOnExit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fixture file", e);
        }
        try {
            files.uploadFile(uploadFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load fixture file", e);
        }

        files.advanceUpload();
        files.downloadCsv();
        files.downloadPdf();
        files.retryDownload();
    }
}
