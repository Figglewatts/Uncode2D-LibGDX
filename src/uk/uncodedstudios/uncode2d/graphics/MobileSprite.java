package uk.uncodedstudios.uncode2d.graphics;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MobileSprite extends AnimatedSprite {
	// the sprite object that holds the graphical data for this sprite
	Sprite asSprite;
	
	// the queue of pathing vectors which will allow the sprite to move along a path
	Queue<Vector2> _queuePath = new LinkedList<Vector2>();
	
	// the location the sprite is currently moving towards
	Vector2 _target;
	
	// the speed at which the sprite will close with it's target
	float _speed = 1.0F;
	
	// these 2 integers represent a clipping range for determining bounding-box style
	// collisions. They return the bounding box of the sprite trimmed by a horizontal
	// and vertical offset to get a collision cushion.
	int _collisionBufferX = 0;
	int _collisionBufferY = 0;
	
	// the status of the sprite, an inactive sprite will not be updated but will be drawn
	boolean _active = true;
	
	// should the sprite track towards a target (Vector2)?
	// if set to false, the sprite will not move on it's own towards the target and
	// will not process pathing information
	boolean _movingTowardsTarget = true;
	
	// should the sprite follow the path in it's path queue?
	// if true, when the sprite has reached it's target the next path node will be pulled
	// from the queue and set as the next target
	boolean _pathing = true;
	
	// if true, any pathing node popped from the queue will be placed at the end of the queue
	boolean _loopPath = true;
	
	// if true, the sprite will collide with other objects. Note that this is only provided
	// as a flag for testing with outside code.
	boolean _collidable = true;
	
	// if true, the sprite will be drawn to the screen
	boolean _visible = true;
	
	// if true, the sprite will be deactivated if the pathing queue is empty
	boolean _deactivateAtEndOfPath = false;
	
	// if true, _visible will be set to false if the pathing queue is empty
	boolean _hideAtEndOfPath = false;
	
	// if set, when the pathing queue is empty, the named animation will be
	// set as the current animation on the sprite
	String _endPathAnimation = null;
	
	public Sprite getSprite()
	{
		return asSprite;
	}
	
	public Vector2 getTarget()
	{
		return _target;
	}
	
	public void setTarget(Vector2 target)
	{
		_target = target;
	}
	
	public int getHorizontalCollisionBuffer()
	{
		return _collisionBufferX;
	}
	
	public void setHorizontalCollisionBuffer(int collisionBufferX)
	{
		_collisionBufferX = collisionBufferX;
	}
	
	public int getVerticalCollisionBuffer()
	{
		return _collisionBufferY;
	}
	
	public void setVerticalCollisionBuffer(int collisionBufferY)
	{
		_collisionBufferY = collisionBufferY;
	}
	
	public boolean getPathing()
	{
		return _pathing;
	}
	
	public void setPathing(boolean pathing)
	{
		_pathing = pathing;
	}
	
	public boolean getLoopPath()
	{
		return _loopPath;
	}
	
	public void setLoopPath(boolean loopPath)
	{
		_loopPath = loopPath;
	}
	
	public boolean getCollidable()
	{
		return _collidable;
	}
	
	public void setCollidable(boolean collidable)
	{
		_collidable = collidable;
	}
	
	public boolean getVisible()
	{
		return _visible;
	}
	
	public void setVisible(boolean visible)
	{
		_visible = visible;
	}
	
	public boolean getDeactivateAtEndOfPath()
	{
		return _deactivateAtEndOfPath;
	}
	
	public void setDeactivateAtEndOfPath(boolean deactivateAtEndOfPath)
	{
		_deactivateAtEndOfPath = deactivateAtEndOfPath;
	}
	
	public boolean getHideAtEndOfPath()
	{
		return _hideAtEndOfPath;
	}
	
	public void setHideAtEndOfPath(boolean hideAtEndOfPath)
	{
		_hideAtEndOfPath = hideAtEndOfPath;
	}
	
	public String getEndPathAnimation()
	{
		return _endPathAnimation;
	}
	
	public void setEndPathAnimation(String endPathAnimation)
	{
		_endPathAnimation = endPathAnimation;
	}
	
	public boolean getActive()
	{
		return _active;
	}
	
	public void setActive(boolean active)
	{
		_active = active;
	}
	
	public boolean getMovingTowardsTarget()
	{
		return _movingTowardsTarget;
	}
	
	public void setMovingTowardsTarget(boolean movingTowardsTarget)
	{
		_movingTowardsTarget = movingTowardsTarget;
	}
	
	public float getSpeed()
	{
		return _speed;
	}
	
	public void setSpeed(float speed)
	{
		_speed = speed;
	}
	
	public Rectangle getRectangle()
	{
		return asSprite.getSpriteRect();
	}
	
	public MobileSprite(Texture tex, Rectangle rect)
	{
		super(tex, rect);
	}
	public MobileSprite(Texture tex, int x, int y)
	{
		super(tex, x, y);
	}
	public MobileSprite(Texture tex, int x, int y, int w, int h)
	{
		super(tex, x, y, w, h);
	}
	
	public void AddPathNode(Vector2 node)
	{
		_queuePath.add(node);
	}
	public void AddPathNode(int x, int y)
	{
		_queuePath.add(new Vector2(x, y));
	}
	public Vector2 GetPathNode()
	{
		return _queuePath.peek();
	}
	
	public void ClearPathNodes()
	{
		_queuePath.clear();
	}
	
	public void Update()
	{
		if (_active && _movingTowardsTarget)
		{
			if (_target != null)
			{
				// get a vector pointing from the current location of the sprite to a new position
				Vector2 Delta = new Vector2((_target.x - asSprite.getSpritePosX()), (_target.y - asSprite.getSpritePosY()));
				if (Delta.len() > _speed)
				{
					Delta.nor();
					Delta.mul(_speed);
					asSprite.setSpritePosition(asSprite.getSpritePosition().add(Delta));
				}
				else
				{
					if (_target == asSprite.getSpritePosition())
					{
						if (_pathing)
						{
							if (_queuePath.size() > 0)
							{
								_target = _queuePath.poll();
								if (_loopPath)
								{
									_queuePath.add(_target);
								}
							}
							else
							{
								if (_endPathAnimation != null)
								{
									if (super.getCurrentAnimationAsString() != _endPathAnimation)
									{
										super.setCurrentAnimationAsString(_endPathAnimation);
									}
								}
								
								if (_deactivateAtEndOfPath)
								{
									_active = false;
								}
								
								if (_hideAtEndOfPath)
								{
									_visible = false;
								}
							}
						}
					}
					else
					{
						asSprite.setSpritePosition(_target);
					}
				}
			}
		}
		if (_active)
		{
			super.Update();
		}
	}
	
	public void Draw(SpriteBatch batch)
	{
		if (_visible)
		{
			super.Draw(batch);
		}
	}
}
