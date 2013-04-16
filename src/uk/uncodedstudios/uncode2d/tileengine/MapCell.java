package uk.uncodedstudios.uncode2d.tileengine;

import com.badlogic.gdx.math.Rectangle;

public class MapCell {
	
	private Rectangle _boundingBox;
	private int _tileID;
	private boolean _isSolid;
	private int _tilePosX;
	private int _tilePosY;
	
	public Rectangle getBoundingBox()
	{
		return _boundingBox;
	}
	
	public void setBoundingBox(Rectangle boundingBox)
	{
		_boundingBox = boundingBox;
	}
	
	public int getTileID()
	{
		return _tileID;
	}
	
	public void setTileID(int tileID)
	{
		_tileID = tileID;
	}
	
	public boolean getIsSolid()
	{
		return _isSolid;
	}
	
	public void setIsSolid(boolean isSolid)
	{
		_isSolid = isSolid;
	}
	
	public int getTilePosX()
	{
		return _tilePosX;
	}
	
	public void setTilePosX(int tilePosX)
	{
		_tilePosX = tilePosX;
	}
	
	public int getTilePosY()
	{
		return _tilePosY;
	}
	
	public void setTilePosY(int tilePosY)
	{
		_tilePosY = tilePosY;
	}
	
	public MapCell(int tileID)
	{
		_tileID = tileID;
	}
	
	public MapCell(int tileID, boolean isSolid)
	{
		_tileID = tileID;
		_isSolid = isSolid;
	}
}
