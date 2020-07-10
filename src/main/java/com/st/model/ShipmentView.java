package com.st.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class ShipmentView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		/*
		 * Sheet s= workbook.createSheet("ShipmentSheet"); Row r=s.createRow(0); Cell
		 * c=r.createCell(0); c.setCellValue("hello");
		 */
		//download and fileName
		response.addHeader("Content-Disposition", "attachment; filename= Shipment.xls");
		
		//read data from controller
		@SuppressWarnings("unchecked")
		List<Shipment> list=(List<Shipment>) model.get("obs");
		
		//create new sheet
		Sheet sheet= workbook.createSheet("Shipment");
		
		setHead(sheet);
		setBody(sheet,list);
	}

	private void setBody(Sheet sheet, List<Shipment> list) {
		int rowno=1;
		for(Shipment st:list) {
			Row row=sheet.createRow(rowno++);
			row.createCell(0).setCellValue(st.getSid());
			row.createCell(1).setCellValue(st.getShipmentMode());
			row.createCell(2).setCellValue(st.getShipmentCode());
			row.createCell(3).setCellValue(st.getEnableShipment());
			row.createCell(4).setCellValue(st.getShipmentGrade());
			row.createCell(5).setCellValue(st.getDescription());
		}
		
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");		
		row.createCell(1).setCellValue("MODE");		
		row.createCell(2).setCellValue("CODE");		
		row.createCell(3).setCellValue("ENABLE");		
		row.createCell(4).setCellValue("GRADE");		
		row.createCell(5).setCellValue("DESCRIPTION");		
	}

}
