public class AppConfig {
    private static AppConfig instance;
    private String currentTheme;

    private AppConfig() {
        currentTheme = "LIGHT"; // Mặc định là sáng
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setTheme(String theme) {
        this.currentTheme = theme;
    }
}