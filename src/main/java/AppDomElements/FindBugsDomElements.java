package main.java.AppDomElements;

import org.openqa.selenium.By;

public class FindBugsDomElements {

    //    pagination elements
    public static final DomElement paginationNotActiveOption = new DomElement(By.cssSelector(".what-we-offer-pagination-link"), "pagination option which is not active");
    public static final DomElement paginationActiveOption = new DomElement(By.cssSelector(".ec_selected"), "pagination option which is active");
    public static final DomElement paginationNumberOfResultsInfo = new DomElement(By.cssSelector(".ec_product_page_showing"), "information about number of showing results");

    //    sorting elements
    public static final DomElement sortingSelect = new DomElement(By.id("sortfield"), "sorting selects");

}
