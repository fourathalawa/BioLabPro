package tn.esprit.biol.service;

import java.util.Map;

public interface PdfGenerateIService {
    void generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName);
}