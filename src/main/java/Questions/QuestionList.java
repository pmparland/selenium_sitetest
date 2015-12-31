package Questions;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by patrickmcparland on 29/12/2015.
 */

/**
 * Defines a list of questions to be applied
 */
public class QuestionList {
    static final Logger logger = Logger.getLogger(QuestionList.class);

    public List <Question> questions = new ArrayList<>();;

    /**
     * Create a question list from an Excel file
     * @param excelFile
     *       the name and path of the excel file in a string
     * @return QuestionList
     *      the list of questions in the file
     */
    public QuestionList(String excelFile){
        try {
            questions = readQuestionDataFromExcelFile(excelFile);
        }
        catch (IOException e) { logger.error(e.getMessage()); }

    }


    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }

    private List<Question> readQuestionDataFromExcelFile(String filename) throws IOException {
        List<Question> listQuestions = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(filename));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        DataFormatter df = new DataFormatter(); //Used to store excel values as they appear

        //Ignore header first row
        Row first = iterator.next();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Question aQuestion = new Question();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 1:
                        aQuestion.setLocatorType(df.formatCellValue(nextCell));
                        break;
                    case 2:
                        aQuestion.setLocatorValue(df.formatCellValue(nextCell));
                        break;
                    case 3:
                        aQuestion.setInputType(df.formatCellValue(nextCell));
                        break;
                    case 4:
                        aQuestion.setInputValue(df.formatCellValue(nextCell));
                        break;
                }
            }
            listQuestions.add(aQuestion);
        }

        workbook.close();
        inputStream.close();

        return listQuestions;
    }



}
