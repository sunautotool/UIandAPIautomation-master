package Tools_Automation.UIandAPIautomation.UI.InputDataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputDataProviderFromExcel {

	private transient Object[][] data;
    String fileName,sheetName;
    String[][] tableArray=null;
    public InputDataProviderFromExcel() { }
    /**
     * 
     * @param excelInputStream
     * @param sheetName
     * @throws IOException
     */
    public InputDataProviderFromExcel(InputStream excelInputStream, String sheetName) throws IOException
    {
        this.data = loadFromSpreadsheet(excelInputStream);
    }
    /**
     * 
     * @return
     */
    public Object[][] getData()
    {
        return data;
    }
    /**
     * 
     * @param excelFile
     * @param sheetName
     * @return
     * @throws IOException
     */
    public Object[][] loadFromSpreadsheet(InputStream excelFile)  throws IOException
    {
        // TODO Auto-generated method stub
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        int numberOfColumns = countNonEmptyColumns(sheet);
        int numberOfRows = sheet.getLastRowNum() + 1;

        data = new Object[numberOfRows - 1][numberOfColumns - 1];
       // data = new Object[numberOfRows - 1][3];

        for (int rowNum = 1; rowNum < numberOfRows; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (isEmpty(row)) {
                break;
            } else {
                for (int column = 1; column < numberOfColumns; column++) {
                    Cell cell = row.getCell(column);
                    if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                        data[rowNum - 1][column - 1] = "";
                    } else {
                        data[rowNum - 1][column - 1] = objectFrom(workbook, cell);
                    }
                }
            }
        }

        return data;
    }
    
    /**
     * 
     * @param row
     * @return
     */
    private boolean isEmpty(Row row)
    {
        // TODO Auto-generated method stub
        Cell firstCell = row.getCell(0);
        boolean rowIsEmpty = (firstCell == null) || (firstCell.getCellType() == Cell.CELL_TYPE_BLANK);
        return rowIsEmpty;
    }

    /**
     * Count the number of columns, using the number of non-empty cells in the
     * first row.
     */
    private int countNonEmptyColumns(Sheet sheet)
    {
        // TODO Auto-generated method stub
        Row firstRow = sheet.getRow(0);
        return firstEmptyCellPosition(firstRow);
    }
    /**
     * 
     * @param cells
     * @return
     */
    private int firstEmptyCellPosition(Row cells)
    {
        // TODO Auto-generated method stub
        int columnCount = 0;
        for (Cell cell : cells) {
            if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                break;
            }
            columnCount++;
        }
        return columnCount;
    }
    /**
     * 
     * @param workbook
     * @param cell
     * @return
     */
    private Object objectFrom(XSSFWorkbook workbook, Cell cell)
    {
        // TODO Auto-generated method stub
        Object cellValue = null;
        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = cell.getRichStringCellValue().getString();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cellValue = getNumericCellValue(cell);
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = cell.getBooleanCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            cellValue = evaluateCellFormula(workbook, cell);
        }else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = evaluateCellFormula(workbook, cell);
        }

        return cellValue;
    }
    private String stringFrom(XSSFWorkbook workbook, Cell cell)
    {
        // TODO Auto-generated method stub
        Object cellValue = null;
        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = cell.getRichStringCellValue().getString();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cellValue = getNumericCellValue(cell);
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = cell.getBooleanCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            cellValue = evaluateCellFormula(workbook, cell);
        }else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = evaluateCellFormula(workbook, cell);
        }

        return (String) cellValue;
    }

    /**
     * 
     * @param cell
     * @return
     */
    private Object getNumericCellValue(final Cell cell)
    {
        Object cellValue;
        if (DateUtil.isCellDateFormatted(cell)) {
            cellValue = new Date(cell.getDateCellValue().getTime());
        } else {
            cellValue = cell.getNumericCellValue();
        }
        return cellValue;
    }
    /**
     * 
     * @param workbook
     * @param cell
     * @return
     */
    private Object evaluateCellFormula(final XSSFWorkbook workbook,final Cell cell)
    {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        CellValue cellValue = evaluator.evaluate(cell);
        Object result = null;

        if (cellValue.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            result = cellValue.getBooleanValue();
        } else if (cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            result = cellValue.getNumberValue();
        } else if (cellValue.getCellType() == Cell.CELL_TYPE_STRING) {
            result = cellValue.getStringValue();
        }

        return result;
     }
    
 
}
