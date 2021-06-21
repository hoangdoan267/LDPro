package tamhoang.ldpro4;

public class NavItem {
    private int resIcons;

    private String subtitle;

    private String title;

    public NavItem(String paramString1, String paramString2, int paramInt) {
        this.title = paramString1;
        this.subtitle = paramString2;
        this.resIcons = paramInt;
    }

    public int getResIcons() {
        return this.resIcons;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public void setResIcons(int paramInt) {
        this.resIcons = paramInt;
    }

    public void setSubtitle(String paramString) {
        this.subtitle = paramString;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }
}
