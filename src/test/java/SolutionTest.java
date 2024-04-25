import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int[] nums = {1, 4, 2, 7};
        int low = 2;
        int high = 6;
        int expected = 6;
        int actual = new Solution().countPairs(nums, low, high);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        int[] nums = {9, 8, 4, 2, 1};
        int low = 5;
        int high = 14;
        int expected = 8;
        int actual = new Solution().countPairs(nums, low, high);

        Assert.assertEquals(expected, actual);
    }
}
