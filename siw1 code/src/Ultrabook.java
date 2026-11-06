public class Ultrabook extends Product implements Shippable {
    private double weightKg;
    private double screenSize;

    public Ultrabook(int id, String name, double price, int quantity, double weightKg, double screenSize) {
        super(id, name, price, quantity);
        this.weightKg = 1;
        this.screenSize = 13;
        setWeightKg(weightKg);
        setScreenSize(screenSize);
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        if (weightKg > 0) {
            this.weightKg = weightKg;
        } else {
            System.out.println("Invalid weight");
        }
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        if (screenSize > 0) {
            this.screenSize = screenSize;
        } else {
            System.out.println("Invalid screen size");
        }
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() + 50;
    }

    @Override
    public String getCategory() {
        return "Ultrabook";
    }

    @Override
    public double calculateShippingCost() {
        return 15;
    }

    @Override
    public int getDeliveryDays() {
        return 2;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Weight: " + weightKg + " kg | Screen: " + screenSize + " inch");
    }

    public void carryInBag() {
        System.out.println(getName() + " is easy to carry in a bag");
    }

    public boolean isLight() {
        return weightKg < 1.5;
    }
}
