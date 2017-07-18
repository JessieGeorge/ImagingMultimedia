import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.plugin.ImageCalculator;
import ij.process.ImageProcessor;
public class difference_Image implements PlugIn{
	
	private void absoluteDiffImage(ImageProcessor ip1, ImageProcessor ip2)
	{
		int w = ip1.getWidth();
		int h = ip1.getHeight();
		ImageProcessor diffImageProc = ip1.duplicate();
		for(int u=0; u<=w; u++)
		{
			for(int v=0; v<=h; v++)
			{
				int d = Math.abs(ip1.getPixel(u, v) - ip2.getPixel(u, v));
				diffImageProc.putPixel(u,v,d);
			}
		}
		
		ImagePlus impls = new ImagePlus("Absolute Difference Image",diffImageProc);
		impls.show();
	}
	public void run(String args)
	{
		//IJ.error("hello");
		ImagePlus im1 = IJ.openImage("/resources/soccer1.bmp");
		//im1.show();
		ImagePlus im2 = IJ.openImage("/resources/soccer2.bmp");
		
		//im2.show();
		//ImageProcessor ip2 = im2.getProcessor();
		
		ImageCalculator ic = new ImageCalculator();
		ImagePlus im3 = ic.run("difference", im1, im2);
		im3.show();
		//absoluteDiffImage(ip1, ip2);
	}

}
