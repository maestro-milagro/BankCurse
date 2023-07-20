
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTests {
    @Test
    public void findValuteTestPos() throws IOException {
        Main main = new Main();
        String valute = "Singapore Dollar";
        String expected = "R01625";

        String result = main.findValute(valute);

        Assertions.assertEquals(expected,result);

    }
    @Test
    public void findValuteTestNeg() throws IOException {
        Main main = new Main();
        String valute = "Singapore Dollart";
        String expected = "nd";

        String result = main.findValute(valute);

        Assertions.assertEquals(expected,result);

    }

    @Test
    public void xmlParserTest() throws IOException {
        Main main = new Main();
        String valute = "Singapore Dollart";
        String expected = "";

        String result = main.findValute(valute);

        Assertions.assertEquals(expected,result);

    }
}
