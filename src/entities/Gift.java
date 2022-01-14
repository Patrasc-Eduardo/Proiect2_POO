package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Gift {
  private final String productName;
  private final Double price;
  private final String category;
  @JsonIgnore private int quantity;

  public Gift(
      final String productName, final Double price, final String category, final int quantity) {
    this.productName = productName;
    this.price = price;
    this.category = category;
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getProductName() {
    return productName;
  }

  public Double getPrice() {
    return price;
  }

  public String getCategory() {
    return category;
  }

  @Override
  public String toString() {
    return "Gift{"
        + "productName='"
        + productName
        + '\''
        + ", price="
        + price
        + ", category='"
        + category
        + '\''
        + ", quantity="
        + quantity
        + '}';
  }
}
