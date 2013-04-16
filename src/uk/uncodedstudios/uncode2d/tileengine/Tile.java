package uk.uncodedstudios.uncode2d.tileengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
	
	private Tile() { }
	
	public static Texture TileSetTexture;
	public static int TileWidth = 16; // the width of the tile in pixels
	public static int TileHeight = 16; // the height of the tile in pixels
	public static int RenderTileWidth = 16; // the width the tile will be drawn as
	public static int RenderTileHeight = 16; // the height the tile will be drawn as
	
	public static Rectangle GetSourceRectangle(int tileIndex)
	{
		int tileY = tileIndex / (TileSetTexture.getHeight() / TileHeight);
		int tileX = tileIndex % (TileSetTexture.getWidth() / TileWidth);
		
		return new Rectangle(tileX * TileWidth, tileY * TileHeight, TileWidth, TileHeight);
	}
}
