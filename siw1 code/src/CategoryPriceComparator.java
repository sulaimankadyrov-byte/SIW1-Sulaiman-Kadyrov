import java.util.Comparator;

public class CategoryPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product a, Product b) {
        int result = a.getCategory().compareTo(b.getCategory());
        if (result != 0) {
            return result;
        }
        return Double.compare(a.getPrice(), b.getPrice());
    }
}
