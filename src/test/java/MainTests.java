
import java.io.IOException;
import java.net.URL;

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
        final URL url = new URL("https://www.cbr.ru/scripts/XML_val.asp?d=0");
        String expected = "<?xml version";
// Первые 13 символов
        String result = main.xmlParser(url).substring(0,13);

        Assertions.assertEquals(expected,result);

    }
//Моя версия IDE не поддерживает ввод русского языка следовательно данный тест на соответствие не может пройти
//Просьба проверить соответствие по количеству знаков
    @Test
    public void getCurseTestPos() throws IOException {
        Main main = new Main();
        String expected = "USD (Доллар США): 30,9436";

        String result = main.getCurse("02/03/2002", main.findValute("US Dollar"));

        Assertions.assertEquals(expected,result);

    }
    @Test
    public void getCurseTestNeg1() throws IOException {
        Main main = new Main();
        String expected = "Валюта отсутствует в базе данных";

        String result = main.getCurse("02/03/2002", "nd");

        Assertions.assertEquals(expected,result);

    }
    @Test
    public void getCurseTestNeg2() throws IOException {
        Main main = new Main();
        String expected = "Данная валюта не представлена в отчете за выбранный год";

        String result = main.getCurse("02/03/2002", main.findValute("Surinam Dollar"));

        Assertions.assertEquals(expected,result);

    }

}
