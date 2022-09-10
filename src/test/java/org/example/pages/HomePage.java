package org.example.pages;

import org.openqa.selenium.Keys;
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

    @FindBy(id = "small-searchterms")
    public WebElement searchField;

    @FindBy(css = "h2.product-title")
    public List<WebElement> searchProducts;

    @FindBy(xpath = "//*[starts-with(@id,'sku')]")
    public WebElement productSkuValue;

    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div/div[2]/h2/a")
    public WebElement firstProduct;

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

    public boolean isProductsContainSearchTerm(String searchWord){
        boolean allContains = true;
        for (WebElement product:searchProducts) {
            allContains &= product.getText().toLowerCase().contains(searchWord.toLowerCase());
        }
        return allContains;
    }

    public void search(String keyword){
        searchField.sendKeys(keyword);
        searchField.sendKeys(Keys.ENTER);
    }

    public void openFirstProduct(){
        firstProduct.click();
    }
}