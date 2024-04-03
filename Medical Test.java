public class MedicalTestAnalyzer {

  public static Map<TestResultRange, TestResultStats> analyze(List<Double> results) {
    Map<TestResultRange, TestResultStats> resultsMap = new HashMap<>();
    for (double value : results) {
      TestResultRange range = getTestResultRange(value);
      TestResultStats stats = resultsMap.get(range);
      if (stats == null) {
        stats = new TestResultStats();
        resultsMap.put(range, stats);
      }
      stats.count++;
      stats.totalValue += value;
    }
    return resultsMap;
  }

  private static TestResultRange getTestResultRange(double value) {
    if (value < normalRangeLow) {
      return TestResultRange.LOW;
    } else if (value > normalRangeHigh) {
      return TestResultRange.HIGH;
    } else {
      return TestResultRange.NORMAL;
    }
  }

  static final double normalRangeLow = /* Define normal range low value */;
  static final double normalRangeHigh = /* Define normal range high value */;

  enum TestResultRange {
    LOW,
    NORMAL,
    HIGH
  }

  static class TestResultStats {
    int count;
    double totalValue;

    double getAverageValue() {
      return count > 0 ? totalValue / count : 0.0;
    }
  }
}
