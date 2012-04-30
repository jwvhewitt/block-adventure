import java.awt.Graphics;


public class LevelMap {
	
	public static final int TERR_CLEAR = 0;
	public static final int TERR_OBSTACLE = 1;
	public static final int TERR_PLATFORM = 2;

	public static final int MAP_WIDTH = 200;
	public static final int MAP_HEIGHT = 50;
	public static final int MAP_TILE_WIDTH = 32;
	public static final int MAP_TILE_HEIGHT = 32;
	
	public static final int[] TERR_DEFINITION = {
		0,
		1,2,0,0,1, 0,1,1,1,0,
		0,0,0,0,0, 0,0,0,1,1,
		0,0,0,0,0, 0,0,0,0,0,
		0,2,2,0,0, 0,0,0,0,2
	};
	
	public int[][] map;
	public Sprite mysprite;
	
	public LevelMap( String image_file ) {
		// Start by dimensioning the array.
		this.map = new int[ MAP_WIDTH ][ MAP_HEIGHT ];
		for(int x=0; x<MAP_WIDTH; x++){
			for(int y=0; y<(MAP_HEIGHT-1); y++){
				this.map[x][y] = TERR_CLEAR;
			}
		}
		for(int x=0; x<MAP_WIDTH; x++){
			this.map[x][MAP_HEIGHT-1] = TERR_OBSTACLE;
		}
		
		mysprite = new Sprite( MAP_TILE_WIDTH , MAP_TILE_HEIGHT , image_file );
	}
	
	public void draw( Graphics g ) {
		for(int x=0; x<MAP_WIDTH; x++){
			for(int y=0; y<MAP_HEIGHT; y++){
				mysprite.draw( g, x*MAP_TILE_WIDTH, y*MAP_TILE_WIDTH, map[x][y]);
			}
		}
	}
	
	private int getMapX( int x ) {
		return x / MAP_TILE_WIDTH;
	}

	private int getMapY( int y ) {
		return y / MAP_TILE_HEIGHT;
	}
	
	public boolean isOnTheMap( int tx , int ty ) {
		return (tx >= 0 ) && (ty >= 0) && (tx<MAP_WIDTH) && (ty<MAP_HEIGHT);
	}

	public boolean isAnObstacle( int x , int y ) {
		int tx = getMapX( x );
		int ty = getMapY( y );
		if ( this.isOnTheMap( tx , ty ) ) {
			return TERR_DEFINITION[ this.map[tx][ty] ] == TERR_OBSTACLE; 
		} else {
			return true;
		}
	}
	
	public boolean isAPlatform( int x , int y ) {
		int tx = getMapX( x );
		int ty = getMapY( y );
		if ( this.isOnTheMap( tx , ty ) ) {
			return TERR_DEFINITION[ this.map[tx][ty] ] == TERR_PLATFORM; 
		} else {
			return true;
		}
	}

	
}
