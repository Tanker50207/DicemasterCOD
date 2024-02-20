import org.apache.commons.lang3.StringUtils;

public class PrettyTable {

    private static final int PADDING_SIZE = 2;
    private static final String NEW_LINE = "\n";
    private static final String TABLE_JOINT_SYMBOL = "+";
    private static final String TABLE_V_SPLIT_SYMBOL = "|";
    private static final String TABLE_H_SPLIT_SYMBOL = "-";

    public static void print(String[] headers, String[][] data) {
        int[] maxWidths = headersMaxWidths(headers, data);

        printPreparedTable(headers, data, maxWidths);
    }
    public static String bold(String text) {
        return "\033[1;32m" + text + "\033[0m";
    }
    private static int getMaxWidth(String text, int col, String[][] data) {
        int maxWidth = stripAnsiCodes(text).length();

        for (String[] datum : data) {
            String cell = stripAnsiCodes(datum[col]);
            int cellWidth = cell.length();

            if (cellWidth > maxWidth) {
                maxWidth = cellWidth;
            }
        }

        return maxWidth + PADDING_SIZE * 2;
    }

    private static String stripAnsiCodes(String text) {
        return text.replaceAll("\033\\[[0-9;]*m", "");
    }
    private static int[] headersMaxWidths(String[] headers, String[][] data) {
        int cols = headers.length;
        int[] result = new int[cols];

        for (int i = 0; i < cols; i++) {
            result[i] = getMaxWidth(headers[i], i, data);
        }

        return result;
    }

    private static void printPreparedTable(String[] headers, String[][] data, int[] widths) {
        int lineLength = getLineLength(widths);
        String hSplit = StringUtils.repeat(TABLE_H_SPLIT_SYMBOL, lineLength + 2);

        printLine(hSplit);
        printRow(headers, widths);

        for (String[] row : data) {
            printLine(hSplit);
            printRow(row, widths);
        }

        printLine(hSplit);
    }

    private static int getLineLength(int[] widths) {
        int result = -1;

        for (int width : widths) {
            result += width + 1;
        }

        return result;
    }

    private static void printLine(String line) {
        System.out.println(line);
    }

    private static void printRow(String[] row, int[] widths) {
        StringBuilder formatBuilder = new StringBuilder();

        int i = 0;
        for (int width : widths) {
            if (row[i].length() != stripAnsiCodes(row[i]).length())
                width += row[i].length() - stripAnsiCodes(row[i]).length();
            formatBuilder.append(TABLE_V_SPLIT_SYMBOL).append(" %-").append(width - 2).append("s ");
        }

        formatBuilder.append(TABLE_V_SPLIT_SYMBOL);
        String format = formatBuilder.toString();

        System.out.printf((format) + "%n", (Object[]) row);
    }
}