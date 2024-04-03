public class HousingPriceAnalyzer {

  public static Map<PriceRange, HousingStats> analyze(List<Double> prices, List<Double> squareFootages) {
    if (prices.size() != squareFootages.size()) {
      throw new IllegalArgumentException("Prices and square footages must have the same size");
    }
    Map<PriceRange, HousingStats> results = new HashMap<>();
    for (int i = 0; i < prices.size(); i++) {
      double price = prices.get(i);
      double sqFootage = squareFootages.get(i);
      PriceRange range = getPriceRange(price);
      HousingStats stats = results.get(range);
      if (stats == null) {
        stats = new HousingStats();
        results.put(range, stats);
      }
      stats.count++;
      stats.totalPrice += price;
      stats.totalSqFootage += sqFootage;
    }
    return results;
  }

  private static PriceRange getPriceRange(double price) {
    int rangeStart = (int) (price / 100000) * 100000;
    return new PriceRange(rangeStart, rangeStart + 100000);
  }

  static class PriceRange {
    final int startPrice;
    final int endPrice;

    public PriceRange(int startPrice, int endPrice) {
      this.startPrice = startPrice;
      this.endPrice = endPrice;
    }

    @Override
    public String toString() {
      return "$" + startPrice + " - $" + endPrice;
    }
  }

  static class HousingStats {
    int count;
    double totalPrice;
    double totalSqFootage;

    double getAveragePrice() {
      return count > 0 ? totalPrice / count : 0.0;
    }

    double getAverageSqFootage() {
      return count > 0 ? totalSqFootage / count : 0.0;
    }
  }
}
