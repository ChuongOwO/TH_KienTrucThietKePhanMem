interface Payment { double getCost(); }

class BasicPayment implements Payment {
    private double amount;
    public BasicPayment(double amount) { this.amount = amount; }
    public double getCost() { return amount; }
}

abstract class PaymentDecorator implements Payment {
    protected Payment decorated;
    public PaymentDecorator(Payment p) { this.decorated = p; }
    public double getCost() { return decorated.getCost(); }
}

class ProcessingFee extends PaymentDecorator {
    public ProcessingFee(Payment p) { super(p); }
    public double getCost() { return super.getCost() + 5.0; }
}

class DiscountCode extends PaymentDecorator {
    public DiscountCode(Payment p) { super(p); }
    public double getCost() { return super.getCost() - 10.0; }
}

public class PaymentDemo {
    public static void main(String[] args) {
        Payment p = new DiscountCode(new ProcessingFee(new BasicPayment(100)));
        System.out.println("Gia sau cung: " + p.getCost());
    }
}