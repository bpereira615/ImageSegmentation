public class PixelDistance {
	
	public double distance (Pixel one, Pixel two) {
		return Math.pow(one.r() - two.r(), 2) + Math.pow(one.g() - two.g(), 2) +
			Math.pow(one.b() - two.b(), 2);
	}
}