public class Pixel {
	/** Row index of pixel */
	private int row;

	/** Column index of pixel */
	private int col;

	/** Color value of the pixel */
	private int val;

	/** Red value */
	private int red;

	/** Green value */
	private int green;

	/** Blue value */
	private int blue;

	/** Constructor for Pixel class */
	public Pixel(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.blue = val & 0xFF;
        this.green = (val >> 8) & 0xFF;
        this.red = (val >> 16) & 0xFF;
	}

	public int row() {
		return this.row;
	}

	public int col() {
		return this.col;
	}
	public int value() {
		return this.val;
	}
	public int r() {
		return this.red;
	}
	public int g() {
		return this.green;
	}
	public int b() {
		return this.blue;
	}

}