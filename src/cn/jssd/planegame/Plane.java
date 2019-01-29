package cn.jssd.planegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * @author jssd
 *
 */
public class Plane extends GameObject{
	public MyGameFrame myGameFrame;
	private boolean left; //是否向左走
	private boolean right;
	private boolean up, down;
	public boolean shoot; //是否开始射击
	public boolean live; //是否存活

	/* (non-Javadoc)
	 * @see cn.jssd.planegame.GameObject#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		if(live) {
			super.draw(g);
			shoot();
			move();
		} else {
			Color c = g.getColor();
			Font font = g.getFont();
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("微软雅黑", Font.BOLD, 20));
			g.drawString("GAME OVER", Constant.WIDTH / 2 - 50, Constant.HEIGHT / 2);
			
			g.setColor(c);
			g.setFont(font);
		}
	}
	
	/**
	 * 构造含税
	 * @param myGameFrame
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public Plane(MyGameFrame myGameFrame, Image img, int x, int y, int speed) {
		super(img, x, y);
		this.myGameFrame = myGameFrame;
		this.setSpeed(speed);
		this.live = true;
	}
	
	/**
	 * 人物移动事件检测
	 *  void
	 */
	public void move() {
		if(left) {
			this.setX(this.getX() - this.getSpeed());
		}
		if(right) {
			this.setX(this.getX() + this.getSpeed());
		}
		if(up) {
			this.setY(this.getY() - this.getSpeed());
		}
		if(down) {
			this.setY(this.getY() + this.getSpeed());
		}
		outOfBound();
	}
	
	/**
	 * 边界检测
	 *  void
	 */
	public void outOfBound() {
		if(this.getX() > Constant.WIDTH - this.getWidth() - 8) { //是否碰撞到右边界
			setX(Constant.WIDTH - this.getWidth() - 8);
		}
		if(this.getX() < 8) { //是否碰撞到左边界
			setX(8);
		}
		if(this.getY() < 40) { //检测碰撞到上边界
			setY(40);
		}
		if(this.getY() > Constant.HEIGHT - this.getHeight() - 8) {
			setY(Constant.HEIGHT - this.getHeight() - 8);
		}
		
	}
	
	private int countOfShoot = 0; //射击计时器
	
	/**
	 * 射击方法
	 *  void
	 */
	public void shoot() {
		if(shoot) {
			if(countOfShoot % 7 == 0) {
				myGameFrame.bullets.add(new Bullet(myGameFrame, GameUtil.getImage("cn/jssd/planegame/img/bullet.png"), getX() + 7, getY() - 20));
			}
			countOfShoot ++;
		}
	}
	
	/**
	 * 按键松开事件检测
	 * @param KeyEvent void
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_J:
			shoot = true;
			break;
		default:
			break;
		}
	}
	
	/**
	 * 按键按下事件检测
	 * @param e void
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_J:
			shoot = false;
			countOfShoot = 0;
			break;
		default:
			break;
		}
	}
}
