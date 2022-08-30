public class Player implements Comparable<Player>{
    private String name;
    private int themeId;
    public int score;

    public Player() {
        this.name = "Player@" + hashCode();
        this.themeId = ThemeManager.GetAvailableIndex();
    }

    public Player(String name, int themeId) {
        this.name = name;
        try {
            this.themeId = ThemeManager.CheckIndex(themeId);
        } catch(IllegalArgumentException e) {
            this.themeId = ThemeManager.NO_THEME;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @Override
    public int compareTo(Player o) {
        return (o.score + this.score) % 2;
    }
}