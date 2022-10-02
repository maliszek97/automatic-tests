package main.java.AppDomElements;

import main.java.Resources.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DomElement {

    private final By by;
    private final String description;

    public DomElement(By by, String description) {
        this.by = by;
        this.description = description;
    }

    public WebElement webElement() {
        return Base.getDriver().findElement(by);
    }

    public List<WebElement> webElements() {
        return Base.getDriver().findElements(by);
    }

    public By getBy() {
        return by;
    }

    public String getDescription() {
        return " element: " + description;
    }
}
