package minicraft.gfx;

public class Insets {
	private int left;
    private int top;
    private int right;
    private int bottom;

	public Insets() { this(0); }
	public Insets(int dist) { this(dist, dist, dist, dist); }
	public Insets(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

    public void setTop(int top) {
      this.top = top;
    }

    public void setBottom(int bottom) {
      this.bottom = bottom;
    }

    public void setLeft(int left) {
      this.left = left;
    }

    public void setRight(int right) {
      this.right = right;
    }

    public int getTop() {
      return top;
    }

    public int getBottom() {
      return bottom;
    }

    public int getLeft() {
      return left;
    }

    public int getRight() {
      return right;
    }

	public Rectangle addTo(Rectangle r) {
		return new Rectangle(r.getLeft() - left, r.getTop() - top, r.getRight() + right, r.getBottom() + bottom, Rectangle.CORNERS);
	}

	public Rectangle subtractFrom(Rectangle r) {
		return new Rectangle(r.getLeft() + left, r.getTop() + top, r.getRight() - right, r.getBottom() - bottom, Rectangle.CORNERS);
	}

	public Dimension addTo(Dimension d) {
		return new Dimension(d.getWidth() + left + right, d.getHeight() + top + bottom);
	}

	public Dimension subtractFrom(Dimension d) {
		return new Dimension(d.getWidth() - left - right, d.getHeight() - top - bottom);
	}

	public Insets addInsets(Insets s) {
		return new Insets(left + s.left, top + s.top, right + s.right, bottom + s.bottom);
	}

	public Insets subtractInsets(Insets s) {
		return new Insets(left - s.left, top - s.top, right - s.right, bottom - s.bottom);
	}

	public String toString() {
		return super.toString()+"[left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom + "]";
	}
}
