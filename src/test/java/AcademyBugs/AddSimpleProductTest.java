package test.java.AcademyBugs;

import main.java.AppDomElements.ProductCardDomElements;
import main.java.Resources.Base;
import main.java.Resources.Listeners;
import main.java.pageObjects.PageUrls;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

@org.testng.annotations.Listeners({Listeners.class})
public class AddSimpleProductTest extends Base {

    private List<Double> productPrices;

    @Parameters({"productIndex"})
    @BeforeTest
    private void addSimpleProductToCartInitialOperations(int productIndex) {

        getAppMainMethods().goToUrl(PageUrls.findBugsUrl);

        productPrices
                .add(Double.parseDouble(getAppMainMethods()
                                .partOfText(getAppMainMethods()
                                        .textFromElement(ProductCardDomElements.productPrice), "&", 0)
                        )
                );

        getAppMainMethods().clickElementByIndex(ProductCardDomElements.btnAddToCart, productIndex);


    }

    @Parameters({"productIndex"})
    @Test
    public void addSimpleProductToCart(int productIndex) throws InterruptedException {

        Assert.assertTrue(getAppMainMethods().elementIsDisplayed(ProductCardDomElements.productSuccessfullyAddInfo));

        getAppMainMethods().clickElement(ProductCardDomElements.btnCheckout);

    }
}
