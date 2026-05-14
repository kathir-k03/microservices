package com.insurance.document.service;

import com.insurance.document.entity.Document;
import com.insurance.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;

    private static final String UPLOAD_DIR = "uploads/";

    public void uploadDocument(String claimId, MultipartFile file) {

        try {
            // ✅ Ensure directory exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) dir.mkdirs();

            String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename();

            file.transferTo(new File(filePath));

            Document doc = Document.builder()
                    .claimId(claimId)
                    .fileName(file.getOriginalFilename())
                    .filePath(filePath)
                    .contentType(file.getContentType())
                    .verified(false) // default
                    .build();

            repository.save(doc);

        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    // ✅ Verify documents (Java 8 Stream)
    public boolean verifyDocuments(String claimId) {

        List<Document> docs = repository.findByClaimId(claimId);

        if (docs.isEmpty()) return false;

        return docs.stream()
                .allMatch(Document::isVerified);
    }

    // ✅ Admin approval
    public void verifyDocument(Long id) {
        Document doc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        doc.setVerified(true);
        repository.save(doc);
    }
}
