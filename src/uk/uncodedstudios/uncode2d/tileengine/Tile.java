package uk.uncodedstudios.uncode2d.tileengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
	
	private Tile() { }
	
	/**
	 * The texture to use for the tileset
	 */
	public static Texture TileSetTexture; // TODO: Set up support for multiple tilesets
	/**
	 * The width of each tile in pixels.
	 */
	public static int TileWidth = 16; // the width of the tile in pixels
	/**
	 * The height of each tile in pixels.
	 */
	public static int TileHeight = 16; // the height of the tile in pixels
	/**
	 * The width in pixels that each tile will be drawn in (twice the size of TileWidth makes the
	 * tiles be drawn 2x wider).
	 */
	public static int RenderTileWidth = 16;
	/**
	 * The height in pixels that each tile will be drawn in (twice the size of TileHeight makes the 
	 * tiles be drawn 2x higher).
	 */
	public static int RenderTileHeight = 16;
	
	/**
	 * Gets the rectangle in which the corresponding tile is located on the TileSetTexture.
	 * @param tileIndex			The ID of the tile to get the source rectangle of.
	 */
	public static Rectangle GetSourceRectangle(int tileIndex)
	{
		int tileY = tileIndex / (TileSetTexture.getHeight() / TileHeight);
		int tileX = tileIndex % (TileSetTexture.getWidth() / TileWidth);
		
		return new Rectangle(tileX * TileWidth, tileY * TileHeight, TileWidth, TileHeight);
	}
}
