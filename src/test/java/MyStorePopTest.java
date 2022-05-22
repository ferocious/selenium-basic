import myStore.BasePage;
import myStore.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyStorePopTest extends TestBase {
    @Test
    public void verifySearchAndAddToCart() {
        List<Float> itemPrice = new ArrayList<>();
        List<Integer> itemQty = new ArrayList<>();

        BasePage myStore = new BasePage();

        myStore.homePage.navigateTo();
        myStore.homePage.searchFor("poster");

        Assert.assertEquals(myStore.searchResultPage.getResultsNumber(), 3);

        myStore.searchResultPage.clickQuickViewOnProduct("TODAY POSTER");

        myStore.productQuickView.changeProductVariant("80x120cm");
        itemPrice.add(myStore.productQuickView.getItemPrice());
        myStore.productQuickView.changeQuantity(2);
        itemQty.add(2);
        myStore.productQuickView.addToCart();
        myStore.cartPreview.close();

        myStore.navigate.openClothes();
        myStore.categoryPage.clickProductTile(1);
        itemPrice.add(myStore.productPage.getItemPrice());
        itemQty.add(1);
        myStore.productPage.addToBasket();
        myStore.cartPreview.clickProceedToCheckout();

        //asercje na cartPage
        for (int i=0; i < itemPrice.size(); i++) {
            String expectedPrice = formatPrice(itemPrice.get(i)*itemQty.get(i));
            Assert.assertEquals(myStore.cartPage.getCartItem(i).getTotalPrice(), expectedPrice);
            Assert.assertEquals(myStore.cartPage.getCartItem(i).getQty(), itemQty.get(i));
        }

        Assert.assertEquals(myStore.cartPage.getCartSubtotal(), formatPrice(getTestSubtotal(itemPrice, itemQty)));
        Assert.assertEquals(myStore.cartPage.getCartTotal(), formatPrice(getTestSubtotal(itemPrice, itemQty)+7));

    }

    private Float getTestSubtotal(List<Float> itemPrice, List<Integer> itemQty) {
        float itemsSubtotal = 0;
        for (int i=0; i< itemPrice.size(); i++)
            itemsSubtotal+=itemPrice.get(i)*itemQty.get(i);
        return itemsSubtotal;
    }

    public String formatPrice(Float price) {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat formatter = new DecimalFormat("0.00", otherSymbols);
        return "$"+formatter.format(price);
    }
}
