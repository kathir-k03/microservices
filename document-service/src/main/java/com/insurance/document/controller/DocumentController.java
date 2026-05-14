package com.insurance.document.controller;

import com.insurance.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    // ✅ Upload API
    @PostMapping("/upload")
    public String upload(
            @RequestParam String claimId,
            @RequestParam MultipartFile file) {

        service.uploadDocument(claimId, file);
        return "Document uploaded successfully";
    }

    // ✅ Verify for Claim Service
    @GetMapping("/{claimId}/verify")
    public boolean verify(@PathVariable String claimId) {
        return service.verifyDocuments(claimId);
    }

    // ✅ Admin approves document
    @PutMapping("/{id}/approve")
    public String approve(@PathVariable Long id) {
        service.verifyDocument(id);
        return "Document verified";
    }
}