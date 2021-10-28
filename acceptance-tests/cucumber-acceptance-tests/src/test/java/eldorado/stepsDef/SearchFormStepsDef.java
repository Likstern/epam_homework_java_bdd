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

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertionError;
import org.eldordoHomework.qa.common.gui.models.ProductList;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchFormStepsDef extends AbstractStepsDef{

    /**
     * Submit Search Form by special search request
     * @param request search request
     */
    @When("^I perform search by request \"([^\"]*)\"$")
    public void iPerformSearchByRequest(String request){
        mainSearchFormPage.setSearchRequest(request);
        mainSearchFormPage.submit();
        attachments.attachScreenShot("SERP page");
    }

    /**
     * Enter search word without submit
     * @param request search request
     */
    @When("^I enter search request \"([^\"]*)\" into search field$")
    public void i_enter_search_request_into_search_field(String request){
        mainSearchFormPage.setSearchRequest(request);
        attachments.attachScreenShot("Search request");
    }

    @Then("^Search form showing tooltip with similar \"([^\"]*)\" products$")
    public void search_form_showing_tooltip_with_similar_products(String request) {
        for (ProductList title: mainSearchFormPage.getAutoCompliteFormPositions()) {
            assertThat(request).isSubstringOf(title.getTitle().toLowerCase());
            attachments.attachScreenShot("Search drop list ");
        }
    }

    @When("^Click search line clear button$")
    public void clickSearchLineClearButton() {
        mainSearchFormPage.clear();
        attachments.attachScreenShot("Click on clear button");
    }

    @Then("^search line is empty$")
    public void checkSearchLineIsEmpty() {
        assertThat((mainSearchFormPage.getSearchLineText())).isEmpty();
    }

    /**
     * Select category option in search line
     * @param
     * @throws Throwable
     */
    @When("^I select category option \"([^\"]*)\"$")
    public void i_select_category_option(String option) throws Throwable {
        wdHelper.click(mainSearchFormPage.getSearchCategoryFilterLocator());
        mainSearchFormPage.changeSearchOption(option);
        attachments.attachScreenShot("New serach option");
    }

    @Then("^Page has no snippets$")
    public void checkThatPageHasNoSnippets() throws SoftAssertionError {
        attachments.attachScreenShot("Page has no snippets");
        assertThat(serpPage.getSnippetList()).isEmpty();
    }

    @When("^Search results can look like \"([^\"]*)\"")
    public void search_results_can_look_like_list(String view) throws AssertionError {
        if (view.equals("list")) {
            assertThat((serpPage.getIsListStyleLocator().isDisplayed())).as("List style");
        } else {assertThat((serpPage.getIsBockStyleLocator().isDisplayed())).as("Block style");}
    }

    @When("^I click \"([^\"]*)\" view icon$")
    public void i_click_view_icon(String viewStyle) throws SoftAssertionError {
        serpPage.changeView(viewStyle);
        attachments.attachScreenShot("Change style");
    }
}
