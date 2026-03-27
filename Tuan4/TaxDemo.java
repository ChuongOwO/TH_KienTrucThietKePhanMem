interface TaxStrategy { double calculateTax(double price); }

class VATStrategy implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.1; }
}
class LuxuryTaxStrategy implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.3; }
}

class Product {
    private TaxStrategy strategy;
    private double price;
    public Product(double price, TaxStrategy strategy) {
        this.price = price;
        this.strategy = strategy;
    }
    public void showPrice() {
        System.out.println("Tong gia: " + (price + strategy.calculateTax(price)));
    }
}

public class TaxDemo {
    public static void main(String[] args) {
        Product p1 = new Product(100, new VATStrategy());
        p1.showPrice();
        Product p2 = new Product(100, new LuxuryTaxStrategy());
        p2.showPrice();
    }
}