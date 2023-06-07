package files;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Account;
import model.Client;
import model.Deposit;
import model.Transactions;
import org.example.AccountDB;
import org.example.ClientDB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransReceipt {

    public TransReceipt(Account acc,Transactions transaction) {

        Document document = new Document();


        try {
            // ...

            // get the desktop directory
            String desktopPath = System.getProperty("user.home") + "/Desktop";

            // get current date and time
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
            //String formattedDateTime = currentDateTime.format(formatter).replace(":", "");
            String formattedDateTime = currentDateTime.format(formatter);

            // check the class that calling this class
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String callingClassName = stackTrace[2].getClassName();
            System.out.println("The class calling MyClass is: " + callingClassName);

            if (callingClassName.contains("Deposit")) {

                String pdfPath = desktopPath + "/DepositReceipt_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

            } else if (callingClassName.contains("Payment")) {

                String pdfPath = desktopPath + "/PaymentReceipt_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

            } else if (callingClassName.contains("Transfer")) {

                String pdfPath = desktopPath + "/TransferReceipt_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

            } else {

                String pdfPath = desktopPath + "/WithdrawReceipt_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

            }


            // open the document
            document.open();
            // Set up font styles
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.DARK_GRAY);
            Font contentFont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);


            // Photo
            String imagePath = "src/main/java/files/LOGO.png";
            Image image = Image.getInstance(imagePath);
            image.scaleToFit(200, 200);
            image.setAlignment(Image.ALIGN_CENTER);
            document.add(image);


            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100f);


            PdfPCell textCell = new PdfPCell();
            textCell.setBorder(Rectangle.NO_BORDER);
            textCell.setVerticalAlignment(Element.ALIGN_TOP);

            Client client = ClientDB.fetchClient(acc.getClient());

            textCell.addElement(new Phrase(client.getFirstName() + " " + client.getLastName(), titleFont));
            textCell.addElement(new Phrase(acc.getIBAN(), contentFont));
            headerTable.addCell(textCell);


            document.add(headerTable);


            Paragraph title = new Paragraph(transaction.getDescription() + " Receipt", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20); // Add some spacing after the title
            document.add(title);


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] headers = {"Transaction ID", "Date", "Description", "Amount"};


            String[][] data;
            if (transaction.getDescription().equals("Deposit")) {


                data = new String[][]{
                        {transaction.getID(), transaction.getDate(),transaction.getDescription(), "+" + String.valueOf(transaction.getAmount())},
                };
            } else {

                data = new String[][]{
                        {transaction.getID(), transaction.getDate(), transaction.getDescription(),"-" + String.valueOf(transaction.getAmount()) },
                };

            }

            PdfPTable table = new PdfPTable(headers.length);
            table.setWidthPercentage(100f);
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header, headerFont));
                cell.setPadding(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Set the background color
                table.addCell(cell);
            }

            for (String[] row : data) {
                for (String cell : row) {
                    table.addCell(new Paragraph(cell, contentFont));
                }
            }
            table.setSpacingAfter(20);
            document.add(table);


            document.close();

            System.out.println("PDF created successfully!");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


    }
}
