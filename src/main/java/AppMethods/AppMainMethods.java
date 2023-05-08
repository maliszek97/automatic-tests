package main.java.AppMethods;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import io.qameta.allure.Step;
import main.java.AppDomElements.DomElement;
import main.java.Resources.Base;
import main.java.PageObjects.PageUrl;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.RequestId;
import org.openqa.selenium.devtools.v103.network.model.Response;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.Select;

public class AppMainMethods {

    private AtomicBoolean flag;

    @Step("Go to {viewUrl.description}")
    public void goToUrl(PageUrl viewUrl) {
        Base.getDriver().navigate().refresh();
        Base.getDriver().get(viewUrl.getUrl());
    }

    @Step("Click {element.description}")
    public void clickElement(DomElement element) {
        element.webElement().click();
    }

    @Step("Click all {element.description} elements")
    public void clickAllElements(DomElement element) {
        element.webElements().forEach(WebElement::click);
    }

    @Step("Click {element.description} with {index} index")
    public void clickElementByIndex(DomElement element, int index) {
        elementByIndex(element, index).click();
    }

    @Step("Click {element.description} with the '{attribute}' attribute with value '{attributeValue}'")
    public void clickElementByAttribute(DomElement element, String attribute, String attributeValue) {
        Optional<WebElement> el = element
                .webElements()
                .stream()
                .filter(el1 -> el1
                        .getAttribute(attribute)
                        .contains(attributeValue))
                .findFirst();
        el.ifPresent(WebElement::click);
    }

    @Step("Click {element.description} elements to {index} index")
    public void clickElementsToIndex(DomElement element, int index) {
        for (int i = 0; i < index; i++) {
            element.webElements().get(i).click();
        }
    }

    @Step("Choose '{value}' value in {element.description} select")
    public void chooseValueInSelect(String value, DomElement element) {
        Select select = new Select(element.webElement());
        select.selectByValue(value);
    }

    @Step("Get text from {element.description}")
    public String textFromElement(DomElement element) {
        return element.webElement().getText();
    }

    @Step
    public String partOfText(String text, String textDivider, int partIndex) {
        String[] partedText = text.split(textDivider);
        return partedText[partIndex];
    }

    @Step("Get text from {element.description} with {index} index")
    public String textFromElementByIndex(DomElement element, int index) {
        return elementByIndex(element, index).getText();
    }

    @Step("Type '{keyToType}' into {element.description}")
    public void typeIntoElement(String keyToType, DomElement element) {
        element.webElement().sendKeys(keyToType);
    }

    @Step("Type '{keyToType}' into {element.description} with {index} index")
    public void typeIntoElementByIndex(String keyToType, DomElement element, int index) {
        elementByIndex(element, index).sendKeys(keyToType);
    }

    @Step("Check for inclusion of {element.description}")
    public Boolean elementIsEnabled(DomElement element) {
        return element.webElement().isEnabled();
    }

    @Step("Display check of {element.description}")
    public Boolean elementIsDisplayed(DomElement element) {
        return element.webElement().isDisplayed();
    }

    @Step("Checking the number of {element.description}")
    public int numberOfElements(DomElement element) {
        return element.webElements().size();
    }

    @Step("Clear {element.description}")
    public void clearElement(DomElement element) {
        element.webElement().clear();
    }

    @Step("Clear {element.description} with {index} index")
    public void clearElementByIndex(DomElement element, int index) {
        elementByIndex(element, index).clear();
    }

    @Step("Merge {element1.description} with {element2.description}")
    public DomElement mergeTwoElements(DomElement element1, DomElement element2) {
        return new DomElement(new ByChained(element1.getBy(), element2.getBy()), "merged elements " + element1.getDescription() + " and" + element2.getDescription());
    }

    public WebElement elementByIndex(DomElement element, int index) {
        return element.webElements().get(index - 1);
    }

    @Step("Check request status, url and body")
    public void checkResponseStatusAndUrlAndBody(int responseStatus, String responseUrl, Boolean responseBodyIsEmpty) {

        DevTools devTools = Base.getDriver().getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        final RequestId[] requestIds = new RequestId[1];
        flag = new AtomicBoolean(false);

        devTools.addListener(Network.responseReceived(), response -> {

            Response res = response.getResponse();
            requestIds[0] = response.getRequestId();

            String responseBody = "[";
            if (responseBodyIsEmpty != null) {
                if (responseBodyIsEmpty) responseBody = "[]";
                else responseBody = "{";
            }

            if (res.getUrl().contains(responseUrl)) {
                flag.set(true);
                flag.set(res.getStatus() == responseStatus && devTools.send(Network.getResponseBody(requestIds[0])).getBody().contains(responseBody));
            }
        });
    }
}
