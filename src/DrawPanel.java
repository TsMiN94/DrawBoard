import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DrawPanel extends JPanel {
	private String shape, shapeT;
	private Shape obj, t;
	private Point p;
	private DrawPanel dp = this;
	private Vector<Shape> v = new Vector<Shape>();
	private int sx, sy, ex, ey, tmpx, tmpy, tmpx2, tmpy2, count;

	public DrawPanel() {
		ex = ey = 0;
		this.shape = "";
		count = 0;
		setLayout(null);
		setBackground(Color.WHITE);

		setSize(1060, 370);
	}

	public void setShape(String shape) {
		this.shape = shape;
		
	}

	public String getShape() {
		return shape;
	}

	public void setPoint(Point p) {
		this.p = p;
		repaint();
	}

	public void setStartp(int sx, int sy) {
		this.sx = sx;
		this.sy = sy;
	}
	public Vector<Shape> getV(){
		return v;
	}
	public void setObj(Shape obj) { // ����ڰ� �ֱٿ� ���� ��ü�� ������ HashMap�� ����
		this.obj = obj;

		if (shape.equals("line"))
			this.obj.setting(sx, sy, (int) p.getX(), (int) p.getY(), this.shape);

		else if (shape.equals("circle"))
			this.obj.setting(tmpx, tmpy, ex, ey, this.shape);

		else if (shape.equals("rect"))
			this.obj.setting(tmpx2, tmpy2, ex, ey, this.shape);

		else if (shape.equals("remove"))
			this.obj.setting(tmpx2, tmpy2, ex, ey, this.shape);
		
		v.add(this.obj);
		System.out.println("������ " + this.shape + "��ü�� HashMap�� �����߽��ϴ�.");
		repaint();
	}

	public Shape getObj() {
		return this.obj;
	}
	public void setV(Vector<Shape> v) {
		this.v =v;
		this.shape = v.get(0).getShape();
		p =  new Point();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // ���ο�

		if (shape != null && p!=null) {

			switch (this.shape) {
			case "line":
				g.drawLine(sx, sy, (int) p.getX(), (int) p.getY());

				shapeT = this.shape;

				for (int i = 0; i < v.size(); i++) {
					t = v.get(i);
					this.shape = t.getShape();
					
					/*
					 * 
					 * ����� shape�� ���� �׸��� �׸���.
					 * 
					 */
					g.drawLine(sx, sy, (int) p.getX(), (int) p.getY());

					if (this.shape.equals("line")) {
						// System.out.println("LINE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawLine(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("circle")) {
						// System.out.println("CIRCLE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawOval(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("rect")) {
						// System.out.println("RECT " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawRect(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("remove")) {
						g.setColor(Color.white);
						g.fillRect(t.sx, t.sy, t.ex, t.ey);
						g.setColor(Color.BLACK);
					}
				}
				this.shape = shapeT; // while�� �ȿ��� ���� ���� shape �� �ٲ��ֱ� ������ ������� �ٽ� �ٲ��ش�
				
				break;
			case "circle":
				tmpx = sx;
				tmpy = sy;
				ex = (int) p.getX() - sx;
				ey = (int) p.getY() - sy;
				if (sx > p.getX()) {
					ex = Math.abs(ex);
					tmpx = sx - ex;
				}
				if (sy > p.getY()) {
					ey = Math.abs(ey);
					tmpy = sy - ey;
				}

				g.drawOval(tmpx, tmpy, ex, ey);
				shapeT = this.shape;

				for (int i = 0; i < v.size(); i++) {

					t = v.get(i);
					this.shape = t.getShape();

					/*
					 * 
					 * ����� shape�� ���� �׸��� �׸���.
					 * 
					 */
					g.drawOval(tmpx, tmpy, ex, ey);
					if (this.shape.equals("line")) {
						// System.out.println("LINE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawLine(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("circle")) {
						// System.out.println("CIRCLE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawOval(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("rect")) {
						// System.out.println("RECT " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawRect(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("remove")) {
						g.setColor(Color.white);
						g.fillRect(t.sx, t.sy, t.ex, t.ey);
						g.setColor(Color.BLACK);
					}
				}
				this.shape = shapeT; // while�� �ȿ��� ���� ���� shape �� �ٲ��ֱ� ������ ������� �ٽ� �ٲ��ش�.
			
				break;
			case "rect":
				tmpx2 = sx;
				tmpy2 = sy;
				ex = (int) p.getX() - sx;
				ey = (int) p.getY() - sy;
				if (sx > p.getX()) {
					ex = Math.abs(ex);
					tmpx2 = sx - ex;
				}
				if (sy > p.getY()) {
					ey = Math.abs(ey);
					tmpy2 = sy - ey;
				}
				
				g.drawRect(tmpx2, tmpy2, ex, ey);
				shapeT = this.shape;

				for (int i = 0; i < v.size(); i++) {
					t = v.get(i);
					this.shape = t.getShape();

					/*
					 * 
					 * ����� shape�� ���� �׸��� �׸���.
					 * 
					 */
					g.drawRect(tmpx2, tmpy2, ex, ey);
					if (this.shape.equals("line")) {
						// System.out.println("LINE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawLine(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("circle")) {
						// System.out.println("CIRCLE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawOval(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("rect")) {
						// System.out.println("RECT " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawRect(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("remove")) {
						g.setColor(Color.white);
						g.fillRect(t.sx, t.sy, t.ex, t.ey);
						g.setColor(Color.BLACK);
					}
				}
				this.shape = shapeT; // while�� �ȿ��� ���� ���� shape �� �ٲ��ֱ� ������ ������� �ٽ� �ٲ��ش�.
			
				break;
			case "remove":

				tmpx2 = sx;
				tmpy2 = sy;
				ex = (int) p.getX() - sx;
				ey = (int) p.getY() - sy;
				if (sx > p.getX()) {
					ex = Math.abs(ex);
					tmpx2 = sx - ex;
				}
				if (sy > p.getY()) {
					ey = Math.abs(ey);
					tmpy2 = sy - ey;
				}
				shapeT = this.shape;
				g.setColor(Color.BLACK);
			
				for (int i = 0; i < v.size(); i++) {
					t = v.get(i);
					this.shape = t.getShape();
					/*
					 * 
					 * ����� shape�� ���� �׸��� �׸���.
					 * 
					 */

					if (this.shape.equals("line")) {
						// System.out.println("LINE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawLine(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("circle")) {
						// System.out.println("CIRCLE " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawOval(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("rect")) {
						// System.out.println("RECT " + t.sx + " " + t.sy + " " + t.ex + " " + t.ey);
						g.drawRect(t.sx, t.sy, t.ex, t.ey);
					}
					if (this.shape.equals("remove")) {
						g.setColor(Color.white);
						g.fillRect(t.sx, t.sy, t.ex, t.ey);
						g.setColor(Color.BLACK);
					}
				}
				this.shape = shapeT; // while�� �ȿ��� ���� ���� shape �� �ٲ��ֱ� ������ ������� �ٽ� �ٲ��ش�.
				g.setColor(Color.white);
				g.fillRect(tmpx2, tmpy2, ex, ey);
	
				break;
			}

		}
		
	}

}
