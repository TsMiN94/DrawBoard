import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GraphicEditor extends JFrame {
	private Vector<Shape> v ;
	private Vector<Shape> v2 = new Vector<Shape>();
	private DrawPanel dp;
	private String select;
	private Point p;
	private Line line;
	private Circle circle;
	private Rect rect;
	private int sx, sy;
	private Shape object, remove;
	private JsonParser jp;
	public GraphicEditor() {
		setTitle("�׷��� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		MyMouse myAdapter = new MyMouse();
		setContentPane(c);
		c.setLayout(null);
		v = new Vector<Shape>();
		JButton lineBt = new JButton("line");
		lineBt.setSize(250, 150);
		lineBt.setLocation(50, 50);
		lineBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.setShape("line");
				System.out.println("line�� ����!");
			}
		});

		JButton circleBt = new JButton("circle");
		circleBt.setSize(250, 150);
		circleBt.setLocation(320, 50);
		circleBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.setShape("circle");
				System.out.println("circle�� ����!");
			}
		});

		JButton rectBt = new JButton("rect");
		rectBt.setSize(250, 150);
		rectBt.setLocation(590, 50);
		rectBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.setShape("rect");
				System.out.println("rect�� ����!");
			}
		});

		JButton save = new JButton("save");
		save.setSize(120, 75);
		save.setLocation(860, 50);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��� �׸� ��ü���� �����մϴ�.");
				jp = new JsonParser();
				jp.openWriter();
				jp.save(dp.getV());
			}
		});
		
		JButton load = new JButton("load");
		load.setSize(120, 75);
		load.setLocation(990, 50);
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp = new JsonParser();
				jp.openReader();
				v = jp.load();
				dp.setV(v);
				System.out.println("����� ��ü���� �ҷ��ɴϴ�.");	
				dp.repaint();
			}
		});
		
		JButton rm = new JButton("reomove");
		rm.setSize(250, 75);
		rm.setLocation(860, 130);
		rm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("����� ���� ������ �����ϼ���");
				dp.setShape("remove");
			}
		});

		dp = new DrawPanel();
		dp.setLocation(50, 250);
		dp.addMouseListener(myAdapter);
		dp.addMouseMotionListener(myAdapter);

		c.add(lineBt);
		c.add(circleBt);
		c.add(rectBt);
		c.add(save);
		c.add(rm);
		c.add(load);
		add(dp);
		dp.repaint();

		setSize(1200, 700);
		setVisible(true);
	}

	class MyMouse extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) { // ������ �� ��ü�����
			if (dp.getShape().equals("line")) {
				line = new Line();
				object = line;
			}
			if (dp.getShape().equals("circle")) {
				circle = new Circle();
				object = circle;
			}
			if (dp.getShape().equals("rect")) {
				rect = new Rect();
				object = rect;
			}
			if (dp.getShape().equals("remove")) {
				remove = new Shape();
				object = remove;
			}
			if(dp.getShape() .equals(""))
			{
				System.out.println("� ���� �׸��� �����ϼ���");
				return ;
			}
			v2.add(object);

			p = new Point();
			p.setLocation(e.getX(), e.getY());
			sx = e.getX();
			sy = e.getY();
			dp.setStartp(sx, sy);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if( v2.size() == 0 ) 
				return ;
			p.setLocation(e.getX(), e.getY());
			dp.setPoint(p);
		}

		@Override
		public void mouseReleased(MouseEvent e) { // ���콺
			if(v2.size() == 0)return ;
			dp.setObj(v2.get(0)); // ���콺�� ���� �� �������� ���������� ���� ��ü�� ������ �ο��ϰ� HashMap �� ����
			System.out.println(dp.getShape() + "�� �׷Ƚ��ϴ�.");
			v2.removeAllElements();
			dp.repaint(); //
		}

	}

	public static void main(String[] args) {
		new GraphicEditor();
	}

}
