package eldorado.stepsDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartStepsDef extends AbstractStepsDef {

    @And("^Open shopping cart$")
    public void openCart() {
        shoppingCart.openShoppingCart();
        attachments.attachScreenShot("Shopping cart is open");
    }

    @And("^Increase the number of similar products per unit$")
    public void increaseGoods() {
        dataContainer.setProductPrice(shoppingCart.getCartCost());
        shoppingCart.increaseProducts();
        attachments.attachScreenShot("Products is increase");
    }

    @Then("^Cost increase by the quantity of goods$")
    public void checkCostIsIncreaseByQuantity() {
        int count = shoppingCart.getCountProducts();
        int productCost = shoppingCart.getProductCost()*count;
        assertThat(shoppingCart.getCartCost().equals(productCost)).isTrue();
    }

    @And("^Click delete item link in shopping cart$")
    public void clickDeleteItemLink() {
        shoppingCart.deleteItem();
        attachments.attachScreenShot("Products is delete");
    }

    @And("^Click restore item link$")
    public void clickRestoreItemLink() {
        shoppingCart.restoreItem();
        attachments.attachScreenShot("Products is restore");
    }

    @Then("^Cost is equal a zero$")
    public void costIsEqualAZero() throws AssertionError{
        assertThat(shoppingCart.getCartCost().equals(0)).isTrue();
    }

    @Then("^Cost is restored$")
    public void costIsRestored() throws AssertionError{
        assertThat(!shoppingCart.getCartCost().equals(0)).isTrue();
    }

    @When("^User select ([^\"]*) year service$")
    public void selectYearService(String year){
        shoppingCart.changeService(year);
    }

    @Then("^Cost increase by express service price$")
    public void checkCostIncreaseByServicePrice() {
        assertThat(shoppingCart.getCartCost().equals(
                shoppingCart.getProductCost()+shoppingCart.getServiceCost())).isTrue();
    }

    @Then("^User add \"(.*)\" to cart, cost is equal a sum of products in cart$")
    public void aCostIsEqualSumOfProducts(String title) {
        int serviceProductPrice = shoppingCart.getServiceProductPrice(title);
        shoppingCart.addServiceProduct(title);
        assertThat(shoppingCart.getCartCost().equals(
                shoppingCart.getProductCost()+serviceProductPrice)).isTrue();
    }

    @And("^User add \"(.*)\" to cart$")
    public void addServiceProduct(String title) {
        dataContainer.setServiceProductPrice(shoppingCart.getServiceProductPrice(title));
        shoppingCart.addServiceProduct(title);
    }

    @Then("^Cost is equal a service product$")
    public void costIsEqualServiceProduct() {
        assertThat(shoppingCart.getCartCost().equals(dataContainer.getServiceProductPrice())).isTrue();
    }

    @And("^Decrease the number of similar products per unit$")
    public void decreaseGoods() {
        shoppingCart.decreaseProducts();
        attachments.attachScreenShot("Products is decrease");
    }

    @And("^Cost is equal a cost one product$")
    public void costIsEqualOneProduct() {
        assertThat(shoppingCart.getCartCost().equals(shoppingCart.getProductCost())).isTrue();
    }

    @When("^User select local delivery$")
    public void selectLocalDelivery$() {
        shoppingCart.clickLocalDelivery();
        attachments.attachScreenShot("Add local delivery$");
    }

    @Then("^Cost is increased by the cost of delivery$")
    public void costIncreasedByDelivery() {
        assertThat(shoppingCart.getCartCost().equals(
                shoppingCart.getGoodsCost()+shoppingCart.getDeliveryCost())).isTrue();
    }
}
