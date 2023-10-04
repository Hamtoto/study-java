import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NoBlankInputStream extends FilterInputStream {
    public NoBlankInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int b;
        do {
            b = super.read();
        } while (b != -1 && Character.isWhitespace(b) && b != '\n');
        return b;
    }

    public static void main(String[] args) {
        try (InputStream in = new NoBlankInputStream(new FileInputStream("Test.txt"))) {
            int b;
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
