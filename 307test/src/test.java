
public class test{
	public static void main(String args[]) {
		/*
		A a = new A();
		B b = new B();
		a.change();
		b.change();
		System.out.println("POINT 1:");
		System.out.println(a.x);
		System.out.println(a.y);
		System.out.println(b.x);
		System.out.println(b.y);
		// Program point 1
		b = new B();
		a = b;
		a.change();
		// Program point 2
		System.out.println("POINT 2:");
		System.out.println(a.x);
		System.out.println(a.y);
		System.out.println(b.x);
		System.out.println(b.y);
		b.change();
		// Program point 3
		System.out.println("POINT 3:");
		System.out.println(a.x);
		System.out.println(a.y);
		System.out.println(b.x);
		System.out.println(b.y);
		*/
		Super a = new Super();
		Sub b = new Sub();
		
		System.out.println("Point 1");
		System.out.println(a.x);
		System.out.println(a.f());
		System.out.println(a.g());
		System.out.println(b.x);
		System.out.println(b.f());
		System.out.println(b.g());
		
		
		a=b;
		
		System.out.println("Point 2");
		System.out.println(a.x);
		System.out.println(a.f());
		System.out.println(a.g());
		System.out.println(b.x);
		System.out.println(b.f());
		System.out.println(b.g());
	}
}