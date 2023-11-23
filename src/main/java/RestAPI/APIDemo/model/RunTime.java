package RestAPI.APIDemo.model;

public class RunTime extends Test {

    public void test1(){
        System.out.println("Printing from overridden method");

    }
    public static void main(String[] args) {
        Test test = new Test();

        test.test1();

    }

}

class Test {
    public void test1(){
        System.out.println("Printing from test 1");
    }
}
