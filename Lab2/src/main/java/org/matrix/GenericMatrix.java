package org.matrix;

public class GenericMatrix<Element extends MatrixElement> {
    private int rowCount;
    private int colCount;
    private Element[][] data;

    public GenericMatrix(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        data = (Element[][]) new MatrixElement[rowCount][colCount];
    }

    public void displayMatrix() {
        System.out.printf("Matrix size: %d %d \n", rowCount, colCount);
        for (int i = 0; i < rowCount; ++i) {
            System.out.print('|');
            for (int j = 0; j < colCount; ++j) {
                if (data[i][j] == null) {
                    System.out.print(" null |");
                } else {
                    System.out.printf(" %s |", data[i][j].getString());
                }
            }
            System.out.println();
        }
    }

    public GenericMatrix<Element> add(GenericMatrix<Element> b) {
        if ((rowCount != b.getRowCount()) || (colCount != b.getColCount())) {
            return null;
        }
        GenericMatrix<Element> res = new GenericMatrix<>(rowCount, colCount);
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                if (data[i][j] != null && b.data[i][j] != null) {
                    res.setElement(i, j, (Element) data[i][j].add(b.data[i][j]));
                }
            }
        }
        return res;
    }


    public void setElement(int i, int j, Element element) {
        data[i][j] = element;
    }
    public void setData(Element[][] data) {
        this.data = data;
    }

    public Element getElement(int i, int j) {
        return data[i][j];
    }
    public Element[][] getData() {
        return data;
    }
    public int getRowCount() {
        return rowCount;
    }
    public int getColCount() {
        return colCount;
    }
}
