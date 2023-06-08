package files;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Account;
import model.Client;
import model.Loan;
import model.Transactions;
import org.example.AccountDB;
import org.example.ClientDB;
import org.example.LoanDB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoanHistory {

    public LoanHistory(Account account, ArrayList<Loan> loans) {


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


            String pdfPath = desktopPath + "/LoanHistory_" + formattedDateTime + ".pdf";
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

            Client client = ClientDB.fetchClient(account.getClient());


            textCell.addElement(new Phrase(client.getFirstName()+" "+ client.getLastName(), titleFont));

            textCell.addElement(new Phrase(account.getIBAN(), contentFont));
            headerTable.addCell(textCell);


            document.add(headerTable);


            Paragraph title = new Paragraph("Loan History", titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20); // Add some spacing after the title
            document.add(title);


            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] headers = {"Loan ID", "ExpireDate", "Description", "Amount","Doses"};
            String[][] data = new String[loans.size()][5];
            for (int i = 0; i < loans.size(); i++) {
                Loan loan = loans.get(i);
                data[i][0] = loan.getId();
                data[i][1] = loan.getDateExp();
                data[i][2] = loan.getDescription();

                if(loan.getLoanAmount()==0.0){
                    data[i][3] = String.valueOf(loan.getLoanAmount()+" (Paid)");
                }
                else {
                    data[i][3] = String.valueOf(loan.getLoanAmount());
                }

                data[i][4] = String.valueOf(loan.getDoses());

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
