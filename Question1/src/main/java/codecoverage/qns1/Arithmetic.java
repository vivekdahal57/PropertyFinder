/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecoverage.qns1;

/**
 *
 * @author Alex
 */
public class Arithmetic {

    public int addPositiveNumbers(int a, int b) {
        if (a >= 1 && b >= 1) {
            return a + b;
        }
        return 0;
    }
}
