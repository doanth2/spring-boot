package com.project.shopapp.utils;
import com.project.shopapp.models.Product;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;
//@AllArgsConstructor
//@Data
public class ProductExportExcel {

    //Biến này được sử dụng để lưu trữ danh sách các
    // sản phẩm được đọc từ hoặc sẽ được ghi vào tệp Excel. List
    private List<Product> productListExcel;
    // Tạo 1 file excel định dạng file .xlsx
    private XSSFWorkbook productWorkBook;
    //Biến sử dụng tạo 1 sheet trong file excel
    private XSSFSheet productSheet;

    public ProductExportExcel(List<Product> productList) {
        this.productListExcel = productList;
        productWorkBook = new XSSFWorkbook();
    }

    private void writeHeader() {
        productSheet = productWorkBook.createSheet("Product Excel");

        Row row = productSheet.createRow(0);

        CellStyle style = productWorkBook.createCellStyle();
        XSSFFont font = productWorkBook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "id", style);
        createCell(row, 1, "Product Name", style);
        createCell(row, 2, "Product Name", style);
        createCell(row, 3, "Product Price", style);
        createCell(row, 4, "category id", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        productSheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;

        CellStyle style = productWorkBook.createCellStyle();
        XSSFFont font = productWorkBook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Product record : productListExcel) {
            Row row = productSheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getName(), style);
            createCell(row, columnCount++, record.getPrice(), style);
            createCell(row, columnCount++, record.getDescription(), style);
            createCell(row, columnCount++, record.getCategory().getId(), style);

        }
}
    public void generate(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        productWorkBook.write(outputStream);
        productWorkBook.close();

        outputStream.close();

    }
}

