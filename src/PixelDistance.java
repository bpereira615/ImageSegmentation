/** Implementation of the Distance interface.
 *
 */
public class PixelDistance implements Distance<Pixel>{
	
	/** Empty constructor for PixelDistance */
	public PixelDistance() {

	}
	public double distance (Pixel one, Pixel two) {
		double square = Math.pow(one.r() - two.r(), 2) + Math.pow(one.g() - two.g(), 2) 
			+ Math.pow(one.b() - two.b(), 2);
		return Math.pow(square, 0.5);
	}
}