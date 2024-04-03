public class EmployeeWorkHoursAnalyzer {

  public static WorkHoursStats analyze(List<Integer> workHours) {
    int over40 = 0, exactly40 = 0, under40 = 0;
    double totalHoursOver40 = 0, totalHoursExactly40 = 0, totalHoursUnder40 = 0;
    for (int hours : workHours) {
      if (hours > 40) {
        over40++;
        totalHoursOver40 += hours;
      } else if (hours == 40) {
        exactly40++;
        totalHoursExactly40 += hours;
      } else {
        under40++;
        totalHoursUnder40 += hours;
      }
    }
    return new WorkHoursStats(over40, exactly40, under40, 
        getAverageHours(totalHoursOver40, over40), 
        getAverageHours(totalHoursExactly40, exactly40), 
        getAverageHours(totalHoursUnder40, under40));
  }

  private static double getAverageHours(double totalHours, int count) {
    return count > 0 ? totalHours / count : 0.0;
  }

  static class WorkHoursStats {
    final int over40Hours;
    final int exactly40Hours;
    final int under40Hours;
    final double averageHoursOver40;
    final double averageHoursExactly40;
    final double averageHoursUnder40;

    public WorkHoursStats(int over40Hours, int exactly40Hours, int under40Hours, 
        double averageHoursOver40, double averageHoursExactly40, double averageHoursUnder40) {
      this.over40Hours = over40Hours;
      this.exactly40Hours = exactly40Hours;
      this.under40Hours = under40Hours;
      this.averageHoursOver40 = averageHoursOver40;
      this.averageHoursExactly40 = averageHoursExactly40;
      this.averageHoursUnder40 = averageHoursUnder40;
    }
  }
}


