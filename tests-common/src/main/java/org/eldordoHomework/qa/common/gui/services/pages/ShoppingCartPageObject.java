package org.eldordoHomework.qa.common.gui.services.pages;

import lombok.val;
import one.util.streamex.StreamEx;
import org.eldordoHomework.qa.common.gui.annotations.PageObject;
import org.eldordoHomework.qa.common.gui.models.ServiceProducts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


@PageObject
public class ShoppingCartPageObject extends AbstractPageObject {

    private final String cartCostLocator = "//*[@id='total_price_id']";
    private final String goodsCostLocator = "//*[@id='goods_val_td']";
    private final String serviceCostLocator = "//*[@id='services_price_td']";
    private final String deliveryCostLocator = "//*[@id='deliv_price_id']";
    private final String productCostLocator = "//div[@class='offline-price']/div[@class='price1']";
    private final String plusButtonLocator = "//*[@class='qs-side-right']";
    private final String minusButtonLocator = "//*[contains(@class, 'qs-side-left')]";
    private final String countProductsLocator = "//*[contains(@class, 'countProd quant qs-spinner')]";
    private final String delItemLinkLocator = "//span[contains(@onclick, 'DeleteItem')]";
    private final String restoreItemLinkLocator = "//a[contains(@onclick, 'RecoveryItems')]";
    private final String checkboxServiceLocator = "//span[@rel='express_service-%s']";
    private final String serviceProductsLocator = "//div[@class='owl-item']";
    private final String localDeliverySpanLocator = "//span[@data-delivery='local']";

    @Value("${basket.url:localhost}")
    protected String shoppingCartUrl;

    @FindBy(xpath = delItemLinkLocator)
    WebElement delItemLink;

    @FindBy(xpath = restoreItemLinkLocator)
    WebElement restoreItemLink;

    @FindBy(xpath = productCostLocator)
    WebElement productCost;

    @FindBy(xpath = cartCostLocator)
    WebElement cartCost;

    @FindBy(xpath = goodsCostLocator)
    WebElement goodsCost;

    @FindBy(xpath = deliveryCostLocator)
    WebElement deliveryCost;

    @FindBy(xpath = serviceCostLocator)
    WebElement serviceCost;

    @FindBy(xpath = countProductsLocator)
    WebElement countProducts;

    @FindBy(xpath = plusButtonLocator)
    WebElement plusButton;

    @FindBy(xpath = minusButtonLocator)
    WebElement minusButton;

    @FindBy(xpath = serviceProductsLocator)
    List<WebElement> serviceProducts;

    @FindBy(xpath = localDeliverySpanLocator)
    WebElement localDeliverySpan;


    public List<ServiceProducts> getServiceProducts(){
        wdHelper.waitForElementIsPresent(By.xpath(serviceProductsLocator));
        return StreamEx.of(serviceProducts)
                .map(this::toServiceProductList)
                .toList();
    }

    private ServiceProducts toServiceProductList(WebElement webElement){
        val title = webElement.findElement(By.xpath("//*[contains(@class, 'q-services-item-name')]")).getText();
        val price = webElement.findElement(By.xpath("//*[@class='q-services-item-price']")).getText();
        val addButton = webElement.findElement(By.xpath("//*[contains(@class, 'servicesItem q-btn-to-cart')]"));
        return new ServiceProducts(title, price, addButton);
    }

    public void openShoppingCart() {
        driver.get(shoppingCartUrl);
    }

    public Integer getCartCost() {
        String[] textCost = cartCost.getText().split(" |р");
        return Integer.parseInt(String.join("", textCost));
    }

    public Integer getProductCost() {
        String[] textCost = productCost.getText().split(" |р.");
        return Integer.parseInt(String.join("", textCost));
    }

    public Integer getServiceCost() {
        String[] textCost = serviceCost.getText().split(" |р");
        return Integer.parseInt(String.join("", textCost));
    }

    public Integer getGoodsCost() {
        String[] textCost = goodsCost.getText().split(" |р");
        return Integer.parseInt(String.join("", textCost));
    }

    public Integer getDeliveryCost() {
        String[] textCost = deliveryCost.getText().split(" |р");
        return Integer.parseInt(String.join("", textCost));
    }

    public Integer getCountProducts() {
        wdHelper.waitForPageUpdated();
        return Integer.parseInt(countProducts.getAttribute("value"));
    }

    public void increaseProducts(){
        wdHelper.click(plusButton);
    }

    public void decreaseProducts(){
        wdHelper.click(minusButton);
    }

    public void deleteItem() {
        wdHelper.click(delItemLink);
    }

    public void restoreItem() {
        wdHelper.click(restoreItemLink);
    }

    public void changeService(String year) {
        wdHelper.click(driver.findElement(By.xpath(
                String.format(checkboxServiceLocator, year))));
    }

    public void addServiceProduct(String title) {
        for (ServiceProducts product : getServiceProducts()) {
            if (product.getTitle().equals(title)) {
                wdHelper.click(product.getAddButton());
                break;
            }
        }
    }

    public int getServiceProductPrice (String title) {
        int price =0 ;
        for (ServiceProducts product : getServiceProducts()) {
            if (product.getTitle().equals(title)) {
                String[] textCost = product.getPrice().split(" |р.");
                price = Integer.parseInt(String.join("", textCost));
            }
        }
        return price;
    }

    public void clickLocalDelivery() {
        wdHelper.click(localDeliverySpan);
    }
}
