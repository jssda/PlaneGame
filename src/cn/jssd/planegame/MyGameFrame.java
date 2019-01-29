/**
 * 
 */
package cn.jssd.planegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jssd 飞机大战游戏主窗口
 */
public class MyGameFrame extends FrameUtil {

	private static final long serialVersionUID = 1L;

	// 加载飞机
	Plane plane = new Plane(this, GameUtil.getImage("cn/jssd/planegame/img/plane.png"), 300, 480, 15);// 初始化飞机

	// 加载背景
	Image bg = GameUtil.getImage("cn/jssd/planegame/img/bg.png");

	// 子弹容器
	List<Bullet> bullets = new ArrayList<Bullet>();

	// 刷新陨石
	List<Ceraunite> ceraunites = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		Font f = g.getFont();

		// 画背景
		g.drawImage(bg, 0, 0, null);

		// 画飞机
		plane.draw(g);

		// 画子弹
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}

		// 画陨石
		for (int i = 0; i < ceraunites.size(); i++) {
			ceraunites.get(i).draw(g);
		}

		g.setFont(f);
		g.setColor(c);
	}

	public void loadFrame() {
		super.launchFrame();
		new MusicUtil("cn/jssd/planegame/music/bg.mp3", true).start();

		new FlashThread().start();
		
		// 添加按键检测监听
		this.addKeyListener(new KeyAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});

	}

	/**
	 * @author jssd 刷新陨石线程
	 */
	class FlashThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Random R = new Random();
				int position = R.nextInt(Constant.WIDTH - 40);
				MyGameFrame.this.ceraunites
						.add(new Ceraunite(MyGameFrame.this, GameUtil.getImage("cn/jssd/planegame/img/ceraunite.png"),
								position, 50, 10 * (int) Math.pow(-1, position)));
//				new MusicUtil("cn/jssd/planegame/music/down.mp3").start();
			}
		}
	}

	/**
	 * 主方法
	 */
	public static void main(String[] args) {
		new MyGameFrame().loadFrame();
	}
}
