public class Main {

    /**
     * Write a function that takes an array of numbers (integers for the tests) and a target number.
     * It should find two different items in the array that, when added together, give the target value.
     * The indices of these items should then be returned in a tuple / list (depending on your language)
     * like so: (index1, index2).
     *
     */

    public static int[] twoSum(int[] numbers, int target) {
        boolean isFind = false;
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target){
                    result[0] = i;
                    result[1] = j;
                    isFind = true;
                    break;
                }
                if(isFind) {
                    break;
                }
            }
        }
        return result;
    }


}
