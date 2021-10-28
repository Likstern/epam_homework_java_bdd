package eldorado.stepsDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageStepsDef extends AbstractStepsDef {

    @Then("^URL of product page should be valid and has \"([^\"]*)\" in path")
    public void isProductPage(String request) throws Throwable{
        String expectedHost = new URI(siteUrl).getHost();
        assertThat(new URI(driver.getCurrentUrl())).hasHost(expectedHost)
                .hasPath(String.format("/cat/detail/%s/",request));
    }

    @And("^Click add to shopping cart button$")
    public void clickAddToCartButton() {
        productCardPage.clickAddToCartButton();
    }

    @And("^Check the cost in header menu is equal to cost in shopping cart$")
    public void checkCostInHeaderAndCart() throws AssertionError {
        shoppingCart.openShoppingCart();
        attachments.attachScreenShot("Open shopping cart");
        int headerCost = homePage.getBasketCost();
        assertThat(shoppingCart.getCartCost().equals(headerCost)).isTrue();
    }
}
