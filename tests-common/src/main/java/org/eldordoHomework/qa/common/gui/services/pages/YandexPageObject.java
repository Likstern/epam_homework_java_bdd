package org.eldordoHomework.qa.common.gui.services.pages;

import org.eldordoHomework.qa.common.gui.annotations.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@PageObject
public class YandexPageObject extends AbstractPageObject{

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${yandex.url:localhost}")
    protected String yandexUrl;

    public void openYandex() {
        LOG.info("Open Yandex search service");
        driver.get(yandexUrl);
    }
}
