package com.bethesda.kcic.metadata.controller;

import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.metadata.service.MetaDataService;
import com.bethesda.kcic.util.BaseMap;
import com.bethesda.kcic.util.DateTimeUtil;
import com.bethesda.kcic.util.WebUtil;
import lombok.extern.java.Log;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log
@RequestMapping("/metadata")
public class MetadataController {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String TAG_USER = "METADATA";

    @Value("${excel.fileDownPath.window}")
    private String fileDownPathWin;

    @Value("${excel.fileDownPath.linux}")
    private String fileDownPathLinux;

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(value = "/sub01")
    public ModelAndView goSub01(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/metadata/sub_0302.html");
        return mav;
    }

    @RequestMapping(value = "/sub02")
    public ModelAndView goSub02(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("metaDataDomainList", metaDataService.getMetaDataDomainList());
        mav.setViewName("content/metadata/sub_0302.html");
        return mav;
    }

    @RequestMapping(value = "/sub03")
    public ModelAndView goSub03(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/metadata/sub_0303_01.html");
        return mav;
    }

    @PostMapping("/metaDataDownloadExcel")
    public void goMetaDataDownloadExcel(MetaDataVO vo) throws Exception {
        String metaPath = "meta";
        String fileDownPath = OS.contains("win") ? fileDownPathWin : fileDownPathLinux;
        String filePath = new StringBuffer()
                .append(fileDownPath).append(File.separator)
                .append(metaPath).toString();

        File file = null;
        FileOutputStream outFile = null;

        String fileNm = "metadata_" + DateTimeUtil.getNowDateHb("_") + ".xlsx";
        SXSSFWorkbook wb = null;
        try {
            // keep 100 rows in memory, exceeding rows will be flushed to disk
            wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);

            // temp files will be gzipped
            wb.setCompressTempFiles(true);

            Sheet sheet = wb.createSheet();     // Sheet
            Row row = null;                     // Row
            Cell cell = null;                   // Cell

            List<Integer> width = new ArrayList<Integer>();
            for (int i = 0; i < 8; i++) {
                width.add(20);
            }

            for (int i = 0; i < width.size(); i++) {
                sheet.setColumnWidth(i, width.get(i) * 256);
            }

            int rowIdx = 0;

            // Header 첫줄
            CellStyle headerStyle = wb.createCellStyle();
            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            headerStyle.setWrapText(true);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headerStyle.setBorderTop(CellStyle.BORDER_THIN);
            headerStyle.setBorderRight(CellStyle.BORDER_THIN);
            headerStyle.setBorderBottom(CellStyle.BORDER_THIN);

            List<String> header = new ArrayList<String>();
            header.add("도메인");
            header.add("변수명");
            header.add("항목명");
            header.add("데이터유형");
            header.add("데이터타입");
            header.add("코드설명");
            header.add("길이");
            header.add("단위");

            row = sheet.createRow(rowIdx++);
            for (int i = 0; i < header.size(); i++) {
                cell = row.createCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(header.get(i));
                cell.setCellStyle(headerStyle);
            }

            // Data Style 지정
            CellStyle dataStyle = wb.createCellStyle();
            dataStyle.setWrapText(true);
            dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
            dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            dataStyle.setBorderTop(CellStyle.BORDER_DASHED);
            dataStyle.setBorderRight(CellStyle.BORDER_DASHED);
            dataStyle.setBorderBottom(CellStyle.BORDER_DASHED);

            //취소선
            CellStyle soStyle = wb.createCellStyle();
            soStyle.cloneStyleFrom(dataStyle);
            Font soFont = wb.createFont();
            soFont.setStrikeout(true);
            soStyle.setFont(soFont);

            List<String> dataKeys = new ArrayList<String>();
            dataKeys.add("m_domain");
            dataKeys.add("m_item_name");
            dataKeys.add("m_item_label");
            dataKeys.add("m_data_type");
            dataKeys.add("m_input_type");
            dataKeys.add("m_item_codelist");
            dataKeys.add("m_item_length");
            dataKeys.add("m_measurement_unit");

            if (metaDataService.getMetaDataCnt(vo) > 100000) {
                row = sheet.createRow(rowIdx++);
                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue("검색된 데이타가 10만건이 넘었습니다. 검색 범위를 재설정하시거나 관리자에게 문의하세요.");
                cell.setCellStyle(dataStyle);
            } else {
                List<BaseMap> metaDataList = metaDataService.getMetaDataExcelList(vo);
                for (BaseMap excelRowData : metaDataList) {
                    row = sheet.createRow(rowIdx++);
                    for (int i = 0; i < dataKeys.size(); i++) {
                        cell = row.createCell(i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(excelRowData.getString(dataKeys.get(i)));
                        cell.setCellStyle(dataStyle);
                    }
                }
            }

            File Folder = new File(filePath);
            if (!Folder.exists()) {
                try{
                    Folder.mkdirs();
                    log.info(TAG_USER + " : " +  "Create Folder Success");
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            } else {
                log.info(TAG_USER + " : " +  "The folder already exists");
            }

            String tempfile = new StringBuffer()
                    .append(filePath).append(File.separator)
                    .append(DateTimeUtil.getNowDate()).toString();

            outFile = new FileOutputStream(tempfile);
            file = new File(tempfile);
            wb.write(outFile);
            wb.dispose();

            //파일다운로드.
            WebUtil.downFile(file, fileNm);

        } catch (Exception e) {
            if (outFile != null) try {
                outFile.close();
            } catch (Exception ignore) {
            }
        } finally {
            try {
                wb.close();
                wb.dispose();
                if (outFile != null) try {
                    outFile.close();
                } catch (Exception ignore) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
