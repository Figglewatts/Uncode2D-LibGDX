package uk.uncodedstudios.uncode2d.graphics;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import uk.uncodedstudios.uncode2d.animation.Frame2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A Sprite with animations.
 * @author Figglewatts
 * @version 1.0 - 2013-04-13
 */
public class AnimatedSprite extends Sprite {

	// TODO: add javadocs
	private boolean _isAnimating; // if true, an animation is currently being played
	private Map<String, Frame2> _animations = new Hashtable<String, Frame2>(); // a Hashtable to hold all the animations of this sprite
	private String _currentAnimation; // a string containing the current animation being played
	
	/**
	 * @return True if the sprite is currently animating.
	 */
	public boolean getIsAnimating()
	{
		return _isAnimating;
	}
	
	/**
	 * @param isAnimating			Is the sprite animating?
	 */
	public void setIsAnimating(boolean isAnimating)
	{
		_isAnimating = isAnimating;
	}
	
	/**
	 * @return The Frame2 object of the currently playing animation.
	 */
	public Frame2 getCurrentAnimation()
	{
		if(_currentAnimation != null) // if the current animation is not null
			return _animations.get(_currentAnimation); // return the corresponding Hashtable value
		else
			return null;
	}
	
	/**
	 * @return The current animation the sprite is doing as a String.
	 */
	public String getCurrentAnimationAsString()
	{
		return _currentAnimation;
	}
	
	/**
	 * @param currentAnimation			The name of the animation you want to set.
	 */
	public void setCurrentAnimationAsString(String currentAnimation)
	{
		if (_animations.containsKey(currentAnimation))
		{
			_currentAnimation = currentAnimation;
			_animations.get(currentAnimation).setCurrentFrame(0);
			_animations.get(currentAnimation).setPlayCount(0);
		}
	}
	
	/**
	 * Adds an animation to the Hashtable.
	 * @param name			The name of the animation to add.
	 * @param x				The X position of the animation to add.
	 * @param y				The Y position of the animation to add.
	 * @param w				The width of the animation to add.
	 * @param frames		The amount of frames in the animation to add.
	 * @param frameLength	The amount of time each frame should be displayed for.
	 */
	public void addAnimation(String name, int x, int y, int w, int h, int frames, float frameLength)
	{
		_animations.put(name, new Frame2(x, y, w, h, frames, frameLength));
		super.setSpriteWidth(w);
		super.setSpriteHeight(h);
		super.setSpriteCenter(new Vector2(super.getSpriteWidth() / 2, super.getSpriteHeight() / 2));
	}
	
	/**
	 * Adds an animation to the Hashtable.
	 * @param name			The name of the animation to add.
	 * @param x				The X position of the animation to add.
	 * @param y				The Y position of the animation to add.
	 * @param w				The width of the animation to add.
	 * @param frames		The amount of frames in the animation to add.
	 * @param frameLength	The amount of time each frame should be displayed for.
	 * @param nextAnimation	The name of the animation which plays after this one.
	 */
	public void addAnimation(String name, int x, int y, int w, int h, int frames, float frameLength, String nextAnimation)
	{
		_animations.put(name, new Frame2(x, y, w, h, frames, frameLength, nextAnimation));
		super.setSpriteWidth(w);
		super.setSpriteHeight(h);
		super.setSpriteCenter(new Vector2(super.getSpriteWidth() / 2, super.getSpriteHeight() / 2));
	}
	
	/**
	 * Gets the Frame2 object of an animation by it's name, if it exists.
	 * @param name			The name of the animation to get.
	 * @return The Frame2 value associated with the animation name key.
	 */
	public Frame2 getAnimationByName(String name)
	{
		if (_animations.containsKey(name))
			return _animations.get(name);
		else
			return null;
	}
	
	/**
	 * Creates an animated sprite with a texture and a rectangle.
	 * @param tex		The texture of the sprite.
	 * @param rect		The rectangle of the sprite.
	 */
	public AnimatedSprite(Texture tex, Rectangle rect) 
	{
		super(tex, rect);
	}
	
	/**
	 * Creates an animated sprite with a texture and an X and Y position. The dimensions are the textures width and height by default.
	 * @param tex		The texture of the sprite.
	 * @param x			The X position of the sprite.
	 * @param y			The Y position of the sprite.
	 */
	public AnimatedSprite(Texture tex, int x, int y) 
	{
		super(tex, x, y);
	}
	
	/**
	 * Creates an animated sprite with a texture and 4 values containing the X, Y, Width and Height.
	 * @param tex		The texture of the sprite.
	 * @param x			The X position of the sprite.
	 * @param y			The Y position of the sprite.
	 * @param w			The width of the sprite.
	 * @param h			The height of the sprite.
	 */
	public AnimatedSprite(Texture tex, int x, int y, int w, int h)
	{
		super(tex, x, y, w, h);
	}
	
	public void Update()
	{
		// don't do anything if the sprite is not animating
		if (_isAnimating)
		{
			// if there is not a currently active animation
			if (_currentAnimation == null)
			{
				// sets the active animation to the first animation associated with this sprite
				@SuppressWarnings("unused")
				String[] sKeys = new String[_animations.size()];
				Set<String> sKeysSet = new HashSet<String>(_animations.keySet());
				sKeys = (String[])sKeysSet.toArray();
			}
			else
			{
				return;
			}
		}
		
		// run the animations update method
		this.getCurrentAnimation().Update();
		
		// check to see if there is a followup animation for this animation
		if (this.getCurrentAnimation().getNextAnimation() != null)
		{
			// if there is, see if the currently playing animation has completed a full loop
			if (this.getCurrentAnimation().getPlayCount() > 0)
			{
				// if it has, set up the next animation
				_currentAnimation = this.getCurrentAnimation().getNextAnimation();
			}
		}
	}
	
	public void Draw(SpriteBatch batch)
	{
		batch.draw(this.getSpriteTex(), this.getSpritePosX(), this.getSpritePosY(), 
				(int)this.getCurrentAnimation().FrameRectangle().x, 
				(int)this.getCurrentAnimation().FrameRectangle().y, 
				(int)this.getCurrentAnimation().FrameRectangle().width, 
				(int)this.getCurrentAnimation().FrameRectangle().height);
	}
}
