package org.eldordoHomework.qa.common.gui.models;

import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
public class ServiceProducts {
    private final String title;
    private final String price;
    private final WebElement addButton;
}
