public class MainApplication {
    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU CHƯƠNG TRÌNH ===");

        // 1. ÁP DỤNG SINGLETON: Lấy cấu hình ứng dụng
        AppConfig config = AppConfig.getInstance();
        
        // Mô phỏng: Người dùng vào cài đặt và chọn "Giao diện tối"
        System.out.println("Người dùng đang chọn Dark Theme...");
        config.setTheme("DARK"); 

        // 2. ÁP DỤNG ABSTRACT FACTORY: Chọn xưởng sản xuất dựa trên cấu hình
        GUIFactory factory;

        if (config.getCurrentTheme().equals("DARK")) {
            factory = new DarkGUIFactory();
        } else {
            factory = new LightGUIFactory();
        }

        // Tạo UI Components (Client không cần biết nó là Light hay Dark)
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        // 3. HIỂN THỊ KẾT QUẢ
        System.out.println("\nĐang render giao diện ứng dụng...");
        button.paint();
        checkbox.check();
        
        System.out.println("=== KẾT THÚC CHƯƠNG TRÌNH ===");
    }
}