package cn.jssd.planegame;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author jssd 陨石类
 */
public class Ceraunite extends GameObject {

	private MyGameFrame myGameFrame;

	/**
	 * 默认构造函数
	 * 
	 * @param img
	 * @param x
	 * @param y
	 */
	public Ceraunite(Image img, int x, int y) {
		super(img, x, y);
	}

	/**
	 * @param myGameFrame
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public Ceraunite(MyGameFrame myGameFrame, Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		this.myGameFrame = myGameFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jssd.planegame.GameObject#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) {
		super.draw(g);
		move();
		outOfBound();
		impact();
	}

	/**
	 * void 自己移动
	 */
	private void move() {
		this.setX(getX() + getSpeed());
		this.setY(getY() + Math.abs(getSpeed()));
	}

	/**
	 * void 边界检测
	 */
	private void outOfBound() {
		if (this.getX() <= 0) {
			this.setX(0);
			this.setSpeed(-getSpeed());
		}
		if (this.getX() + this.getWidth() >= Constant.WIDTH) {
			this.setX(Constant.WIDTH - this.getWidth());
			this.setSpeed(-getSpeed());
		}
		if (this.getY() >= Constant.HEIGHT) {
			myGameFrame.ceraunites.remove(this);
		}
	}

	/**
	 * 碰撞检测
	 *  void
	 */
	private void impact() {
		if (myGameFrame.plane.live && this.getRectangle().intersects(myGameFrame.plane.getRectangle())) {
			new MusicUtil("cn/jssd/planegame/music/over.mp3", false).start();
			myGameFrame.plane.live = false;
		}
	}

}
