// Abstract Factory Interface
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory cho Light Theme
class LightGUIFactory implements GUIFactory {
    public Button createButton() { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
}

// Concrete Factory cho Dark Theme
class DarkGUIFactory implements GUIFactory {
    public Button createButton() { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}