package uk.uncodedstudios.uncode2d.tileengine;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import uk.uncodedstudios.uncode2d.camera.Camera;

public class Level {
	public List<MapRow> Rows = new ArrayList<MapRow>();
	
	public List<Integer> SolidTiles = new ArrayList<Integer>();
	
	public int MapWidth = 1000;
	public int MapHeight = 1000;
	
	public Level()
	{
		for (int y = 0; y < MapHeight; y++)
		{
			MapRow thisRow = new MapRow();
			for (int x = 0; x < MapWidth; x++)
			{
				thisRow.Columns.add(new MapCell(0));
			}
			Rows.add(thisRow);
		}
	}
	
	public void Draw(SpriteBatch batch, Level myMap, int squaresDown, int squaresAcross)
	{
		Vector2 firstSquare = new Vector2(Camera.Location.x / Tile.RenderTileWidth, Camera.Location.y / Tile.RenderTileHeight);
		int firstX = (int)firstSquare.x;
		int firstY = (int)firstSquare.y;
		
		Vector2 squareOffset = new Vector2(Camera.Location.x % Tile.RenderTileWidth, Camera.Location.y / Tile.RenderTileHeight);
		int offsetX = (int)squareOffset.x;
		int offsetY = (int)squareOffset.y;
		
		for (int y = 0; y < squaresDown; y++)
		{
			for (int x = 0; x < squaresDown; x++)
			{
				// LONGEST DRAW METHOD EVER
				batch.draw(Tile.TileSetTexture, 
				(float)((x * Tile.RenderTileWidth) - offsetX), 
				(float)((Gdx.graphics.getHeight() - Tile.RenderTileHeight) - ((y * Tile.RenderTileHeight) - offsetY)), // the extra stuff here makes it render to y-down coords
				(float)Tile.RenderTileWidth, 
				(float)Tile.RenderTileHeight,
				(int)Tile.GetSourceRectangle(myMap.Rows.get(y+firstY).Columns.get(x+firstX).getTileID()).x,
				(int)Tile.GetSourceRectangle(myMap.Rows.get(y+firstY).Columns.get(x+firstX).getTileID()).y,
				(int)Tile.GetSourceRectangle(myMap.Rows.get(y+firstY).Columns.get(x+firstX).getTileID()).width,
				(int)Tile.GetSourceRectangle(myMap.Rows.get(y+firstY).Columns.get(x+firstX).getTileID()).height,
				false, false);
			}
		}
	}
	
	public class MapRow {
		public List<MapCell> Columns = new ArrayList<MapCell>();
	}
}
