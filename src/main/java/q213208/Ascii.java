package q213208;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ascii implements Table.Format {
    private final List<List<String>> header = new ArrayList<>();
    private final List<List<String>> body = new ArrayList<>();
    private final Appendable out;
    private final String spacing;

    private List<List<String>> section;
    private int[] lengths;
    private List<String> row;
    private int column;

    public Ascii(Appendable out) {
        this(out, "  ");
    }

    public Ascii(Appendable out, String spacing) {
        this.out = out;
        this.spacing = spacing;
    }

    @Override
    public void initialize(int columns, int rows) {
        this.header.clear();
        this.body.clear();
        this.lengths = new int[columns];
        Arrays.fill(this.lengths, 0);

        this.section = null;
        this.column = 0;
        this.row = null;
    }

    @Override
    public void startHeader() {
        this.section = this.header;
    }

    @Override
    public void endHeader() {

    }

    @Override
    public void startBody() {
        this.section = this.body;
    }

    @Override
    public void endBody() {

    }

    @Override
    public void startRow() {
        this.row = new ArrayList<>();
        this.column = 0;
    }

    @Override
    public void endRow() {
        this.section.add(this.row);
    }

    @Override
    public void addColumn(Object value) {
        updateMaxLength(this.column, value);
        this.row.add(String.valueOf(value));
        this.column++;
    }

    private void updateMaxLength(int column, Object value) {
        int current = this.lengths[column];
        int length = String.valueOf(value).length();
        this.lengths[column] = Math.max(current, length);
    }

    @Override
    public void flush() throws IOException {
        for (List<String> line : this.header) {
            write(line);
            this.out.append("\n");
        }
        for (List<String> line : this.body) {
            write(line);
            this.out.append("\n");
        }
        this.out.append("\n");
    }

    private void write(List<String> line) throws IOException {
        for (int column = 0; column < line.size(); column++) {
            String value = line.get(column);
            String pattern = "%1$-" + this.lengths[column] + "s";
            this.out
                    .append(String.format(pattern, value))
                    .append(this.spacing);
        }
    }
}
