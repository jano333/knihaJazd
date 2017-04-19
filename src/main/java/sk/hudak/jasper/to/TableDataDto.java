package sk.hudak.jasper.to;

/**
 * Created by hudak on 29.09.2016.
 */
public class TableDataDto {

    private String productName;
    private String productUrl;
    private String unitPrice;
    private String lastActulization;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLastActulization() {
        return lastActulization;
    }

    public void setLastActulization(String lastActulization) {
        this.lastActulization = lastActulization;
    }
}
