package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  
  public void zeroRed()
  {
	  Pixel [][] redPixels = this.getPixels2D();
	  for (int row = 0; row < redPixels.length; row++)
	  {
		for (int col = 0; col < redPixels[0].length; col++)
		{
			Pixel currentPixel = redPixels[row][col];
			currentPixel.setRed(0);
			
			redPixels[row][col].setRed(0);
		}
		  
	  }
  }
  
  public void zeroGreen()
  {
	  Pixel[][] greenPixels = this.getPixels2D();
	  for(int row = 0; row < greenPixels.length; row++)
	  {
		  for(int col = 0; col < greenPixels[0].length; col++)
		  {
			  Pixel currentPixel = greenPixels[row][col];
			  currentPixel.setGreen(0);
			  
			  greenPixels[row][col].setGreen(0);
		  }
	  }
  }
  
  
  
  public void keepOnlyBlue()
  {
	  zeroRed();
	  zeroGreen();
  }
  
  public void keepOnlyRed()
  {
	  zeroGreen();
	  zeroBlue();
  }
  
  public void keepOnlyGreen()
  {
	  zeroBlue();
	  zeroRed();
  }
  
  
  public void grayScale()
  {
	  Pixel[][] pixels = this.getPixels2D();
	    for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	    	  int gray = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen() / 3);
	        pixelObj.setBlue(gray);
	        pixelObj.setGreen(gray);
	        pixelObj.setRed(gray);
	      }
	    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  
  
  
  
  public void mirrorVerticalRight()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel RightPixel = null;
    Pixel leftPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = pixels[0].length - 1; col > width/2; col--)
      {
        RightPixel = pixels[row][col];
        leftPixel = pixels[row][(width/2) - (col - width/2) ];
        leftPixel.setColor(RightPixel.getColor());
      }
    } 
  }
  
  
  
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  
  public void mirrorArms()
  {
	  int mirrorPoint1 = 206;
	  int mirrorPoint2 = 200;
	  int mirrorPoint3 =198;
	  Pixel firstPixel = null;
	  Pixel seconedPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for(int row = 158; row < 194; row ++)
	  {
		  for(int col = 102; col < 172; col++)
		  {
			  firstPixel = pixels[row][col];
			  seconedPixel = pixels[mirrorPoint1 - row + mirrorPoint1][mirrorPoint2 - col + mirrorPoint2];
			  seconedPixel.setColor(firstPixel.getColor());
		  }
	  }
  
  
	  for(int row = 168; row < 290; row++)
	  {
		  for(int col = 240; col < 300; col++)
		  {
			  firstPixel = pixels[row][col];
			  seconedPixel = pixels[mirrorPoint3 - row + mirrorPoint3][mirrorPoint1 - col + mirrorPoint1];
			  seconedPixel.setColor(firstPixel.getColor());
		  }
	  }
  
  
  
  
  
  }
  
  
  public void mirrorHorizontal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length;
	  for (int row = 0; row < height/2; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  bottomPixel = pixels[row][col];
			  topPixel = pixels[height - 1 - row ][col];
			  topPixel.setColor(bottomPixel.getColor());
		  }
	  }
  }
  
  
  public void mirrorGull()
  {
	  int mirrorPoint1 = 330;
	  int mirrorPoint2 = 370;
	  Pixel firstPixel = null;
	  Pixel seconedPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for(int row = 230; row < 340; row ++)
	  {
		  for(int col = 230; col < 330 ; col++)
		  {
			  firstPixel = pixels[row][col];
			  seconedPixel = pixels[mirrorPoint1 + row - mirrorPoint1][mirrorPoint2 - col + mirrorPoint2];
			  seconedPixel.setColor(firstPixel.getColor());
		  }
	  }
  
  
  }
  
  public void copyPicture()
  {
	    Picture flower1 = new Picture("robot.jpg");
	    this.copy(flower1,0,0);
	    this.copy(flower1,200,0);
	   
  }
 
  
  
  public void fixUnderWater()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for( int row = 0; row < pixels.length; row ++)
	  {
		  for(int col = 0; col < pixels[0].length; col ++)
		  {
			  Pixel currentPixel = pixels[row][col];
			  int currentBlue = currentPixel.getBlue();
			  int currentGreen = currentPixel.getGreen();
			  currentPixel.setGreen(currentGreen - 55);
			  currentPixel.setBlue(currentBlue - 35);  
		  }
	  }
  }
  
  public void negate()
  {
	  Pixel[][] pixels = this.getPixels2D();
	    for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	        int newRed = pixelObj.getGreen();
	        int newBlue = pixelObj.getRed();
	        int newGreen = pixelObj.getBlue();
	        pixelObj.setRed(newRed);
	        pixelObj.setGreen(newGreen);
	        pixelObj.setBlue(newBlue);
	      }
	    }
			  
  }
  
  
  public void mirrorHorizontalBottomToTop()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length;
	  for (int row = height - 1; row > height/2; row--)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[(height/2) - (row - height/2) ][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  
  
  
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("robot.jpg");
    Picture flower2 = new Picture("swan.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
	
    Picture beach = new Picture("seagull.jpg");
    beach.explore();
    beach.copyPicture();
    beach.explore();
    
  }
  
} // this } is the end of class Picture, put all new methods before this
