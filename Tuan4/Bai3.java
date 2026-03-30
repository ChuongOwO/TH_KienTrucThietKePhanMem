import java.util.*;

// --- STRATEGY PATTERN: Các phương thức thanh toán ---
interface PaymentStrategy {
    void collectDetails();
    boolean validate();
}

class CreditCardPay implements PaymentStrategy {
    public void collectDetails() { System.out.println("Dang lay thong tin The tin dung..."); }
    public boolean validate() { return true; } // Gia lap xac thuc thanh cong
}

class PayPalPay implements PaymentStrategy {
    public void collectDetails() { System.out.println("Dang ket noi tai khoan PayPal..."); }
    public boolean validate() { return true; }
}

// --- DECORATOR PATTERN: Phí và Giảm giá ---
interface PayAmount { double getAmount(); String getDetail(); }

class BaseAmount implements PayAmount {
    private double val;
    public BaseAmount(double v) { this.val = v; }
    public double getAmount() { return val; }
    public String getDetail() { return "Gia goc: " + val; }
}

abstract class PayDecorator implements PayAmount {
    protected PayAmount payAmount;
    public PayDecorator(PayAmount p) { this.payAmount = p; }
}

class HandlingFee extends PayDecorator {
    public HandlingFee(PayAmount p) { super(p); }
    public double getAmount() { return payAmount.getAmount() + 5.0; }
    public String getDetail() { return payAmount.getDetail() + " + Phi xu ly (5.0)"; }
}

class PromoCode extends PayDecorator {
    public PromoCode(PayAmount p) { super(p); }
    public double getAmount() { return payAmount.getAmount() - 10.0; }
    public String getDetail() { return payAmount.getDetail() + " - Ma giam gia (10.0)"; }
}

// --- STATE PATTERN: Trang thai giao dich ---
interface TransactionState { void proceed(PaymentContext ctx); }

class InitialState implements TransactionState {
    public void proceed(PaymentContext ctx) {
        System.out.println("Buoc 1: Nhap thong tin thanh toan.");
        ctx.getStrategy().collectDetails();
        ctx.setState(new ValidatingState());
    }
}

class ValidatingState implements TransactionState {
    public void proceed(PaymentContext ctx) {
        System.out.println("Buoc 2: Dang xac thuc giao dich...");
        if (ctx.getStrategy().validate()) {
            ctx.setState(new SuccessState());
        }
    }
}

class SuccessState implements TransactionState {
    public void proceed(PaymentContext ctx) {
        System.out.println("Buoc 3: THANH TOAN THANH CONG!");
        System.out.println("Chi tiet: " + ctx.getAmountModel().getDetail());
        System.out.println("Tong tien cuoi cung: " + ctx.getAmountModel().getAmount());
    }
}

// --- CONTEXT: Ket noi 3 Pattern ---
class PaymentContext {
    private TransactionState state = new InitialState();
    private PaymentStrategy strategy;
    private PayAmount amountModel;

    public PaymentContext(PayAmount a, PaymentStrategy s) { this.amountModel = a; this.strategy = s; }
    public void setState(TransactionState s) { this.state = s; }
    public PaymentStrategy getStrategy() { return strategy; }
    public PayAmount getAmountModel() { return amountModel; }
    
    public void execute() { state.proceed(this); }
}

// --- MAIN ---
public class Bai3 {
    public static void main(String[] args) {
        // Decorator: Hoa don 100$, co phi xu ly va ma giam gia
        PayAmount finalAmount = new PromoCode(new HandlingFee(new BaseAmount(100.0)));

        // Strategy: Chon thanh toan qua Credit Card
        PaymentContext context = new PaymentContext(finalAmount, new CreditCardPay());

        // State: Chay quy trinh thanh toan
        System.out.println("--- KHOI TAO GIAO DICH ---");
        context.execute(); // Initial -> Validating
        context.execute(); // Validating -> Success
        context.execute(); // Success: In hoa don
    }
}