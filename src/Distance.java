public class Distance {
	/** One of the pixels */
	private Pixel one;

	/** The other pixel*/
	private Pixel two;

	public Distance(Pixel one, Pixel two) {
		this.one = one;
		this.two = two;
	}

    public double Distance(Pixel one, Pixel two) {
    	return Math.pow((one.getRed() - one.getRed()), 2) + Math.pow((one.getGreen() - one.getGreen()), 2) +
			Math.pow((one.getBlue() - one.getBlue()), 2);
    }
}