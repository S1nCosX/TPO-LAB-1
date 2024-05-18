package main.task1;

public class Cos{
    public static double get_power_series(double x, int n){

        double ans = 0;
        double inc = 1;
        for (int i = 1; i < n; i++){
            System.out.println(ans + " " + inc);
            ans += inc;
            inc *= -x*x/(((i)*2.0)*((i)*2.0-1.0));
        }

        return ans;
    }
}

