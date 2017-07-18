import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
public class edge_Highlights implements PlugIn {
	
	private void grayscaleEdgeHighlight(boolean colorImage, double alpha, double scale, ImageProcessor ip)
	{
		
		ImageProcessor copy, ipColor;
		ipColor = ip.duplicate();
		if(colorImage)
		{
			copy = convertToGrayscale(ip);
		}
		else
		{
			copy = ip.duplicate();
		}
		
		int w = ip.getWidth();
		int h = ip.getHeight();
		int Ix, Iy, I, q;
		int[] rgb = new int[3];
		
		for(int u=1; u<=w-2; u++)
		{
			for(int v=1; v<=h-2; v++)
			{
				 Ix = (int) (0.5 * (copy.getPixel(u, v+1) - copy.getPixel(u,v-1)));
				 Iy = (int) (0.5 * (copy.getPixel(u+1, v) - copy.getPixel(u-1,v)));
				 I = (int)((scale) * Math.sqrt((Ix*Ix)+(Iy*Iy)));
				 
				 if(colorImage)
				 {
					ipColor.getPixel(u,v,rgb);
					
					rgb[0] = (int)((alpha*rgb[0])+((1-alpha)*(I)));
					rgb[1] = (int)((alpha*rgb[1])+((1-alpha)*(I)));
					rgb[2] = (int)((alpha*rgb[2])+((1-alpha)*(I)));
					
					if(rgb[0]>255)
						 rgb[0]=255;
					if(rgb[0]<0)
						 rgb[0]=0;
					if(rgb[1]>255)
						 rgb[1]=255;
				    if(rgb[1]<0)
						 rgb[1]=0;
				    if(rgb[2]>255)
						 rgb[2]=255;
					if(rgb[2]<0)
						 rgb[2]=0;
					ipColor.putPixel(u,v,rgb);
				 }
				 else
				 {	 q = (int)((alpha*copy.getPixel(u, v))+((1-alpha)*(I)));
				 	if(q>255)
				 		q=255;
				 	if(q<0)
				 		q=0;
				 	ip.putPixel(u, v, q);
				 }
			}
		}
		
		ImagePlus imPls;
		if(colorImage)
			imPls = new ImagePlus("edge highlight", ipColor);
		else
			imPls = new ImagePlus("edge highlight", ip);
		imPls.show();
	}
	
	private ImageProcessor convertToGrayscale(ImageProcessor ip)
	{
		ImageProcessor ipNew = ip.duplicate();
		int w = ip.getWidth();
		int h = ip.getHeight();
		int[] rgb = new int[3];
		
		for(int u = 0;u<w;u++)
		{
			for(int v = 0;v<h;v++)
			{
				ip.getPixel(u,v,rgb);
				
				int r = rgb[0];
				int g = rgb[1];
				int b = rgb[2];
				
				int gray = (r+g+b)/3;
				int grayVal = (gray<<16) | (gray<<8) | gray;
				ipNew.putPixel(u,v,grayVal);
			}
		}
		
		return ipNew;
	}
	
	public void run(String args)
	{
		ImagePlus im = IJ.getImage();
		ImageProcessor ip = im.getProcessor();
		if(ip.isGrayscale())
			grayscaleEdgeHighlight(false, 0.5, 2.0, ip);
		else
		{
			//bright highlights
			grayscaleEdgeHighlight(true, 0.8, 2.0, ip);
			
			ImageProcessor ip1 = im.getProcessor();
			//dark highlights
			grayscaleEdgeHighlight(true, 0.8, -0.3, ip1);
		}
	}

}
