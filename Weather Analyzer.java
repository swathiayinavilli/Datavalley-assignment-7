public class WeatherAnalyzer {

  public static Map<TemperatureRange, WeatherStats> analyze(List<WeatherData> data) {
    Map<TemperatureRange, WeatherStats> results = new HashMap<>();
    for (WeatherData entry : data) {
      TemperatureRange range = getTemperatureRange(entry.temperature);
      WeatherStats stats = results.get(range);
      if (stats == null) {
        stats = new WeatherStats();
        results.put(range, stats);
      }
      stats.count++;
      stats.totalHumidity += entry.humidity;
    }
    return results;
  }

  private static TemperatureRange getTemperatureRange(double temperature) {
    if (temperature < 0) {
      return TemperatureRange.BELOW_ZERO;
    } else if (temperature < 10) {
      return TemperatureRange.ZERO_TO_TEN;
    } else if (temperature < 20) {
      return TemperatureRange.TEN_TO_TWENTY;
    } else {
      return TemperatureRange.ABOVE_TWENTY;
    }
  }

  static class WeatherData {
    final double temperature;
    final double humidity;

    public WeatherData(double temperature, double humidity) {
      this.temperature = temperature;
      this.humidity = humidity;
    }
  }

  enum TemperatureRange {
    BELOW_ZERO,
    ZERO_TO_TEN,
    TEN_TO_TWENTY,
    ABOVE_TWENTY
  }

  static class WeatherStats {
    int count;
    double totalHumidity;

    double getAverageHumidity() {
      return count > 0 ? totalHumidity / count : 0.0;
    }
  }
}
