import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprite {
	
	public BufferedImage img;
	public int twidth;
	public int theight;

	public Sprite(int Width, int Height, String FName) {
		// Load an image from disk, set its height and width fields.
		try {
		    img = ImageIO.read(new File(FName));
		} catch (IOException e) {
		}
		this.theight = Height;
		this.twidth = Width;
	}
	
	public void draw( Graphics g, int x, int y, int frame ) {
		if ( img != null ) {
			int tile_x = ( frame % ( img.getWidth() / twidth ) ) * twidth;
			int tile_y = ( frame / ( img.getWidth() / twidth ) ) * theight;;
			BufferedImage subimg = this.img.getSubimage( tile_x, tile_y, this.twidth , this.theight );
			if (subimg != null ) {
				g.drawImage(subimg, x, y, null );
			} else {
				g.setColor(Color.PINK);
				g.fillRect(x, y, 32, 32);
			}
		} else {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, 32, 32);
		}
	}
}
