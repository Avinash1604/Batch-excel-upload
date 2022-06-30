package com.test.batch;

import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConsoleItemReader<LeadFile> implements ItemReader<LeadFile> {

    private List<LeadFile> leadFiles;
    private int count = 0;
    XSSFSheet xssfSheet;
    @Override
    public LeadFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (leadFiles == null ||  leadFiles.isEmpty()){
           leadFiles = this.getExcelData();
        }
        if (count >= leadFiles.size()) {
            return null;
        }
        return leadFiles.get(count++);
    }


//    private XSSFSheet getSheetDetails() throws IOException {
//        return readExcelData().getSheet("leads");
//    }
//
//    private XSSFWorkbook readExcelData() throws IOException {
//        ClassPathResource resource = new ClassPathResource("Patient-Demographics.xlsx");
//        InputStream inp =  resource.getInputStream();
//        XSSFWorkbook workbook = new XSSFWorkbook(inp);
//        return workbook;
//    }

    private InputStream getStream() throws IOException {
        ClassPathResource resource = new ClassPathResource("Patient-Demographics.xlsx");
        return  resource.getInputStream();
    }

    private List<LeadFile> getExcelData() throws IOException {
        return (List<LeadFile>) Poiji.fromExcel(this.getStream(), PoijiExcelType.XLSX, com.test.model.LeadFile.class);
    }

}