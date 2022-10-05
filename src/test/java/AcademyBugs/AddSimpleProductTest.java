package test.java.AcademyBugs;

import main.java.AppDomElements.MyCartDomElements;
import main.java.AppDomElements.ProductCardDomElements;
import main.java.Resources.Base;
import main.java.Resources.Listeners;
import main.java.Resources.Pair;
import main.java.pageObjects.PageUrls;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@org.testng.annotations.Listeners({Listeners.class})
public class AddSimpleProductTest extends Base {

    private final List<Pair<String, Double>> nameAndPriceOfChosenProducts = new ArrayList<>();

    @Parameters({"productIndex"})
    @BeforeTest
    private void addSimpleProductToCartInitialOperations(int productIndex) {

        getAppMainMethods().goToUrl(PageUrls.findBugsUrl);

        String productName = getAppMainMethods().textFromElement(ProductCardDomElements.productNameLink);

        Double productPrice = Double.parseDouble(getAppMainMethods()
                .textFromElement(
                        ProductCardDomElements
                                .productPrice)
                .substring(1));

        Pair<String, Double> productNameAndPrice = new Pair<>(productName, productPrice);
        nameAndPriceOfChosenProducts.add(productNameAndPrice);

        getAppMainMethods().clickElementByIndex(ProductCardDomElements.btnAddToCart, productIndex * 2);

    }

    @Parameters({"productIndex"})
    @Test
    public void addSimpleProductToCart(int productIndex) throws InterruptedException {

        Assert.assertTrue(getAppMainMethods()
                .elementIsEnabled(ProductCardDomElements
                        .productSuccessfullyAddInfo), "Product successfully added info is not displayed");

        getAppMainMethods()
                .clickElementByIndex(ProductCardDomElements.btnCheckout, productIndex * 2);

        int productTableIndex;
        productTableIndex = MyCartDomElements
                .productTableName
                .webElements()
                .indexOf(nameAndPriceOfChosenProducts
                        .get(productIndex - 1)
                        .getL());

        Assert.assertNotEquals(
                productTableIndex
                , null
                , "In products table there isn't added '"
                        + nameAndPriceOfChosenProducts
                        .get(productIndex - 1)
                        .getL()
                        + "' product"
        );

        Assert.assertEquals(Double.parseDouble(
                getAppMainMethods()
                        .textFromElementByIndex(
                                MyCartDomElements
                                        .productTablePrice
                                , productTableIndex).substring(1))
                , nameAndPriceOfChosenProducts
                        .get(productIndex - 1)
                        .getR()
                , "Price of added product in products table is not correct"
        );
    }
}
