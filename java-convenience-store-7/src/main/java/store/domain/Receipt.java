package store.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import store.domain.product.Product;

public class Receipt {
    private Set<Product> purchaseProducts;
    private Set<Product> additionalProducts;

    public Receipt() {
        this.purchaseProducts = new HashSet<>();
        this.additionalProducts = new HashSet<>();
    }

    public void addPurchaseProducts(final Product purchaseProduct) {
        if (!purchaseProducts.add(purchaseProduct)) {
            addProductQuantity(purchaseProducts, purchaseProduct);
        }
    }

    public void addAdditionalProducts(final Product additionlProduct) {
        if (!additionalProducts.add(additionlProduct)) {
            addProductQuantity(additionalProducts, additionlProduct);
        }
    }

    public int getPurchaseProductsCount() {
        return purchaseProducts.stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public int getPurchaseProductsPrice() {
        return purchaseProducts.stream()
                .mapToInt(this::calculateProductPrice)
                .sum();
    }

    public int getAdditionalProductsPrice() {
        return additionalProducts.stream()
                .mapToInt(this::calculateProductPrice)
                .sum();
    }

    public int getMembershipPrice(final String membershipAnswer) {
        if (Objects.equals("N", membershipAnswer)) {
            return 0;
        }
        int membershipableMoney = getPurchaseProductsPrice() - getAdditionalProductsPrice();
        int membershipMoney = (int) (membershipableMoney * 0.3);
        if (membershipMoney < 8000) {
            return (int) (membershipableMoney * 0.3);
        }
        return 8000;
    }

    public int getTotalPrice(final String membershipAnswer) {
        return getPurchaseProductsPrice() - getAdditionalProductsPrice() - getMembershipPrice(membershipAnswer);
    }

    public Set<Product> getPurchaseProducts() {
        return purchaseProducts;
    }

    public Set<Product> getAdditionalProducts() {
        return additionalProducts;
    }

    private void addProductQuantity(final Set<Product> products, final Product additionlProduct) {
        Product findProduct = products.stream()
                .filter(product -> Objects.equals(product, additionlProduct))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
        findProduct.addQuantity(additionlProduct);
    }

    private int calculateProductPrice(final Product product) {
        return product.getPrice() * product.getQuantity();
    }
}
