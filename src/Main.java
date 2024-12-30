import java.util.Scanner;

class Kadane {

    int start;
    int end;
    int maxSum;

    Kadane () {
        start = 0;
        end = 0;
        maxSum = 0;
    }

    Kadane ( int s, int e, int M ) {
        start = s;
        end = e;
        maxSum = M;
    }

    Kadane kadane ( int tmp [] ) {
        int maxSum = -1;
        int maxSart = 0;
        int maxEnd = 0;
        int currMaxSum = 0;
        int currStart = 0;
        int currEnd = 0;
        int maxLen = tmp.length + 1;


        for ( int i = 0; i < tmp.length; ++i ) {
            if ( currMaxSum <= 0 ) {
                currMaxSum = tmp [ i ];
                currStart = i;
                currEnd = i;
            } else {
                currMaxSum += tmp [ i ];
                currEnd = i;
            }
            if ( ( currMaxSum > maxSum ) || ( ( currMaxSum == maxSum ) && ( ( currEnd - currStart + 1 ) < maxLen ) ) ) {
                maxSum = currMaxSum;
                maxSart = currStart;
                maxEnd = currEnd;
                maxLen = currEnd - currStart + 1;
            }
        }
        return new Kadane( maxSart, maxEnd, maxSum );
    }

}


class Source {


    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int number = scanner.nextInt();

        while ( number --> 0 ) {
            int id = scanner.nextInt();
            char chr = scanner.next().charAt(0);

            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            int negative = 0;
            boolean inserted = false;

            int[][] matrix = new int [ rows ][ columns ];
            for ( int i = 0; i < rows; i++ ) {
                for ( int j = 0; j < columns; j++ ) {
                    matrix[i][j] = scanner.nextInt();
                    if ( matrix [i][j] < 0 )
                        negative++;
                }
            }
            if ( negative == rows * columns ) {
                System.out.println( id + ": ms_tab is empty");
                continue;
            }

            int maxSum = 0;
            int mLeft = 0;
            int mRight = 0;
            int mUp = 0;
            int mDown = 0;

            int[] tmp = new int[columns];
            Kadane results = new Kadane();
            Kadane k = new Kadane();

            for (int up = 0; up < rows; up++) {
                for ( int i = 0; i < columns; i++ ) {
                    tmp[i] = 0;
                }

                for (int down = up; down < rows; down++) {
                    for (int i = 0; i < columns; i++) {

                        if (matrix[down][i] > 0) {
                            tmp[i] += 3 * matrix[down][i];
                        } else {
                            tmp[i] += 2 * matrix[down][i];
                        }
                    }
                    k = k.kadane(tmp);

                    int first =  ( mDown - mUp + 1 ) * (mRight - mLeft + 1);
                    int second = ( down - up + 1 ) * (k.end - k.start + 1);


                    if ((!inserted && k.maxSum == 0) || (k.maxSum > results.maxSum) || ((k.maxSum == results.maxSum) && ( second < first ))) {
                        inserted = true;
                        results = k;
                        maxSum = k.maxSum;
                        mUp = up;
                        mDown = down;
                        mLeft = k.start;
                        mRight = k.end;
                    }

                }
            }
            System.out.println ( id + ": ms_tab = a[" + mUp + ".." + mDown + "][" + mLeft + ".." + mRight + "], msum=" + maxSum );
        }
        scanner.close();
    }
}
// tests
// input
//1 : 8 7
//4 0 3 0 -1 1 -3
//-2 0 -3 -1 -4 2 3
//-4 4 -3 3 0 -2 2
//-2 -3 3 -3 -4 0 0
//4 4 -3 1 0 4 -2
//-1 -2 -3 4 1 -4 2
//-1 2 1 1 1 -1 4
//2 0 0 -3 2 3 1
//2 : 7 7
//3 1 2 2 2 0 4
//4 1 -3 0 4 -2 0
//0 -2 2 -2 -2 2 2
//4 -1 -3 3 2 -4 -3
//-2 -4 4 2 -1 -1 -2
//-2 4 -1 -4 0 0 0
//-4 2 0 3 -4 3 -4
//3 : 4 1
//-2
//-4
//4
//4
//4 : 9 3
//-4 -1 -2
//-3 -1 -3
//-4 1 0
//-3 4 0
//4 -3 4
//1 1 -2
//0 0 -1
//-1 2 3
//5 : 6 2
//0 0
//3 0
//0 -2
//0 -2
//4 4
//-4 -2
//6 : 5 2
//3 3
//-4 -2
//2 4
//-4 -3
//-1 0
//7 : 8 7
//2 -2 -1 -4 3 1 -1
//2 -2 0 2 3 3 4
//0 0 4 3 0 0 4
//-3 0 2 1 0 3 -2
//0 4 0 -3 4 4 3
//4 -4 -4 0 1 -1 -1
//-4 -1 3 3 2 -2 1
//0 -2 -4 -4 -4 -2 0
//8 : 7 9
//-4 2 -1 4 3 2 1 0 1
//2 1 -4 3 2 -2 -4 -4 0
//4 1 4 2 4 -1 0 3 1
//2 0 3 -4 -2 3 3 0 -3
//-2 -3 3 4 -1 -3 -4 -4 -2
//-4 -3 -2 -2 2 -1 1 -1 -2
//9 : 7 3
//2 1 3
//-1 0 3
//0 -1 -4
//1 -3 1
//-2 3 2
//1 1 -2
// output
//1: ms_tab = a[0..7][0..6], msum=76
//2: ms_tab = a[0..3][0..6], msum=70
//3: ms_tab = a[2..3][0..0], msum=24
//4: ms_tab = a[2..8][1..2], msum=42
//5: ms_tab = a[1..4][0..1], msum=25
//6: ms_tab = a[0..2][0..1], msum=24
//7: ms_tab = a[0..6][0..6], msum=128
//8: ms_tab = a[0..4][0..8], msum=115
//9: ms_tab = a[1..6][0..2], msum=28
