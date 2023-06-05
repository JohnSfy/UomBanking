package files;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class CreatePDFS {


    public CreatePDFS(int i) {


        if(i==1){   // TRANSACTION HISTORY PDF \\

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


                String pdfPath = desktopPath + "/transactions_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

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
                textCell.addElement(new Phrase("GIANNIS SFYRAKIS", titleFont));
                textCell.addElement(new Phrase("GR23794784367", contentFont));
                headerTable.addCell(textCell);


                document.add(headerTable);


                Paragraph title = new Paragraph("Transaction History", titleFont);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setSpacingAfter(20); // Add some spacing after the title
                document.add(title);


                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String[] headers = {"Transaction ID", "Date", "Description", "Amount"};
                String[][] data = {
                        {"000001", dateFormat.format(new Date()), "Payment from John Smith", "$100.00"},
                        {"000002", dateFormat.format(new Date()), "Refund to Jane Doe", "-$50.00"},
                        {"000003", dateFormat.format(new Date()), "Payment from Joe Brown", "$75.00"},
                        {"000004", dateFormat.format(new Date()), "Payment from Sarah Lee", "$200.00"}
                };
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

//

        }
        else if(i==2) // Transaction receipt
        {
            Document document = new Document();

            try {
                // ...

                // get the desktop directory
                String desktopPath = System.getProperty("user.home") + "/Desktop";

                // get current date and time
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                String formattedDateTime = currentDateTime.format(formatter);

                String pdfPath = desktopPath + "/receipt_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

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
                textCell.addElement(new Phrase("GIANNIS SFYRAKIS", titleFont));
                textCell.addElement(new Phrase("GR23794784367", contentFont));
                headerTable.addCell(textCell);

                document.add(headerTable);

                Paragraph title = new Paragraph("Transaction Receipt", titleFont);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setSpacingAfter(20); // Add some spacing after the title
                document.add(title);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String[] headers = {"Transaction ID", "Date", "Description", "Amount"};
                String[][] data = {
                        {"Transaction ID", "Date", "Description", "Amount"},
                        {"000001", dateFormat.format(new Date()), "Payment from John Smith", "$100.00"}
                };
                PdfPTable table = new PdfPTable(headers.length);
                table.setWidthPercentage(100f);
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, headerFont));
                    cell.setPadding(5);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Set the background color
                    table.addCell(cell);
                }

                for (String cell : data[0]) {
                    table.addCell(new Paragraph(cell, contentFont));
                }

                table.setSpacingAfter(0);
                document.add(table);

                document.close();

                System.out.println("PDF created successfully!");

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }

        }
        else if(i==3){

            Document document = new Document();

            try {
                // ...

                // get the desktop directory
                String desktopPath = System.getProperty("user.home") + "/Desktop";

                // get current date and time
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
                String formattedDateTime = currentDateTime.format(formatter);

                String pdfPath = desktopPath + "/receipt_" + formattedDateTime + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

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
                textCell.addElement(new Phrase("GIANNIS SFYRAKIS", titleFont));
                textCell.addElement(new Phrase("GR23794784367", contentFont));
                headerTable.addCell(textCell);

                document.add(headerTable);

                Paragraph title = new Paragraph("Transaction Receipt", titleFont);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setSpacingAfter(20); // Add some spacing after the title
                document.add(title);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String[] headers = {"Transaction ID", "Date", "Description", "Amount"};
                String[][] data = {
                        {"Transaction ID", "Date", "Description", "Amount"},
                        {"000001", dateFormat.format(new Date()), "Payment from John Smith", "$100.00"}
                };
                PdfPTable table = new PdfPTable(headers.length);
                table.setWidthPercentage(100f);
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, headerFont));
                    cell.setPadding(5);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Set the background color
                    table.addCell(cell);
                }

                for (String cell : data[0]) {
                    table.addCell(new Paragraph(cell, contentFont));
                }

                table.setSpacingAfter(0);
                document.add(table);

                document.close();

                System.out.println("PDF created successfully!");

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }

        }

    }
}
