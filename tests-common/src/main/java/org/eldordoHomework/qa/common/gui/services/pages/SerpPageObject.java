package org.eldordoHomework.qa.common.gui.services.pages;
/*
* Copyright 2002 - 2017 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
import lombok.val;
import one.util.streamex.StreamEx;
import org.eldordoHomework.qa.common.gui.models.SerpSnippet;
import org.eldordoHomework.qa.common.gui.annotations.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@PageObject
public class SerpPageObject extends AbstractPageObject{
    private final String snippetListLocator = ".//*[@class='item ']";
    private final String buttonAddToCartLocator = ".//*[@class='addToCart cartButton']//*[text()='Добавить в корзину']";
    private final String catalogSectionFilterLocator = ".//*[@class='catalogSectionFilter q-catalog-filter']";
    private final String quantityOfGoodsLocator = ".//span/a[contains(text(),'%s')]";
    private final String viewResultsIconLocator = ".//*[contains(@class, 'gs-view-state-%s')]";
    private final String listStyleLocator = ".//*[contains(@class, 'listGood')]";
    private final String blockStyleLocator = ".//*[contains(@class, 'is-block-goodsList')]";

    @FindBy(xpath = snippetListLocator)
    List<WebElement> snippetList;

    @FindBy(xpath = buttonAddToCartLocator)
    List<WebElement> buttonAddToCart;

    @FindBy(xpath = catalogSectionFilterLocator)
    WebElement catalogSectionFilter;

    @FindBy(xpath = listStyleLocator)
    WebElement listStyle;

    @FindBy(xpath = blockStyleLocator)
    WebElement blockStyle;

    public List<SerpSnippet> getSnippets(){
        wdHelper.waitForElementIsPresent(By.xpath(snippetListLocator));
        return StreamEx.of(snippetList)
                .map(this::toSnippet)
                .toList();
    }

    public SerpSnippet toSnippet(WebElement webElement){
        val title = webElement.findElement(By.xpath(".//*[@class='itemTitle']")).getText();
        return new SerpSnippet(title);
    }

    public boolean getButtonAddToCart(){
        return buttonAddToCart.size() > 0;
    }

    public WebElement getCatalogSectionFilter() {
        return catalogSectionFilter;
    }

    public void setQuantityOfGoods(String quantity) {
       wdHelper.click(driver.findElement((By.xpath(String.format(quantityOfGoodsLocator,quantity)))));
    }

    public List<WebElement> getSnippetList() {
        return snippetList;
    }

    public WebElement getIsListStyleLocator() {
        return listStyle;
    }

    public WebElement getIsBockStyleLocator() {
        return blockStyle;
    }

    public void changeView(String style) {
        wdHelper.click(driver.findElement(By.xpath(String.format(viewResultsIconLocator,style))));
    }
}
