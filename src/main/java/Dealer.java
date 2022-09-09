import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int CARS = 10;
    private static final int GET_TIME = 2000;
    private static final int SELL_TIME = 1000;


    private final List<Car> cars = new ArrayList<>();

    public void getCar() {
        for (int i = 0; i < CARS; i++) {
            try {
                Thread.sleep(GET_TIME);
                cars.add(new Car());
                System.out.println(Thread.currentThread().getName() + " выпустил 1 автомобиль.");
                synchronized (cars) {
                    cars.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (cars.size() == 0) {
                System.out.println("нет доступных машин");
                synchronized (cars) {
                    cars.wait();
                }
            }

            Thread.sleep(SELL_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новом Subaru.");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
