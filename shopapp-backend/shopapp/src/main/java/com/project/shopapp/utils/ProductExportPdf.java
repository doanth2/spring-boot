package com.project.shopapp.utils;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.shopapp.entities.Product;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductExportPdf {
    // Khởi tạo contructor
    private List<Product> products;
    // Tạo pdf
    public  void exportPdf(HttpServletResponse response) throws IOException {
        // Đối tượng Document là nơi bạn định nghĩa cấu trúc của tài liệu PDF. Bạn sẽ thêm các phần tử như văn bản, hình ảnh, bảng, v.v., vào Document này để tạo nội dung cho tài liệu PDF.
        Document document = new Document(PageSize.A4);
        try {
            // PdfWriter xuất tài liệu PDF thông qua đối tượng response
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            // xét front cho tiêu đề
            Font fontHeaderR = FontFactory.getFont(FontFactory.TIMES_ITALIC, 8);
            //Đối tượng Paragraph trong iText được sử dụng để chứa văn bản trong tài liệu PDF
            Paragraph parag = new Paragraph("Fukuoka", fontHeaderR);
            // căn chỉnh phần chính giữa
            parag.setAlignment(Element.ALIGN_RIGHT);
            parag.setSpacingAfter(10);
            // Thêm nội dung sử dụng add function
            document.add(parag);

            Font fontHeaderRight = FontFactory.getFont(FontFactory.TIMES_ITALIC, 8);
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            // Tạo một đối tượng Paragraph với văn bản và font đã xác định
            Paragraph rightPara = new Paragraph(currentDateTime, fontHeaderRight);
            // Căn chỉnh văn bản về phía bên phải
            rightPara.setAlignment(Element.ALIGN_RIGHT);
            // Thêm một dòng trống vào tài liệu PDF (nếu bạn muốn)

            // Thêm đối tượng Paragraph được căn chỉnh về phải vào tài liệu PDF
            document.add(rightPara);
            document.add(Chunk.NEWLINE);
            // Add Content to PDF file ->
            // xét front cho tiêu đề
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            //Đối tượng Paragraph trong iText được sử dụng để chứa văn bản trong tài liệu PDF
            Paragraph para = new Paragraph("Product List", fontHeader);
            // căn chỉnh phần giữa file pdf
            para.setAlignment(Element.ALIGN_CENTER);
            // Thêm nội dung sử dụng add function
            document.add(para);
            document.add(Chunk.NEWLINE);
            //thêm một khoảng trống mới (newline) vào tài liệu PDF.
            // document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);
            // Add PDF Table Header ->
            // stream tạo các element ở header mỗi cột
            Stream.of("Product Id", "Product Name", "Product Description", "Product Quantity",  "Product Price", "CategoryId").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.ORANGE);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                // Thêm nội dung sử dụng add function
                table.addCell(header);
            });

            // Thêm danh sách Product vào PDF
            for (Product product : products) {
                // cell là tạo ô
                PdfPCell idProductCell = new PdfPCell(new Phrase(String.valueOf(product.getId())));
                idProductCell.setPaddingLeft(4);
                idProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idProductCell);

                PdfPCell nameProductCell = new PdfPCell(new Phrase(product.getName()));
                nameProductCell.setPaddingLeft(4);
                nameProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nameProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(nameProductCell);

                PdfPCell descriptionProductCell = new PdfPCell(new Phrase((product.getDescription())));
                descriptionProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                descriptionProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                descriptionProductCell.setPaddingRight(4);
                table.addCell(descriptionProductCell);

                PdfPCell priceProductCell = new PdfPCell(new Phrase(String.valueOf(product.getPrice())));
                priceProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                priceProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceProductCell.setPaddingRight(4);
                table.addCell(priceProductCell);

                PdfPCell quantityProductCell = new PdfPCell(new Phrase(String.valueOf(product.getQuantity())));
                quantityProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantityProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityProductCell.setPaddingRight(4);
                table.addCell(quantityProductCell);

                PdfPCell categoryIdCell = new PdfPCell(new Phrase(String.valueOf(product.getCategory().getId())));
                categoryIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                categoryIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                categoryIdCell.setPaddingRight(4);
                table.addCell(categoryIdCell);

            }
            document.add(table);
            document.close();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}

