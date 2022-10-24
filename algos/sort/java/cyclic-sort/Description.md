## Cyclic sort Java example

leet-code 448

```java
// {4, 3, 2, 7, 8, 2, 3, 1} -> {5, 6}
// {1, 1} -> 2

// n == nums.length
// 1 <= n <= 10^5
// 1 <= nums[i] <= n

public class CyclicSort {

    public static void main(String[] args) {

        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};

        // 7, 3, 2, 4, 8, 2, 3, 1 (i = 0)
        // 3, 3, 2, 4, 8, 2, 7, 1 (i = 0)
        // 2, 3, 3, 4, 8, 2, 7, 1 (i = 0)
        // 3, 2, 3, 4, 8, 2, 7, 1 (i = 1)
        // 3, 2, 3, 4, 8, 2, 7, 1 (i = 2)
        // 3, 2, 3, 4, 8, 2, 7, 1 (i = 3)
        // 3, 2, 3, 4, 1, 2, 7, 8 (i = 4)
        // 1, 2, 3, 4, 3, 2, 7, 8 (i = 5)
        // 1, 2, 3, 4, 3, 2, 7, 8 (i = 6)

        int i = 0;
        // Cyclic sort
        while (i < nums.length) {
            int pos = nums[i] - 1;
            if (nums[i] != nums[pos]) {
                nums[i] = nums[i] + nums[pos];
                nums[pos] = nums[i] - nums[pos];
                nums[i] -= nums[pos];
            } else
                i++;
        }
        i = 0;
        Arrays.stream(nums).forEach(value -> System.out.print(value + " "));
        List<Integer> result = new ArrayList<>();
        while (i < nums.length) {
            if (nums[i] != i + 1)
                result.add(i + 1);
            i++;
        }
        System.out.println();
        result.forEach(value -> System.out.print(value + " "));
    }
}
```
