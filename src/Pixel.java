import java.awt.Color;

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
		setRGBVals(val);
	}

	/** Extract the red value from total */
	private void setRGBVals(int val) {
		//TODO: credit to stack overflow?

		Color c = new Color(val);
		this.red = c.getRed();
		this.green = c.getGreen();
		this.blue = c.getBlue();
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
	public int getRed() {
		return this.red;
	}
	public int getGreen() {
		return this.green;
	}
	public int getBlue() {
		return this.blue;
	}

}