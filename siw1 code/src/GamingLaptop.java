public class GamingLaptop extends Product implements Discountable, Shippable {
    private String gpu;
    private int refreshRate;

    public GamingLaptop(int id, String name, double price, int quantity, String gpu, int refreshRate) {
        super(id, name, price, quantity);
        this.gpu = "Unknown";
        this.refreshRate = 60;
        setGpu(gpu);
        setRefreshRate(refreshRate);
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        if (gpu != null && !gpu.isEmpty()) {
            this.gpu = gpu;
        } else {
            System.out.println("Invalid GPU");
        }
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        if (refreshRate > 0) {
            this.refreshRate = refreshRate;
        } else {
            System.out.println("Invalid refresh rate");
        }
    }

    @Override
    public double calculateFinalPrice() {
        return getPrice() + 100;
    }

    @Override
    public String getCategory() {
        return "Gaming laptop";
    }

    @Override
    public double getDiscountPercent() {
        return 5;
    }

    @Override
    public double calculateDiscountedPrice() {
        return calculateFinalPrice() - calculateFinalPrice() * getDiscountPercent() / 100;
    }

    @Override
    public double calculateShippingCost() {
        return 40;
    }

    @Override
    public int getDeliveryDays() {
        return 3;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   GPU: " + gpu + " | Refresh rate: " + refreshRate + " Hz");
    }

    public void playGame(String game) {
        System.out.println(getName() + " is running " + game + " on " + gpu);
    }

    public boolean isHighRefresh() {
        return refreshRate >= 144;
    }
}
