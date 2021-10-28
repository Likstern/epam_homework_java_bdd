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
import org.eldordoHomework.qa.common.gui.annotations.PageObject;
import org.eldordoHomework.qa.common.gui.models.OptionsList;
import org.eldordoHomework.qa.common.gui.models.ProductList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@PageObject
public class MainSearchFormPageObject extends AbstractPageObject{

    private final String POSITIONS_LIST_LOCATOR = ".//*[@id='ui-id-1']/li";

    private final String SEARCH_LINE_LOCATOR = ".//*[@id='search_line']";

    private final String SEARCH_SUBMIT_LOCATOR = ".//*[@class='headerSearchSubmit']";

    private final String SEARCH_TEXT_BLOCK_LOCATOR = ".//div[@class='searchTextBlock']";

    private final String SEARCH_CATEGORY_FILTER_LOCATOR = "//*[@class ='search_line_category_option_selected']";

    private final String CATEGORY_OPTION_LOCATOR = "//div[@class='search_line_category_option']";

    private final String SEARCH_CLEAN_BUTTON_LOCATOR = ".//*[contains(@class, 'search-line-clear')]";

    @FindBy(xpath = SEARCH_LINE_LOCATOR)
    WebElement searchLine;

    @FindBy(xpath = SEARCH_SUBMIT_LOCATOR)
    WebElement buttonSubmit;

    @FindBy(xpath = POSITIONS_LIST_LOCATOR)
    List<WebElement> positionsList;

    @FindBy(xpath = SEARCH_CLEAN_BUTTON_LOCATOR)
    WebElement clearButton;

    @FindBy(xpath = SEARCH_CATEGORY_FILTER_LOCATOR)
    WebElement searchCategoryFilterLocator;

    @FindBy(xpath = CATEGORY_OPTION_LOCATOR)
    List<WebElement> categoryOptionList;

    public void setSearchRequest(String request){
        searchLine.sendKeys(request);
    }

    public void submit() {
        buttonSubmit.click();
    }

    public void clear() {clearButton.click();}

    public ProductList toList(WebElement webElement){
        val title = wdHelper.getText(webElement.findElement(By.xpath(SEARCH_TEXT_BLOCK_LOCATOR)));
        return new ProductList(title);
    }

    public OptionsList optionsList (WebElement webElement) {
        val title = wdHelper.getText(driver.findElement(By.xpath(CATEGORY_OPTION_LOCATOR.concat("[normalize-space(text()) and normalize-space(.)]"))));
        val data = webElement.findElement(By.xpath(CATEGORY_OPTION_LOCATOR)).getAttribute("data-value");
        return new OptionsList(title,data);
    }

    public List<ProductList> getAutoCompliteFormPositions() {
        wdHelper.waitForElementIsPresent(By.xpath(POSITIONS_LIST_LOCATOR));
        return StreamEx.of(positionsList)
                .map(this::toList)
                .toList();
    }

    public List<OptionsList> getSearchOptions() {
        wdHelper.waitForElementIsPresent(By.xpath(CATEGORY_OPTION_LOCATOR));
        return StreamEx.of(categoryOptionList)
                .map(this::optionsList)
                .toList();
    }

    public String getSearchLineText() {
        return searchLine.getText();
    }

    public WebElement getSearchCategoryFilterLocator() {
        return searchCategoryFilterLocator;
    }

    public void changeSearchOption(String option) {
        wdHelper.click(driver.findElement(
                By.xpath(CATEGORY_OPTION_LOCATOR.concat(String.format("[normalize-space(text())='%s']", option)))));

    }
}
