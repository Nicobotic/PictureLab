package classes;
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
  
  /** Method to set the everything to only blue */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to negate the colors in the picture */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
    	int r = pixelObj.getRed();
    	int b = pixelObj.getBlue();
    	int g = pixelObj.getGreen();
    	pixelObj.setBlue(255-b);
        pixelObj.setGreen(255-g);
        pixelObj.setRed(255-r);
      }
    }
  }
  
  /** Method to make the picture gray */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
    	int r = pixelObj.getRed();
    	int b = pixelObj.getBlue();
    	int g = pixelObj.getGreen();
    	int s = (r+b+g)/3;
    	pixelObj.setBlue(s);
        pixelObj.setGreen(s);
        pixelObj.setRed(s);
      }
    }
  }
  
  /** Method to give the color of the photo more contrast */
  public void fixUnderwater()
  {
	Pixel[][] pixels = this.getPixels2D();
    Pixel pixel = null;
    for (int r = 0; r < pixels.length; r++)
    {
     for (int c = 0; c < pixels[0].length; c++)
     {
       pixel = pixels[r][c];
       pixel.setRed(pixel.getRed()*4);
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
  
  //mirrors the right side of the image
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)//loops through the rows
    {
      for (int col = 0; col < width / 2; col++)//loops through the columns until the middle
      {
    	//sets the left side = to the right
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  //method that mirrors the top to bottom half
  public void mirrorHorizontal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length;
	  int width = pixels[0].length;
	  for (int row = 0; row < height / 2; row++)//goes through half of the rows
	  {
		  for (int col = 0; col < width; col++)//goes through all columns
		  {
			  //sets bottom = to top
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[(pixels.length- 1) - row][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  //method that mirrors the image bottom to top
  public void mirrorHorizontalBotToTop()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length;
	  int width = pixels[0].length;
	  for (int row = 0; row < height / 2; row++)//goes through half of the rows
	  {
		  for (int col = 0; col < width; col++)//goes through all columns
		  {
			//sets top = to bottom
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[(pixels.length- 1) - row][col];
			  topPixel.setColor(bottomPixel.getColor());
		  }
	  }
  }
  
  //method that mirrors the image diagonally
  public void mirrorDiagonal()
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel topRightPixel = null;
      Pixel bottomLeftPixel = null;
      int length;
      //chooses between row or column
      if (pixels.length < pixels[0].length) {
    	  length = pixels.length; 
      }
      else {
    	  length = pixels[0].length; 
      }
      for (int row = 0; row < length; row++)//goes the rows
      {
          for (int col = row; col < length; col++)//goes the columns
          { 
              topRightPixel = pixels[row][col];
              bottomLeftPixel = pixels[col][row];
              bottomLeftPixel.setColor(topRightPixel.getColor());
          }
      }
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
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
        count++;
      }
    }
    System.out.println(count);
  }
  
  //method that mirrors a part of a picture of arms
  public void mirrorArms()
  {
	  int mirrorPoint = 193;
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 158; row < mirrorPoint; row++)// loop through the rows
	  {
		  for (int col = 103; col < 170; col++)// loop through the column
		  {
			  //replicates the left arm at the mirror point
			  topPixel = pixels[row][col];      
			  bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
	  int mirrorPoint2 = 198;
	  Pixel topPixel2 = null;
	  Pixel bottomPixel2 = null;
	  for (int row = 171; row < mirrorPoint2; row++)// loop through the rows
	  {
		  for (int col = 239; col < 294; col++)// loop through the columns
		  {
			  //replicates the right arm at the mirror point
			  topPixel2 = pixels[row][col];      
			  bottomPixel2 = pixels[mirrorPoint2 - row + mirrorPoint2][col];
			  bottomPixel2.setColor(topPixel2.getColor());
		  }
	  }
  }
  
  //method that mirrors part part of a picture of a seagull
  public void mirrorGull()
  {
    int mirrorPoint = 344;
    Pixel rightPixel = null;
    Pixel leftPixel = null;
    Pixel[][] pixels = this.getPixels2D();   
    for (int row = 234; row < 322; row++)// loop through the rows
    {
      for (int col = 238; col < mirrorPoint; col++)// loop through the columns
      {
    	//mirrors seagull on the mirror point
        rightPixel = pixels[row][col];      
        leftPixel = pixels[row][mirrorPoint - col + mirrorPoint/3];
        leftPixel.setColor(rightPixel.getColor());
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
       System.out.println("Fromrow " + fromRow + " toRow " + toRow + " fromCol" + fromCol + " toCol " + toCol);
     }
   }   
 }
 
 //copies the picture from one place to another
 public void copyBruh(Picture fromPic, int startRow, int endRow,
		 int startCol, int endCol)
 {
   Pixel fromPixel = null;
   Pixel toPixel = null;
   Pixel[][] toPixels = this.getPixels2D();
   Pixel[][] fromPixels = fromPic.getPixels2D();
   for (int fromRow = 0, toRow = startRow; //goes through rows until the endrow that the user selected
        fromRow < fromPixels.length &&
        toRow < endRow;
        fromRow++, toRow++)
   {
     for (int fromCol = 0, toCol = startCol; //goes through col until the endcol that the user selected
          fromCol < fromPixels[0].length &&
          toCol < endCol;  
          fromCol++, toCol++)
     {
    	 //copies the picture from one place to the other
       fromPixel = fromPixels[fromRow][fromCol];
       toPixel = toPixels[toRow][toCol];
       toPixel.setColor(fromPixel.getColor());
     }
   } 
 }

 /** Method to create a collage of several pictures */
 public void createCollage()
 {
   Picture flower1 = new Picture("flower1.jpg");
   Picture flower2 = new Picture("flower2.jpg");
  //mirrors
   this.copyBruh(flower1,0,100, 0, 100);
   int mirrorPoint = 98;
   Pixel rightPixel = null;
   Pixel leftPixel = null;
   Pixel[][] pixels = this.getPixels2D();   
   for (int row = 0; row < 98; row++)
   {
     for (int col = 0; col < 88; col++)
     {
       rightPixel = pixels[row][col];      
       leftPixel = pixels[mirrorPoint - row + mirrorPoint][col];
       leftPixel.setColor(rightPixel.getColor());
     }
   }
   //flower no blue
   Picture flowerNoBlue = new Picture(flower2);
   flowerNoBlue.zeroBlue();
   this.copyBruh(flowerNoBlue,300,350,80,500);
   //flower negate
   Picture flowernegate = new Picture(flower2);
   flowernegate.negate();
   this.copyBruh(flowernegate, 100, 300, 80, 300);
   this.write("collage.jpg");
 }
 
 //method that makes the collage
 public void myCollage()
 {
	 //prints collage
     Picture flower1 = new Picture("flower1.jpg");
     this.copyBruh(flower1,10,20, 0, 100);
     this.write("mycollage.jpg");
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
  
  //method that checks for the edges of a picture
  public void edgeDetectionBruh(int edgeDist)
  {
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    
    //goes through picture in column major order
    for (int col = 0; col < pixels[0].length-1; col++)
    {
      for (int row = 0; row < pixels.length; row++)
      {
    	  /*sets the top pixel either white or black
    	  based on the bottom pixel
    	  */
        topPixel = pixels[row][col];
        bottomPixel = pixels[row][col+1];
        rightColor = bottomPixel.getColor();
        if (topPixel.colorDistance(rightColor) > 
            edgeDist)
          topPixel.setColor(Color.BLACK);
        else
          topPixel.setColor(Color.WHITE);
      }
    }
  }
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
