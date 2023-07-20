


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста введите название искомой валюты на английском языке");
        String valute = scanner.nextLine();
        System.out.println("Введите дату за которую вы хотите увидеть курс выбранной валюты в формате (dd/mm/yyyy)");
        String date = scanner.nextLine();
        System.out.println(main.getCurse(date, main.findValute(valute)));
    }

    public String findValute(String name) throws IOException {
        final URL url = new URL("https://www.cbr.ru/scripts/XML_val.asp?d=0");
        String s = xmlParser(url);
        if (s.contains(name)) {
            String tr = s.substring(s.indexOf(name));
            String parentCode = tr.substring(tr.indexOf("<ParentCode>") + 12, tr.indexOf("    </ParentCode>"));
            return parentCode;
        }
        return "nd";
    }

    public String getCurse(String date, String valute) throws IOException {
        if (valute.equals("nd")) {
            return "Валюта отсутствует в базе данных";
        }
        final URL url = new URL("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date);
        String s = xmlParser(url);
        if (s.contains("<Valute ID=\"" + valute + "\">")) {
            String tr = s.substring(s.indexOf("<Valute ID=\"" + valute + "\">"));
            String charCode = tr.substring(tr.indexOf("<CharCode>") + 10, tr.indexOf("</CharCode>"));
            String name = tr.substring(tr.indexOf("<Name>") + 6, tr.indexOf("</Name>"));
            String curse = tr.substring(tr.indexOf("<Value>") + 7, tr.indexOf("</Value>"));
            return charCode + " (" + name + "): " + curse;
        }
        return "Данная валюта не представлена в отчете за выбранный год";
    }


    public String xmlParser(URL url) throws IOException {
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/xml");
        con.setConnectTimeout(3000);
        con.setReadTimeout(3000);
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        }
    }
}
