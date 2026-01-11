package com.demo.pages;

import java.nio.file.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FilesPage extends BasePage {
    private final By fileInput = testId("file-input");
    private final By uploadAdvance = testId("upload-advance");
    private final By downloadCsv = testId("download-csv");
    private final By downloadPdf = testId("download-pdf");
    private final By downloadRetry = testId("download-retry");

    public FilesPage(WebDriver driver) {
        super(driver);
    }

    public void uploadFile(Path file) {
        get(fileInput).sendKeys(file.toAbsolutePath().toString());
    }

    public void advanceUpload() {
        click(uploadAdvance);
    }

    public void downloadCsv() {
        click(downloadCsv);
    }

    public void downloadPdf() {
        click(downloadPdf);
    }

    public void retryDownload() {
        click(downloadRetry);
    }
}
