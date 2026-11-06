import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        return Double.compare(a.getPrice(), b.getPrice());
    }
}
