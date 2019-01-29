/**
 * 
 */
package cn.jssd.planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * @author jssd
 *
 */
/**
 * @author jssd
 *
 */
/**
 * @author jssd
 *
 */
/**
 * @author jssd
 *
 */
public class GameObject {
	private Image img; // 此物品对应的图片
	private int speed; // 速度
	private int x; // x轴坐标
	private int y; // y轴坐标
	private int width;
	private int height; //所在矩形的宽和高

	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public GameObject(Image img, int x, int y) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 10;
	}
	
	/**
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public GameObject(Image img, int x, int y, int speed) {
		this(img, x, y);
		this.setSpeed(speed);
	}
	/**
	 * 取得图片矩形
	 * @return Rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * 画出自己
	 * @param g void
	 */
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	public Image getImg() {
		return img;
	}

	public int getSpeed() {
		return speed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
