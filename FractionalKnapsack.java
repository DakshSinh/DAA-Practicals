import java.util.Arrays;
import java.util.Comparator;

// Item class to represent an item with weight and value
class Item {
  int weight;
  int value;

  // Constructor
  Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }
}

// Comparator class to sort items by value/weight ratio
class ValueWeightRatioComparator implements Comparator<Item> {
  @Override
  public int compare(Item item1, Item item2) {
    double ratio1 = (double) item1.value / item1.weight;
    double ratio2 = (double) item2.value / item2.weight;
    return Double.compare(ratio2, ratio1); // Descending order
  }
}

public class FractionalKnapsack {
  // Function to solve fractional knapsack using largest profit approach
  static double knapsackLargestProfit(Item[] items, int capacity) {
    Arrays.sort(items, new ValueWeightRatioComparator());
    double totalValue = 0;

    for (Item item : items) {
      if (capacity >= item.weight) {
        totalValue += item.value;
        capacity -= item.weight;
      } else {
        totalValue += (double) item.value / item.weight * capacity;
        break;
      }
    }
    return totalValue;
  }

  // Function to solve fractional knapsack using smallest weight approach
  static double knapsackSmallestWeight(Item[] items, int capacity) {
    Arrays.sort(items, Comparator.comparingInt(item -> item.weight));
    double totalValue = 0;

    for (Item item : items) {
      if (capacity >= item.weight) {
        totalValue += item.value;
        capacity -= item.weight;
      } else {
        totalValue += (double) item.value / item.weight * capacity;
        break;
      }
    }
    return totalValue;
  }

  // Function to solve fractional knapsack using balance approach
  static double knapsackBalance(Item[] items, int capacity) {
    Arrays.sort(items, new ValueWeightRatioComparator());
    double totalValue = 0;

    for (Item item : items) {
      if (capacity >= item.weight) {
        totalValue += item.value;
        capacity -= item.weight;
      } else {
        totalValue += (double) item.value / item.weight * capacity;
        break;
      }
    }
    return totalValue;
  }

  public static void main(String[] args) {
    Item[] items = {
        new Item(10, 60),
        new Item(20, 100),
        new Item(30, 120),
        new Item(40, 150),
        new Item(50, 200)
    };
    int capacity = 70;

    // Largest Profit approach
    double largestProfit = knapsackLargestProfit(items.clone(), capacity);
    System.out.println("Using Largest Profit approach, maximum value: " + largestProfit);

    // Smallest Weight approach
    double smallestWeight = knapsackSmallestWeight(items.clone(), capacity);
    System.out.println("Using Smallest Weight approach, maximum value: " + smallestWeight);

    // Balance approach
    double balance = knapsackBalance(items.clone(), capacity);
    System.out.println("Using Balance approach, maximum value: " + balance);
  }
}
