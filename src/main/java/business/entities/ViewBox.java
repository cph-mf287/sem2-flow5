package business.entities;

public class ViewBox {
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setOffsetX(int xOffset) {
        this.offsetX = xOffset;
    }

    public void setOffsetY(int yOffset) {
        this.offsetY = yOffset;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public boolean isDefined() {
        return width != 0 && height != 0;
    }
}
