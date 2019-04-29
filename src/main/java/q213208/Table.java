package q213208;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
class Table {

    private final String[] columns;
    private final Object[][] rows;

    public interface Format {
        void initialize(int columns, int rows);
        void startHeader();
        void endHeader();
        void startBody();
        void endBody();

        void startRow();
        void endRow();
        void addColumn(Object value);

        void flush() throws IOException;

    }

    public Table(String[] columns, Object[][] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public <T> Table sort(int column, Comparator<T> comparator) {
        Object[][] sorted = new Object[this.rows.length][];
        for (int index = 0; index < sorted.length; index++) {
            Object[] row = Arrays.copyOf(this.rows[index], this.rows[index].length);
            sorted[index] = row;
        }
        Arrays.sort(sorted, (row1, row2) -> {
            try {
                T column1 = (T) row1[column];
                T column2 = (T) row2[column];
                return comparator.compare(column1, column2);
            } catch (ClassCastException cce) {
                throw new IllegalArgumentException(
                        "Cannot sort column "+column+
                        " of type "+row1[column].getClass().getName()+
                        " with comparator "+comparator
                        , cce);
            }
        });
        return new Table(this.columns, sorted);
    }


    public <T> Table filter(int column, Predicate<T> filter) {
        List<Object[]> content = new ArrayList<>(this.rows.length);
        for (Object[] row : this.rows) {
            T value = (T) row[column];
            if ( filter.test(value) ) {
                content.add(row);
            }
        }
        return new Table(this.columns, content.toArray(new Object[1][1]));
    }

    public void write(Format format) throws IOException {
        format.initialize(this.columns.length, this.rows.length);
        format.startHeader();
        format.startRow();
        for (String column : this.columns) {
            format.addColumn(column);
        }
        format.endRow();
        format.endHeader();
        format.startBody();
        for (Object[] row : this.rows) {
            format.startRow();
            for (Object cell : row) {
                format.addColumn(cell);
            }
            format.endRow();
        }
        format.endBody();
        format.flush();
    }

}
