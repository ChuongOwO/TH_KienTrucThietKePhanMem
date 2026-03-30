import java.util.*;

// --- STRATEGY PATTERN ---
interface TaxStrategy { double calculateTax(double price); }

class VATTax implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.10; }
}

class LuxuryTax implements TaxStrategy {
    public double calculateTax(double price) { return price * 0.30; }
}

// --- DECORATOR PATTERN ---
interface TaxItem { double getPrice(); String getName(); }

class BasicTaxItem implements TaxItem {
    private String name;
    private double price;
    public BasicTaxItem(String name, double price) { this.name = name; this.price = price; }
    public double getPrice() { return price; }
    public String getName() { return name; }
}

abstract class TaxDecorator implements TaxItem {
    protected TaxItem item;
    public TaxDecorator(TaxItem i) { this.item = i; }
    public String getName() { return item.getName(); }
}

class EnvironmentalFee extends TaxDecorator {
    public EnvironmentalFee(TaxItem i) { super(i); }
    public double getPrice() { return item.getPrice() + 2.0; }
    public String getName() { return super.getName() + " (+ Phi moi truong)"; }
}

// --- STATE PATTERN ---
interface TaxState { void applyTax(TaxProductContext ctx); }

class PendingTaxState implements TaxState {
    public void applyTax(TaxProductContext ctx) {
        System.out.println("Trang thai: DANG CHO DUYET...");
        ctx.setState(new AppliedTaxState());
    }
}

class AppliedTaxState implements TaxState {
    public void applyTax(TaxProductContext ctx) {
        double taxAmount = ctx.getStrategy().calculateTax(ctx.getItem().getPrice());
        double finalPrice = ctx.getItem().getPrice() + taxAmount;
        System.out.println("Trang thai: DA AP THUE.");
        System.out.println("San pham: " + ctx.getItem().getName());
        System.out.println("Gia sau thue & phi: " + finalPrice);
    }
}

// --- CONTEXT ---
class TaxProductContext {
    private TaxState state = new PendingTaxState();
    private TaxItem item;
    private TaxStrategy strategy;

    public TaxProductContext(TaxItem i, TaxStrategy s) { this.item = i; this.strategy = s; }
    public void setState(TaxState s) { this.state = s; }
    public TaxItem getItem() { return item; }
    public TaxStrategy getStrategy() { return strategy; }
    public void process() { state.applyTax(this); }
}

// --- MAIN ---
public class Bai2 {
    public static void main(String[] args) {
        // Decorator
        TaxItem laptop = new EnvironmentalFee(new BasicTaxItem("Laptop Gaming", 1000.0));

        // Strategy
        TaxProductContext context = new TaxProductContext(laptop, new LuxuryTax());

        // State
        System.out.println("--- KHOI TAO HE THONG THUE ---");
        context.process(); 
        context.process(); 
    }
}