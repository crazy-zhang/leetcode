package finance;

import java.util.Scanner;

public class YangXu {
    public static void main(String[] args) {
        double[][] fund_prop_a = new double[2][3];
        double[][] fund_value_b = new double[2][3];

        fund_prop_a[0][0] = 0.015;
        fund_prop_a[0][1] = 0.005;
        fund_prop_a[0][2] = 0;

        fund_prop_a[1][0] = 0.014;
        fund_prop_a[1][1] = 0.004;
        fund_prop_a[1][2] = 0;

        fund_value_b[0][0] = 1;
        fund_value_b[0][1] = 0.998;
        fund_value_b[0][2] = 1.003;
        fund_value_b[1][0] = 1;
        fund_value_b[1][1] = 0.996;
        fund_value_b[1][2] = 1.008;

        double fund_before_a = 2000;
        double fund_before_b = 1000;
        double fund_after_a = 1000;
        double fund_after_b = 1000;

        //t = 1 to t = 4 to t = T的时候，计算AB不同的金额
        double fund_T_a = (fund_before_a + fund_after_a / fund_value_b[0][1]) * fund_value_b[0][2];
        double fund_T_b = (fund_before_b + fund_after_a / fund_value_b[1][1]) * fund_value_b[1][2];

        Scanner sc = new Scanner(System.in);
        System.out.println("输入T天数，和X基金金额");
        int T = sc.nextInt();
        double X = sc.nextDouble();
        //当t >= 365以上的时候，无赎回费
        if(T >= 365) {
            System.out.println(fund_T_a);
            System.out.println(fund_T_b);

        }
        if(T >= 8 && T <= 364){
            if(X <= fund_T_b) System.out.println("优先赎回B");
            else System.out.println("优先赎回B,剩下的赎回A");
        }
        if(T >= 4 && T <= 7) {
            if(X <= fund_T_b) System.out.println("优先赎回B");
            else System.out.println("优先赎回B,剩下的赎回A");
        }

    }
}
