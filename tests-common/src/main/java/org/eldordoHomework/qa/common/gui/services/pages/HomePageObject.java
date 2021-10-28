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


import org.eldordoHomework.qa.common.gui.annotations.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@PageObject
public class HomePageObject extends AbstractPageObject {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final String POPUP_LOCATOR = ".//*[@id='closed_salePopup']";

    private final String CLOSE_POPUP_LOCATOR = ".//*[@id='closed_salePopup']//a[contains(@class, 'close')]";

    private final String REGION_LOCATOR = ".//*[@class='headerRegion gg']";

    private final String REGION_POPUP_LOCATOR = ".//*[@class='popup popupRegion']";

    private final String REGION_NAME_LOCATOR = ".//*[@class='headerRegionName']";

    private final String BASKET_COST_LOCATOR = ".//*[@id='basketCost']";


    @Value("${site.url:localhost}")
    protected String siteUrl;

    @Value("${friend.url:localhost}")
    protected String anotherUrl;

    @FindBy(xpath = BASKET_COST_LOCATOR)
    WebElement basketCost;

    public void open() {
        LOG.info("Open main page");
        driver.get(siteUrl);
        if (wdHelper.isElementDisplayed(By.xpath(POPUP_LOCATOR))) {
            wdHelper.click(driver.findElement(By.xpath(CLOSE_POPUP_LOCATOR)));
        }
    }

    public void changeRegion(String region) {
        wdHelper.click(driver.findElement(By.xpath(REGION_LOCATOR)));
        wdHelper.click(driver.findElement(By.xpath(String.format(
                REGION_POPUP_LOCATOR.concat("//li/*[contains(text(), '%s')]"), region))));
    }

    public String getRegionName() {
        return wdHelper.getText(By.xpath(REGION_NAME_LOCATOR));
    }

    public Integer getBasketCost() {
        String[] textCost = basketCost.getText().split(" |р.");
        return Integer.parseInt(String.join("", textCost));
    }
}
