package minicraft.gfx;

public class Dimension {
	private int width;
	private int height;

	public Dimension() {
        this(0, 0);
    }

    public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Dimension(Dimension model) {
		width = model.width;
		height = model.height;
	}

    public void setWidth(int width) {
      this.width = width;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    public int getWidth() {
      return width;
    }

    public int getHeight() {
      return height;
    }

	public String toString() {
		return width + "x" + height;
	}
}
