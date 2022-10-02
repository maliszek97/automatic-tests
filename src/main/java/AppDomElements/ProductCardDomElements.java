package main.java.AppDomElements;

import org.openqa.selenium.By;

public class ProductCardDomElements {

    //    button elements
    public static final DomElement btnAddToCart = new DomElement(By.xpath("//span[@class='ec_product_addtocart']/a[text()='ADD TO CART']"), "button to add to cart");
    public static final DomElement btnCheckout = new DomElement(By.xpath("//span[@class='ec_product_addtocart']/a[text()='CHECKOUT NOW']"), "button to checkout the card");
    public static final DomElement btnSelectOptions = new DomElement(By.xpath("//span[contains(@class,'ec_product_addtocart')]/a[text()='Select Options']"), "button to select options");
    public static final DomElement btnLoginForPricing = new DomElement(By.xpath("//span[contains(@class,'ec_product_addtocart')]/a[text()='Login for Pricing']"), "button to login for pricing");

    //    general elements
    public static final DomElement productNameLink = new DomElement(By.cssSelector(".ec_image_link_cover"), "product name link");
    public static final DomElement productImage = new DomElement(By.cssSelector(".ec_image_container_none"), "product image");
    public static final DomElement productContainer = new DomElement(By.cssSelector(".ec_product_type1"), "product container");
    public static final DomElement productPrice = new DomElement(By.cssSelector(".ec_price_type1"), "product price");
    public static final DomElement productSuccessfullyAddInfo = new DomElement(By.cssSelector(".ec_product_successfully_added div"), "product successfully added information");


}
