// Interfaces
interface Button { void paint(); }
interface Checkbox { void check(); }

// --- Light Components ---
class LightButton implements Button {
    public void paint() { System.out.println("-> Vẽ Nút bấm màu TRẮNG (Light Mode)."); }
}
class LightCheckbox implements Checkbox {
    public void check() { System.out.println("-> Tích vào Hộp kiểm màu TRẮNG (Light Mode)."); }
}

// --- Dark Components ---
class DarkButton implements Button {
    public void paint() { System.out.println("-> Vẽ Nút bấm màu ĐEN (Dark Mode)."); }
}
class DarkCheckbox implements Checkbox {
    public void check() { System.out.println("-> Tích vào Hộp kiểm màu ĐEN (Dark Mode)."); }
}