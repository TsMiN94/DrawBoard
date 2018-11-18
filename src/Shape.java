
 public class Shape {
	 protected int sx,sy,ex,ey;
	 protected String shape;
	 private Shape a = this;

	 public void setting(int a,int b,int c,int d,String shape)
	 {
		 this.sx = a;
		 this.sy = b;
		 this.ex = c;
		 this.ey = d;
		 this.shape = shape;
	 }
//	 public Shape getAllPoint() {
//		 return a;
//	 }
	 
//	 public void setShape(String shape) {
//		 this.shape = shape;
//	 }
	 public String getShape() {
		 return shape;
	 }

	public int getSx() {
		return sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public int getEx() {
		return ex;
	}

	public void setEx(int ex) {
		this.ex = ex;
	}

	public int getEy() {
		return ey;
	}

	public void setEy(int ey) {
		this.ey = ey;
	}

	public Shape getA() {
		return a;
	}

	public void setA(Shape a) {
		this.a = a;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	 
}
