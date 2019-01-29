/**
 * 
 */
package cn.jssd.planegame;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 子弹类
 * 
 * @author jssd
 */
public class Bullet extends GameObject {

	public MyGameFrame myGameFrame;

	/**
	 * 默认构造函数
	 * 
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public Bullet(MyGameFrame myGameFrame, Image img, int x, int y) {
		super(img, x, y);
		this.setSpeed(20);
		this.myGameFrame = myGameFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jssd.planegame.GameObject#draw(java.awt.Graphics) 画自己
	 */
	public void draw(Graphics g) {
		super.draw(g);
		this.move();
		impact();
		this.outOfBound();
	}

	/**
	 * 子弹射出之后移动 void
	 */
	public void move() {
		this.setY(this.getY() - this.getSpeed());
	}

	// 边界检测
	public void outOfBound() {
		if (this.getY() < 0) {
			myGameFrame.bullets.remove(this);
		}
	}

	/**
	 * 撞击检测
	 *  void
	 */
	public void impact() {
		for (int i = 0; i < myGameFrame.ceraunites.size(); i++) {
			if (this.getRectangle().intersects(myGameFrame.ceraunites.get(i).getRectangle())) {
				new MusicUtil("cn/jssd/planegame/music/explosion_enemy.mp3").start();
				myGameFrame.ceraunites.remove(i);
				myGameFrame.bullets.remove(this);
			}
		}
	}

}
