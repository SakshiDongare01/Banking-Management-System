package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.entity.Transaction;
import com.bank.repository.TransactionRepository;
import com.bank.util.StatementPdfGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PdfController {

    @Autowired
    private TransactionRepository repository;

    @GetMapping("/download")
    public void downloadPdf(@RequestParam Long accountNo,
                            HttpServletResponse response)
            throws Exception {

        response.setContentType("application/pdf");

        response.setHeader("Content-Disposition",
                "attachment; filename=statement.pdf");

        List<Transaction> list =
                repository.findByAccountNo(accountNo);

        Document document = new Document();

        PdfWriter.getInstance(document,
                response.getOutputStream());

        document.open();

        document.add(new Paragraph("Bank Statement"));

        document.add(new Paragraph("Account No : "
                + accountNo));

        document.add(new Paragraph(" "));

        StatementPdfGenerator.generate(document, list);

        document.close();
    }
}