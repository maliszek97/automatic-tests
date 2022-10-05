package main.java.AppDomElements;

import org.openqa.selenium.By;

public class MyCartDomElements {

    public static final DomElement productTablePrice = new DomElement(By.cssSelector(".ec_cartitem_price"), "product price in table");
    public static final DomElement productTableTotalPrice = new DomElement(By.cssSelector(".ec_cartitem_total"), "product total price in table");
    public static final DomElement productTableName = new DomElement(By.cssSelector(".ec_cartitem_title"), "product name in table");


}
