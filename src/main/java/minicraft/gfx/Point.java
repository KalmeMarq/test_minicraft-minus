package minicraft.gfx;

public class Point {
	private int x;
    private int y;

	public Point() { this(0, 0); }
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point model) {
		x = model.x;
		y = model.y;
	}

    public void setX(int x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

	public void translate(int xoff, int yoff) {
		x += xoff;
		y += yoff;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Point)) return false;
		Point o = (Point) other;
		return x == o.x && y == o.y;
	}

	@Override
	public int hashCode() { return x * 71 + y; }
}
