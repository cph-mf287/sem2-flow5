package business.entities;

public class SvgImage {
    private final Viewport viewport;
    private final ViewBox viewBox;
    private String backgroundColor;
    private String fillColor;
    private String strokeColor;
    private int strokeWidth;
    private String graphics;

    public SvgImage() {
        viewport = new Viewport();
        viewBox = new ViewBox();
    }

    public void setViewport(int width, int height) {
        viewport.setWidth(width);
        viewport.setHeight(height);
    }

    public void setViewBox(int x, int y, int width, int height) {
        viewBox.setOffsetX(x);
        viewBox.setOffsetY(y);
        viewBox.setWidth(width);
        viewBox.setHeight(height);
    }

    public void setBackgroundColor(String color) {
        backgroundColor = color;
    }

    public void setDefaultFillColor(String color) {
        fillColor = color;
    }

    public void setDefaultStrokeColor(String color) {
        strokeColor = color;
    }

    public void setDefaultStrokeWidth(int width) {
        strokeWidth = width;
    }

    public void addRect(float x, float y, float width, float height) {
        addRect(x, y, width, height, null, null);
    }

    public void addRect(float x, float y, float width, float height, String color) {
        addRect(x, y, width, height, color, null);
    }

    public void addRect(float x, float y, float width, float height, String color, String content) {
        graphics += String.format("\t<rect x='%f' y='%f' width='%f' height='%f' fill='%s'>%s</rect>\n", x, y, width, height, color, content);
    }

    public String toString() {
        String svgElement = String.format("<svg width='%d' height='%d'", viewport.getWidth(), viewport.getHeight());
        if (viewBox.isDefined())
            svgElement += String.format(" viewBox='%d %d %d %d'", viewBox.getOffsetX(), viewBox.getOffsetY(), viewBox.getWidth(), viewBox.getHeight());
        if (!fillColor.isEmpty())
            svgElement += String.format(" fill='%s'", fillColor);
        if (!strokeColor.isEmpty())
            svgElement += String.format(" stroke='%s'", strokeColor);
        if (strokeWidth > 0)
            svgElement += String.format(" stroke-width='%s'", strokeWidth);
        if (!backgroundColor.isEmpty())
            svgElement += String.format(" style='background-color:%s'", backgroundColor);
        svgElement += String.format(">\n%s</svg>", graphics);
        return svgElement;
    }

}
