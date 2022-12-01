package Model.Persistence;

import Model.Currency;
import Model.exchangeRate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ExchangeRateLoader implements ExchangeRateMethods{

    @Override
    public exchangeRate load(Currency from, Currency to) {
        try {
            return new exchangeRate(from, to, read(from.getName(), to.getName()));
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * This method read the codes below (from a exchangeRate) to create an URL where we can get the value
     * It's returned in double, as we need to the class.
     */
    private double read(String from, String to) throws IOException {
        String url = data(from, to);
        return Double.parseDouble(url);
    }

    
    /**
     * This method takes an URL and create a request to the website
     * If the website allows it, it gets the exchangeRate.
     */
    private String data(String from, String to) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + from.toLowerCase() + "/" + to.toLowerCase() + ".json").openStream()));
        String input;
        input = reader.readLine();
        input = reader.readLine();
        input = reader.readLine();
        reader.close();
        return input.substring(11);
    }
}
