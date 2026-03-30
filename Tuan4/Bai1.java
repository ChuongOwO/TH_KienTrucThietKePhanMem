import java.util.*;

// --- STRATEGY PATTERN ---
interface ShippingStrategy { double calculate(double weight); }
class FastShipping implements ShippingStrategy { public double calculate(double w) { return w * 20; } }
class StandardShipping implements ShippingStrategy { public double calculate(double w) { return w * 10; } }

// --- DECORATOR PATTERN ---
interface Order { double getCost(); String getDescription(); }
class BasicOrder implements Order { 
    public double getCost() { return 100.0; } 
    public String getDescription() { return "Don hang co ban"; }
}
abstract class OrderDecorator implements Order {
    protected Order decoratedOrder;
    public OrderDecorator(Order o) { this.decoratedOrder = o; }
    public double getCost() { return decoratedOrder.getCost(); }
    public String getDescription() { return decoratedOrder.getDescription(); }
}
class GiftWrap extends OrderDecorator {
    public GiftWrap(Order o) { super(o); }
    public double getCost() { return super.getCost() + 5.0; }
    public String getDescription() { return super.getDescription() + " + Boc qua"; }
}

// --- STATE PATTERN ---
interface OrderState { void next(OrderContext ctx); }
class NewState implements OrderState {
    public void next(OrderContext ctx) { System.out.println("Dang kiem tra... Chuyen sang DANG XU LY."); ctx.setState(new ProcessingState()); }
}
class ProcessingState implements OrderState {
    public void next(OrderContext ctx) { System.out.println("Dang dong goi... Chuyen sang DA GIAO."); ctx.setState(new DeliveredState()); }
}
class DeliveredState implements OrderState {
    public void next(OrderContext ctx) { System.out.println("Don hang da hoan tat!"); }
}

// --- CONTEXT ---
class OrderContext {
    private OrderState state = new NewState();
    private Order orderDetails;
    private ShippingStrategy shipping;

    public OrderContext(Order o, ShippingStrategy s) { this.orderDetails = o; this.shipping = s; }
    public void setState(OrderState s) { this.state = s; }
    public void process() { state.next(this); }
    public void showBill() {
        double total = orderDetails.getCost() + shipping.calculate(2.0); 
        System.out.println("Mo ta: " + orderDetails.getDescription());
        System.out.println("Tong thanh toan: " + total);
    }
}

public class Bai1 {
    public static void main(String[] args) {
        // Tao don hang co ban -> Boc qua (Decorator)
        Order myOrder = new GiftWrap(new BasicOrder());
        
        // Chon giao hang nhanh (Strategy)
        OrderContext context = new OrderContext(myOrder, new FastShipping());
        
        // Chay quy trinh (State)
        System.out.println("--- Kiem tra hoa don ---");
        context.showBill();
        
        System.out.println("\n--- Cap nhat trang thai ---");
        context.process(); // Moi tao -> Dang xu ly
        context.process(); // Dang xu ly -> Da giao
        context.process(); // Dang xu ly -> Da giao
    }
}