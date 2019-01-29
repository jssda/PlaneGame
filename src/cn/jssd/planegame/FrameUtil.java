/**
 * 窗口继承类
 * 包含窗口中重画, 双缓冲处理, 窗口标题, 关闭监听等方法
 */
package cn.jssd.planegame;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author jssd
 *
 */
public class FrameUtil extends Frame {

	private static final long serialVersionUID = 1L;

	/**
	 * 初始化主窗口 void
	 */
	public void launchFrame() {
		this.setTitle("我的飞机大战小游戏"); // 设置标题
		this.setSize(Constant.WIDTH, Constant.HEIGHT); // 设置大小
		this.setVisible(true); // 设置可见性
		this.setLocationRelativeTo(null);// 相对屏幕居中

		// 窗口关闭监听器
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		new RepaintThread().start(); // 启动重画线程

	}

	// 双缓冲解决图片闪烁
	private Image offScreenImage = null;

	public void update(Graphics g) {
		if (offScreenImage == null)
			offScreenImage = this.createImage(605, 576);// 这是游戏窗口的宽度和高度

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	/**
	 * @author jssd 重画线程
	 */
	class RepaintThread extends Thread {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
