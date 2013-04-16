package uk.uncodedstudios.uncode2d.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * A class to store information about a 2D Frame animation.
 * @author Figglewatts
 * @version 1.0 - 2013-04-13
 */
public class Frame2 {
	// the rectangle of the initial frame
	private Rectangle _initialFrameRect;
	
	// the number of frames in the animation
	private int _frameCount = 1;
	
	// the frame currently being displayed
	private int _currentFrame = 0; // this value can only be between 0 and _frameCount-1
	
	// the amount of time each frame is displayed for
	private float _frameLength = 0.2F;
	
	// the amount of time that has passed since we last animated
	private float _frameTimer = 0.0F;
	
	// the number of times this animation has been played
	private int _playCount = 0;
	
	// the animation to be played after this one
	private String _nextAnimation = null;
	
	public int getFrameCount()
	{
		return _frameCount;
	}
	
	public void setFrameCount(int frameCount)
	{
		_frameCount = frameCount;
	}
	
	public int getCurrentFrame()
	{
		return _currentFrame;
	}
	
	public void setCurrentFrame(int currentFrame)
	{
		_currentFrame = currentFrame;
	}
	
	public int getFrameWidth()
	{
		return (int)_initialFrameRect.width;
	}
	
	public int getFrameHeight()
	{
		return (int)_initialFrameRect.height;
	}
	
	public Rectangle FrameRectangle()
	{
		return new Rectangle(_initialFrameRect.x + (_initialFrameRect.width * _currentFrame), _initialFrameRect.y, _initialFrameRect.width, _initialFrameRect.height);
	}
	
	public float getFrameLength()
	{
		return _frameLength;
	}
	
	public void setFrameLength(float frameLength)
	{
		_frameLength = frameLength;
	}
	
	public int getPlayCount()
	{
		return _playCount;
	}
	
	public void setPlayCount(int playCount)
	{
		_playCount = playCount;
	}
	
	public String getNextAnimation()
	{
		return _nextAnimation;
	}
	
	public void setNextAnimation(String nextAnimation)
	{
		_nextAnimation = nextAnimation;
	}
	
	public int frameWidth()
	{
		return (int)_initialFrameRect.width;
	}
	public int frameHeight()
	{
		return (int)_initialFrameRect.height;
	}
	
	/**
	 * Creates a frame animation.
	 * @param firstFrame		A Rectangle which details the size and position of the first frame.
	 * @param frames			The amount of frames in the animation.
	 */
	public Frame2(Rectangle firstFrame, int frames)
	{
		this._initialFrameRect = firstFrame;
		this._frameCount = frames;
	}
	
	/**
	 * Creates a frame animation.
	 * @param x					The X position of the animation.
	 * @param y					The Y position of the animation.
	 * @param w					The Width of the animation.
	 * @param h					The Height of the animation.
	 * @param frames			The amount of frames in the animation.
	 */
	public Frame2(int x, int y, int w, int h, int frames)
	{
		this._initialFrameRect = new Rectangle(x, y, w, h);
		this._frameCount = frames;
	}
	
	/**
	 * Creates a frame animation.
	 * @param x					The X position of the animation.
	 * @param y					The Y position of the animation.
	 * @param w					The Width of the animation.
	 * @param h					The Height of the animation.
	 * @param frames			The amount of frames in the animation.
	 * @param frameLength		The length of each frame in the animation.
	 */
	public Frame2(int x, int y, int w, int h, int frames, float frameLength)
	{
		this._initialFrameRect = new Rectangle(x, y, w, h);
		this._frameCount = frames;
		this._frameLength = frameLength;
	}
	
	/**
	 * Creates a frame animation.
	 * @param x					The X position of the animation.
	 * @param y					The Y position of the animation.
	 * @param w					The Width of the animation.
	 * @param h					The Height of the animation.
	 * @param frames			The amount of frames in the animation.
	 * @param frameLength		The length of each frame in the animation.
	 * @param nextAnimation		The name of the next animation to play.
	 */
	public Frame2(int x, int y, int w, int h, int frames, float frameLength, String nextAnimation)
	{
		this._initialFrameRect = new Rectangle(x, y, w, h);
		this._frameCount = frames;
		this._frameLength = frameLength;
		this._nextAnimation = nextAnimation;
	}
	
	public void Update()
	{
		this._frameTimer += Gdx.graphics.getDeltaTime();
		
		if (this._frameTimer > this._frameLength)
		{
			this._frameTimer = 0.0F;
			this._currentFrame = (this._currentFrame + 1) % this._frameCount;
			if (this._currentFrame == 0)
				this._playCount = Math.min(this._playCount + 1, Integer.MAX_VALUE);
		}
	}
}
