package HomeWork7;

public class TestClass2 {

    @BeforeSuite
    void start() {
        System.out.println("Начало теста");

    }

    @AfterSuite
    void stop1() {
        System.out.println("Окончание теста");
    }

    @AfterSuite
    void stop() {
        System.out.println("Окончание теста");
    }

    @Test(priority = 1)
    void test1() {
        System.out.println("test1: приоритет 1");
    }

    @Test(priority = 0)
    void test2() {
        System.out.println("test2: приоритет 0");
    }

    @Test(priority = 2)
    void test3() {
        System.out.println("test3: приоритет 2");
    }


    @Test(priority = 0)
    void test4() {
        System.out.println("test4: приоритет 0");
    }


}
