package Controller;

import Model.Currency;
import Model.Persistence.ExchangeRateLoader;
import Model.Persistence.FileLoader;
import Model.exchangeRate;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class Controller {
    private View vista;
    private JButton boton;
    private JTextField text;
    private JTextField text0;
    private JComboBox<String> lista;
    private JComboBox<String> lista0;

    public Controller(View vista, Currency[] lista2) {
        this.vista = vista;
        this.boton = vista.getBoton();        
        this.text = vista.getText();
        this.text0 = vista.getText0();
        this.lista = vista.getLista();
        this.lista0 = vista.getLista0();
        addListener(lista2);
    }


    
    
    private void addListener(Currency[] lista2) {
        ActionListener Listener = new ActionListener() {
            private Currency from;
            private Currency to;
            private exchangeRate rate;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double amount = Double.parseDouble(text.getText());
                    getCurrency();
                    getLoader();
                    text0.setText(String.valueOf(amount* rate.getAmount()));
                }catch(NumberFormatException exa){
                }

            }

            private void getCurrency() {
                from = lista2[lista.getSelectedIndex()];
                to = lista2[lista0.getSelectedIndex()];
            }

            private void getLoader() {
                ExchangeRateLoader loader = new ExchangeRateLoader();
                rate = loader.load(from, to);                
            }
        };
        boton.addActionListener(Listener);
    }    

    
    
}
