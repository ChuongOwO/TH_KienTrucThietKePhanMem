import java.util.*;

// Giao diện chung cho các trạng thái
interface OrderState {
    void handle(OrderContext context);
    void cancel(OrderContext context); 
}

// Trạng thái: MỚI TẠO
class NewOrderState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Trang thai: MOI TAO. Dang kiem tra thong tin don hang...");
        context.setState(new ProcessingState());
    }
    public void cancel(OrderContext context) {
        System.out.println("Hanh vi: Dang o trang thai MOI TAO -> Cho phep HUY.");
        context.setState(new CancelledState());
    }
}

// Trạng thái: ĐANG XỬ LÝ
class ProcessingState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Trang thai: DANG XU LY. Dang dong goi va van chuyen...");
        context.setState(new DeliveredState());
    }
    public void cancel(OrderContext context) {
        System.out.println("Hanh vi: Dang o trang thai DANG XU LY -> Van cho phep HUY va goi ship quay dau.");
        context.setState(new CancelledState());
    }
}

// Trạng thái: ĐÃ GIAO (Không thể hủy nữa)
class DeliveredState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Trang thai: DA GIAO. Cap nhat thanh cong!");
    }
    public void cancel(OrderContext context) {
        System.out.println("Loi: Don hang DA GIAO thanh cong, khong the HUY.");
    }
}

// Trạng thái: HỦY
class CancelledState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Trang thai: DA HUY. Dang tien hanh hoan tien cho khach hang...");
    }
    public void cancel(OrderContext context) {
        System.out.println("Loi: Don hang nay da o trang thai HUY roi.");
    }
}

// Lớp ngữ cảnh (Context)
class OrderContext {
    private OrderState state = new NewOrderState();

    public void setState(OrderState state) { this.state = state; }
    
    public void nextStep() { state.handle(this); }
    public void cancelOrder() { state.cancel(this); }
}

// --- HÀM CHẠY THỬ ---
public class OrderDemo {
    public static void main(String[] args) {
        System.out.println("--- KICH BAN 1: Chay thong suot ---");
        OrderContext order1 = new OrderContext();
        order1.nextStep(); // Moi tao -> Dang xu ly
        order1.nextStep(); // Dang xu ly -> Da giao
        order1.nextStep(); // Da giao -> Cap nhat thanh cong
        
        System.out.println("\n--- KICH BAN 2: Huy giua chung ---");
        OrderContext order2 = new OrderContext();
        order2.nextStep();   // Moi tao -> Dang xu ly
        order2.cancelOrder(); // Dang xu ly -> Huy
        order2.nextStep();   // Huy -> Thuc hien hoan tien
    }
}