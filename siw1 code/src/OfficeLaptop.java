public class OfficeLaptop extends Product implements Discountable {
    private int batteryHours;
    private boolean hasNumpad;

    public OfficeLaptop(int id, String name, double price, int quantity, int batteryHours, boolean hasNumpad) {
        super(id, name, price, quantity);
        this.batteryHours = 1;
        setBatteryHours(batteryHours);
        this.hasNumpad = hasNumpad;
    }

    public int getBatteryHours() {
        return batteryHours;
    }

    public void setBatteryHours(int batteryHours) {
        if (batteryHours > 0) {
            this.batteryHours = batteryHours;
        } else {
            System.out.println("Invalid battery hours");
        }
    }

    public boolean isHasNumpad() {
        return hasNumpad;
    }

    public void setHasNumpad(boolean hasNumpad) {
        this.hasNumpad = hasNumpad;
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() + 30;
    }

    @Override
    public String getCategory() {
        return "Office laptop";
    }

    @Override
    public double getDiscountPercent() {
        return 10;
    }

    @Override
    public double calculateDiscountedPrice() {
        return calculateFinalPrice() - calculateFinalPrice() * getDiscountPercent() / 100;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Battery: " + batteryHours + " h | Numpad: " + hasNumpad);
    }

    public void joinMeeting() {
        System.out.println(getName() + " joined an online meeting");
    }

    public boolean canWorkAllDay() {
        return batteryHours >= 8;
    }
}
