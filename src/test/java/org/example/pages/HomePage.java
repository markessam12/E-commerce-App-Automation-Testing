package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

// page_url = https://demo.nopcommerce.com/
public class HomePage {
    @FindBy(id = "customerCurrency")
    public WebElement customerCurrencyMenu;

    @FindBy(css = "span.price.actual-price")
    public List<WebElement> pricesTagline;
    
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setCurrency(String currency){
        Select selectCurrency = new Select(customerCurrencyMenu);
        selectCurrency.selectByVisibleText(currency);
    }

    public boolean isCurrencyCorrect(){
        String symbol = "$";
        Select selectCurrency = new Select(customerCurrencyMenu);
        boolean allCorrect = true;
        if(selectCurrency.getFirstSelectedOption().getText().equals("Euro")) symbol = "€";
        else if(customerCurrencyMenu.getText().equals("US Dollar")) symbol = "$";
        for (WebElement price:pricesTagline) {
            allCorrect &= price.getText().contains(symbol);
        }
        return allCorrect;
    }
}