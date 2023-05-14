public class Bai1 {
    public static int findMin(int[] a) {
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }
    public static int findMaxColumn(int[][] a, int column) {
        int max = a[0][column];
        for (int i = 0; i < a.length; i++) {
            if (a[i][column] > max) {
                max = a[i][column];
            }
        }
        return max;
    }
    // find saddle point in a matrix
    public static int findSaddlePoint(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int min = 0;
        int max = 0;
        int saddlePoint = 0;
        for (int i = 0; i < n; i++) {
            min = findMin(a[i]);
            for (int j = 0; j < m; j++) {
                if (a[i][j] == min) {
                    max = findMaxColumn(a, j);
                    if (max == min) {
                        saddlePoint = max;
                    }
                }
            }
        }
        return saddlePoint;
    }

    // create a matrix
    public static int[][] createMatrix(int n, int m) {
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = (int) (Math.random() * 100);
            }
        }
        return a;
    }

    
    public static void main(String[] args) {
        int[][] a = createMatrix(3, 3);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Saddle point: " + findSaddlePoint(a));
    }
}


