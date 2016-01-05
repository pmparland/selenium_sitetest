package com.seopa.tests.automated.questions;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Defines a list of questions to be applied.
 *
 * Created by patrickmcparland on 29/12/2015.
 */
public class ExcelQuestionList {

    @Getter
    private List <Question> questions = new ArrayList<>();

    /**
     * Create a question list from an Excel file.
     *
     * @param   excelFile The name and path of the excel file in a string.
     *
     * @return  ExcelQuestionList The list of questions in the file.
     */
    public ExcelQuestionList(String excelFile) {
        try {
            questions = readQuestionDataFromExcelFile(excelFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Reads the question data from the file.
     *
     * @param   filename The fully qualified name of the file to read.
     *
     * @return  A non-null reference to the list of Questions that has been read from the file has been
     *          returned.
     *
     * @throws  IOException Raised if there is an issue reading from the file.
     */
    private List<Question> readQuestionDataFromExcelFile(String filename) throws IOException {
        List<Question> listQuestions = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(filename));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter(); // Used to store excel values as they appear

        // Ignore header first row
        Row first = iterator.next();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Question aQuestion = new Question();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                String value = df.formatCellValue(nextCell);
                int columnIndex = nextCell.getColumnIndex();
                //Ignore column zero
                switch (columnIndex) {
                    case 1:
                        aQuestion.setLocatorType(value);
                        break;
                    case 2:
                        aQuestion.setLocatorValue(value);
                        break;
                    case 3:
                        aQuestion.setInputType(value);
                        break;
                    case 4:
                        aQuestion.setInputValue(value);
                        break;
                    case 5:
                        aQuestion.setExpectedResultOperator(df.formatCellValue(nextCell));
                        break;
                    case 6:
                        aQuestion.setExpectedResultValue(df.formatCellValue(nextCell));
                        break;
                }
            }
            listQuestions.add(aQuestion);
        }

        workbook.close();
        inputStream.close();

        return listQuestions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
