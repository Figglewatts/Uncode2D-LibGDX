package uk.uncodedstudios.uncode2d.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

/**
 * A class containing variables to set up a sprite.
 * @author Figglewatts
 * @version 1.0 - 2013-04-12
 */
public class Sprite {
	private Texture _spriteTex;
	private Rectangle _spriteRect;
	private int _spritePosX;
	private int _spritePosY;
	private int _spriteWidth;
	private int _spriteHeight;
	private Vector2 _spriteVelocity;
	private Vector2 _spritePosition;
	private Vector2 _spriteLastPosition;
	private Vector2 _spriteCenter;
	
	/**
	 * Gets the texture of a sprite.
	 * @return The Texture of the sprite.
	 */
	public Texture getSpriteTex()
	{
		return _spriteTex;
	}
	
	/**
	 * Sets the texture of a sprite.
	 * @param tex	The texture to set it to.
	 */
	public void setSpriteTex(Texture tex)
	{
		_spriteTex = tex;
	}
	
	/**
	 * Gets a sprite's rectangle.
	 * @return The rectangle of the sprite.
	 */
	public Rectangle getSpriteRect()
	{
		return _spriteRect;
	}
	
	/**
	 * Sets a sprite's rectangle.
	 * @param rect	The rectangle to set it to.
	 */
	public void setSpriteRect(Rectangle rect)
	{
		_spriteRect = rect;
	}
	
	/**
	 * Sets a sprite's rectangle.
	 * @param x		The X position of the rectangle.
	 * @param y 	The Y position of the rectangle.
	 * @param w		The Width of the rectangle.
	 * @param h		The Height of the rectangle.
	 */
	public void setSpriteRect(int x, int y, int w, int h)
	{
		_spriteRect = new Rectangle(x, y, w, h);
	}
	
	/**
	 * Gets the X position of a sprite.
	 * @return An int containing the X position of the sprite.
	 */
	public int getSpritePosX()
	{
		return _spritePosX;
	}
	
	/**
	 * Sets the X position of a sprite.
	 * @param x		The X position to set it to.
	 */
	public void setSpritePosX(int x)
	{
		_spritePosX = x;
	}
	
	/**
	 * Gets the Y position of a sprite.
	 * @return An int containing the Y position of the sprite.
	 */
	public int getSpritePosY()
	{
		return _spritePosY;
	}
	
	/**
	 * Sets the Y position of a sprite.
	 * @param y		The Y position to set it to.
	 */
	public void setSpritePosY(int y)
	{
		_spritePosY = y;
	}
	
	/**
	 * Gets a sprite's width.
	 * @return An int containing the width of a sprite.
	 */
	public int getSpriteWidth()
	{
		return _spriteWidth;
	}
	
	/**
	 * Sets the width of a sprite.
	 * @param w		The width to set it to.
	 */
	public void setSpriteWidth(int w)
	{
		_spriteWidth = w;
	}
	
	/**
	 * Gets a sprite's height.
	 * @return An int containing the height of a sprite.
	 */
	public int getSpriteHeight()
	{
		return _spriteHeight;
	}
	
	/**
	 * Sets the height of a sprite.
	 * @param h		The height to set it to.
	 */
	public void setSpriteHeight(int h)
	{
		_spriteHeight = h;
	}
	
	/**
	 * Gets the velocity of a sprite as a Vector2.
	 * @return The sprite's velocity.
	 */
	public Vector2 getSpriteVelocity()
	{
		return _spriteVelocity;
	}
	
	public void setSpriteVelocity(Vector2 v2)
	{
		_spriteVelocity = v2;
	}
	
	public Vector2 getSpritePosition()
	{
		return _spritePosition;
	}
	
	public void setSpritePosition(Vector2 v2)
	{
		_spriteLastPosition = _spritePosition;
		_spritePosition = v2;
	}
	
	public Vector2 getSpriteLastPosition()
	{
		return _spriteLastPosition;
	}
	
	public Vector2 getSpriteCenter()
	{
		return new Vector2(this._spriteWidth / 2, this._spriteHeight / 2);
	}
	
	public void setSpriteCenter(Vector2 center)
	{
		_spriteCenter = center;
	}
	
	/**
	 * Creates a sprite with a texture and a rectangle.
	 * @param tex		The texture of the sprite.
	 * @param rect		The rectangle of the sprite.
	 */
	public Sprite(Texture tex, Rectangle rect)
	{
		this._spriteTex = tex;
		this._spriteRect = rect;
		this._spritePosX = (int)_spriteRect.x;
		this._spritePosY = (int)_spriteRect.y;
		this._spriteWidth = (int)_spriteRect.width;
		this._spriteHeight = (int)_spriteRect.height;
		this._spriteCenter = new Vector2(_spritePosX + (_spriteWidth / 2), _spritePosY + (_spriteHeight / 2));
	}
	
	/**
	 * Creates a sprite with a texture and an X and Y position. The dimensions are the textures width and height by default.
	 * @param tex		The texture of the sprite.
	 * @param x			The X position of the sprite.
	 * @param y			The Y position of the sprite.
	 */
	public Sprite(Texture tex, int x, int y)
	{
		this._spriteTex = tex;
		this._spriteRect = new Rectangle(x, y, tex.getWidth(), tex.getHeight());
		this._spritePosX = x;
		this._spritePosY = y;
		this._spriteWidth = tex.getWidth();
		this._spriteHeight = tex.getHeight();
		this._spriteCenter = new Vector2(_spritePosX + (_spriteWidth / 2), _spritePosY + (_spriteHeight / 2));
	}
	
	/**
	 * Creates a sprite with a texture and 4 values containing the X, Y, Width and Height.
	 * @param tex		The texture of the sprite.
	 * @param x			The X position of the sprite.
	 * @param y			The Y position of the sprite.
	 * @param w			The width of the sprite.
	 * @param h			The height of the sprite.
	 */
	public Sprite(Texture tex, int x, int y, int w, int h)
	{
		this._spriteTex = tex;
		this._spriteRect = new Rectangle(x, y, w, h);
		this._spritePosX = x;
		this._spritePosY = y;
		this._spriteWidth = w;
		this._spriteHeight = h;
		this._spriteCenter = new Vector2(_spritePosX + (_spriteWidth / 2), _spritePosY + (_spriteHeight / 2));
	}
	
	public void Draw(SpriteBatch batch)
	{
		batch.draw(this._spriteTex, this._spriteRect.x, this._spriteRect.y, this._spriteRect.width, this._spriteRect.height);
	}
}
