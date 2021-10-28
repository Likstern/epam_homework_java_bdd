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
package eldorado.stepsDef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.eldordoHomework.qa.common.gui.models.SerpSnippet;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSerpStepsDef extends AbstractStepsDef {
    /**
     * Check the following points:<br/>
     * <ul>
     * <li>The host of URL if valid</li>
     * <li>URL doesn`t have port</li>
     * <li>Path of URL is "/sch/i.html"</li>
     * </ul>
     *
     * @throws Throwable AssertionError
     */
    @Then("^URL of product search page should be valid and has \"([^\"]*)\" in query$")
    public void urlOfProductSearchPageShouldBeValid(String request) throws Throwable {
        String expectedHost = new URI(siteUrl).getHost();
        assertThat(new URI(driver.getCurrentUrl())).hasHost(expectedHost)
                .hasNoPort()
                .hasQuery(String.format("q=%s&category=", request));
    }

    @Then("^URL of product search page should be valid and has \"([^\"]*)\" and \"([^\"]*)\"in query$")
    public void productAndOptionShouldBeInUrl(String request, String option) throws Throwable {
        String expectedHost = new URI(siteUrl).getHost();
        assertThat(new URI(driver.getCurrentUrl())).hasHost(expectedHost)
                .hasNoPort()
                .hasQuery(String.format("q=%s&category=%s", request,option));
    }

    @And("^Check that Button 'Добавить в корзину' is present$")
    public void checkThatButtonBuyItNowIsPresent() throws AssertionError {
        attachments.attachScreenShot("Check that Button 'Добавить в корзину' is present");
        assertThat(serpPage.getButtonAddToCart()).as("Check that Button 'Добавить в корзину' is present").isTrue();
    }

    @And("^Catalog filter is present$")
    public void checkCatalogFilterItNowIsPresent() throws AssertionError {
        attachments.attachScreenShot("Check that catalog filter is present");
        assertThat(serpPage.getCatalogSectionFilter().isDisplayed()).as("Check that catalog filter is present").isTrue();
    }

    /**
     * Check the number of snippets
     * @param expected number of snippets
     * @throws AssertionError AssertionError
     */
    @Then("^Check the number of snippets is \"([^\"]*)\"$")
    public void checkTheNumberOfSnippetsIs(int expected) throws AssertionError {
        attachments.attachScreenShot("Check the number of snippets");
        assertThat(serpPage.getSnippets()).as("Check the number of snippets").hasSize(expected);
    }

    @Then("^Check the title of snippet[s] equal to \"([^\"]*)\"$")
    public void checkTitlesOfSnippets(String request) throws AssertionError {
        for (SerpSnippet snippet: serpPage.getSnippets()) {
            assertThat(snippet.getTitle()).as(
                    "Check the title of snippets").containsIgnoringCase(request);
        }
    }

    @Then("^change quantity of snippets to \"([^\"]*)\"$")
    public void change_quantity_of_snippets_to(String quantity) {
        serpPage.setQuantityOfGoods(quantity);
        attachments.attachScreenShot("Change quantity of snippets");
    }
}
