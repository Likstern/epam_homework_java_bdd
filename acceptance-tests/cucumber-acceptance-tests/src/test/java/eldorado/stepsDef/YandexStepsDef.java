package eldorado.stepsDef;

import cucumber.api.java.en.And;

public class YandexStepsDef extends AbstractStepsDef {

    @And("^Open Yandex search service$")
    public void OpenYandexSite() {
        yandexPage.openYandex();
        attachments.attachScreenShot("Yandex search service main page");
    }
}
