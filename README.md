Project Description

This Java program is purpose-built to identify the largest 2-dimensional sub-array from within a specified 2D array.
The computation process follows this rule: it triples every positive number and doubles every negative number or zero.
If all values are negative, the program returns an empty sub-array.
The application utilizes a variant of the Kadane's algorithm to locate the maximum subarray.

Working of Algorithm:

1. Populate the 2D array with data provided by the user.

2. Iterate through the 2D array.

3. Upon reaching each row, create a temporary 1D array. If a positive number is encountered in the 2D array,
   it is tripled before being added to the temporary array. Conversely, if the number is either negative or zero,
   its doubled value is appended to the temporary array.

4. Use Kadane's algorithm to ascertain the maximum subarray sum. In addition, it also fetches the start and end indices of the subarray.

5. Monitor the highest maximum sum and the associated indices.

6. Finally, print the maximum sum as well as its respective indices, providing users with easy-to-understand output.

Complexity Analysis

Time Complexity:
The time complexity of the algorithm is O((n+m)³), 'n' standing for the number of rows and 'm'
being the number of columns in the 2D array. The reason being, iterating up and down the rows has a complexity of O(n),
and within that iteration, we perform an operation (applying Kadane's algorithm) which itself has a complexity of O(m).
Thus, the overall time complexity equates to the cube of the sum of the number of rows and columns.

Space Complexity:
The space complexity of the implementing software is O(m). It stems from the fact that we've used a 1D
temporary array whose length equals the number of columns in the 2D array.
