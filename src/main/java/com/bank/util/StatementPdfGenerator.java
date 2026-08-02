package com.bank.util;

import java.awt.Color;
import java.util.List;

import com.bank.entity.Transaction;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class StatementPdfGenerator {

    public static void generate(Document document, List<Transaction> list)
            throws Exception {

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.LIGHT_GRAY);

        cell.setPhrase(new Phrase("Date"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount"));
        table.addCell(cell);

        for (Transaction t : list) {

            table.addCell(t.getDate());
            table.addCell(t.getType());
            table.addCell(String.valueOf(t.getAmount()));
        }

        document.add(table);
    }
}