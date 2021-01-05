/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payulatam.recruiting;

import java.util.Arrays;

/**
 *
 * @author ANDRES VILLALBA GAVIRIA, aspirante a Desarrollador Java Junior. E-mail: andresvg8@gmail.com
 */
public class Principal {
    
    public static void main(String[] args){
        System.out.println("HOLA, BIENVENIDO A PRIORIZACION.PRINCIPAL");
        int[] _responseTimes = {100, 20, 15, 100, 15, 100, 50, 9, 15};
        int[] _cost = {35, 50, 60, 40, 55, 25, 40, 70, 55};
        NetworkPrioritization np = new NetworkPrioritization();
        
        int[] result1 = np.prioritizeNetwork(_responseTimes, _cost, NetworkPrioritization.Criteria.RESPONSE_TIME);
        np.showArray(result1, "RESULTADO FINAL 1:");
        
        int[] result2 = np.prioritizeNetwork(_responseTimes, _cost, NetworkPrioritization.Criteria.COST);
        np.showArray(result2, "RESULTADO FINAL 2:");
        
        np.showArray(np.prioritizeNetwork(new int[]{20, 15, 100, 16, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                NetworkPrioritization.Criteria.RESPONSE_TIME), "RESULTADO FINAL 3:");//int[] expected = new int[]{5, 1, 3, 0, 4, 2};
        
        np.showArray(np.prioritizeNetwork(new int[]{20, 15, 100, 16, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                NetworkPrioritization.Criteria.COST), "RESULTADO FINAL 4:");//int[] expected = new int[]{2, 4, 0, 3, 1, 5};
        
        np.showArray(np.prioritizeNetwork(new int[]{20, 15, 100, 15, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                NetworkPrioritization.Criteria.RESPONSE_TIME), "RESULTADO FINAL 5:");//int[] expected = new int[]{5, 3, 1, 0, 4, 2};
    }
}
