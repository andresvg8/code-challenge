package com.payulatam.recruiting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ANDRES VILLALBA GAVIRIA, aspirante a Desarrollador Java Junior. E-mail: andresvg8@gmail.com
 */
public class NetworkPrioritization {

    public enum Criteria {
        RESPONSE_TIME, COST;
    }

    public int[] prioritizeNetwork(int[] responseTimes, int[] cost, Criteria priority) {
        if(responseTimes.length==cost.length){
            int[] initialResponseTimes = cloneArray(responseTimes);
            int[] initialCost = cloneArray(cost);
            int[] indexes = new int[responseTimes.length];
            
            ArrayList<PaymentNetwork> nets = new ArrayList<PaymentNetwork>();
            for(int z=0; z<responseTimes.length; z++){
                PaymentNetwork p = new PaymentNetwork();
                p.setIndex(z);
                p.setResponseTime(initialResponseTimes[z]);
                p.setCost(initialCost[z]);
                nets.add(p);
            }
            for(int z=0; z<nets.size(); z++){
                System.out.println("nets["+z+"] : "
                        + "index="+nets.get(z).getIndex()
                        +" responseTime="+nets.get(z).getResponseTime()
                        +" cost="+nets.get(z).getCost());
            }
            if(priority==Criteria.RESPONSE_TIME){
                Arrays.sort(responseTimes);
                showArray(responseTimes, "responseTimes ordenado: ");
                // PRIMER ORDENAMIENTO.
                for(int i=0; i<(responseTimes.length-1); i++){
                    for(int j=0; j<(responseTimes.length-1-i); j++){
                        if( nets.get(j).getResponseTime()>nets.get(j+1).getResponseTime() ){
                            swap(nets.get(j), nets.get(j+1));
                        }
                    }
                }
                this.showArrayList(nets, "NETS:");
                // SEGUNDO ORDENAMIENTO.
                for(int m=0; m<responseTimes.length; m++){
                    int[] indexesRepeated = getIndexesOf(responseTimes[m], responseTimes);
                    int timesRepeated = indexesRepeated.length;
                    if(timesRepeated>1){
                        // Adiciona solamente repetidos.
                        this.showArray(indexesRepeated, "indexesRepeated");
                        this.sortSiblingsByCost(nets, indexesRepeated);
                    }
                }
                int[] output = new int[nets.size()];
                for(int z=0; z<nets.size(); z++){
                    System.out.println("nets["+z+"] : "+ "index="+nets.get(z).getIndex()+" responseTime="+nets.get(z).getResponseTime()+" cost="+nets.get(z).getCost());
                    output[z]=nets.get(z).getIndex();
                }
                return output;
            }
            else if(priority==Criteria.COST){
                Arrays.sort(cost);
                showArray(cost, "cost ordenado: ");
                // PRIMER ORDENAMIENTO.
                for(int i=0; i<(cost.length-1); i++){
                    for(int j=0; j<(cost.length-1-i); j++){
                        if( nets.get(j).getCost()>nets.get(j+1).getCost() ){
                            swap(nets.get(j), nets.get(j+1));
                        }
                    }
                }
                this.showArrayList(nets, "NETS:");
                // SEGUNDO ORDENAMIENTO.
                for(int m=0; m<cost.length; m++){
                    int[] indexesRepeated = getIndexesOf(cost[m], cost);
                    int timesRepeated = indexesRepeated.length;
                    if(timesRepeated>1){
                        // Adiciona solamente repetidos.
                        this.showArray(indexesRepeated, "indexesRepeated");
                        this.sortSiblingsByResponseTime(nets, indexesRepeated);
                    }
                }
                int[] output = new int[nets.size()];
                for(int z=0; z<nets.size(); z++){
                    System.out.println("nets["+z+"] : "+ "index="+nets.get(z).getIndex()+" responseTime="+nets.get(z).getResponseTime()+" cost="+nets.get(z).getCost());
                    output[z]=nets.get(z).getIndex();
                }
                return output;
            }
            else{
                System.out.println("El criterio no existe.");
                return new int[0];
            }
        }
        else{
            System.out.println("Los arreglos responseTimes y cost no tienen igual cantidad de elementos.");
            return new int[0];
        }
    }
    
    public void showArray(int[] arr, String headerMessage){
        String message = "{";
        for(int k=0; k<arr.length; k++){
            message += arr[k];
            if(k<(arr.length-1)){
                message += " , ";
            }
        }
        message += "}";
        System.out.println("" + headerMessage);
        System.out.println("" + message);
    }
    
    public void showArrayList(ArrayList<PaymentNetwork> arr, String headerMessage){
        System.out.println(""+headerMessage);
        for(int k=0; k<arr.size(); k++){
            System.out.println("index="+arr.get(k).getIndex()+" responseTime="+arr.get(k).getResponseTime()+" cost="+arr.get(k).getCost());
        }
    }
    
    public int[] cloneArray(int[] arr){
        int[] x = new int[arr.length];
        for(int k=0; k<arr.length; k++){
            x[k] = arr[k];
        }
        return x;
    }
    
    private int[] getIndexesOf(int element, int[] arr){
        if(arr.length>0){
            List<Integer> a = new ArrayList<Integer>();
            for(int k=0; k<arr.length; k++){
                if(element==arr[k]){
                    a.add(k);
                }
            }
            int[] indexes = new int[a.size()];
            for(int k=0; k<a.size(); k++){
                indexes[k] = a.get(k);
            }
            return indexes;
        }
        else{
            return null;
        }
    }
    
    private void swap(PaymentNetwork a, PaymentNetwork b){
        a.setResponseTime(a.getResponseTime()+b.getResponseTime());
        b.setResponseTime(a.getResponseTime()-b.getResponseTime());
        a.setResponseTime(a.getResponseTime()-b.getResponseTime());
        a.setCost(a.getCost()+b.getCost());
        b.setCost(a.getCost()-b.getCost());
        a.setCost(a.getCost()-b.getCost());
        a.setIndex(a.getIndex()+b.getIndex());
        b.setIndex(a.getIndex()-b.getIndex());
        a.setIndex(a.getIndex()-b.getIndex());
    }
    
    private void sortSiblingsByCost(ArrayList<PaymentNetwork> x, int[] indexes){
        System.out.println("x antes de ordenar:");
        for(int i=0; i<x.size(); i++){
            System.out.println("x["+i+"]: index="+x.get(i).getIndex()+" responseTime="+x.get(i).getResponseTime()+" cost="+x.get(i).getCost());
        }
        ArrayList<PaymentNetwork> y = new ArrayList<PaymentNetwork>();
        for(int i=0; i<indexes.length; i++){
            PaymentNetwork w = new PaymentNetwork();
            w.setCost(x.get(indexes[i]).getCost());
            w.setIndex(x.get(indexes[i]).getIndex());
            w.setResponseTime(x.get(indexes[i]).getResponseTime());
            y.add(w);
        }
        System.out.println("y antes de ordenar:");
        for(int i=0; i<y.size(); i++){
            System.out.println("y["+i+"]: index="+y.get(i).getIndex()+" responseTime="+y.get(i).getResponseTime()+" cost="+y.get(i).getCost());
        }
        for(int i=0; i<(y.size()-1); i++){
            for(int j=0; j<(y.size()-1-i); j++){
                if( y.get(j).getCost()>y.get(j+1).getCost() ){
                    swap(y.get(j), y.get(j+1));
                }
            }
        }
        System.out.println("y después de ordenar:");
        for(int i=0; i<y.size(); i++){
            System.out.println("y["+i+"]: index="+y.get(i).getIndex()+" responseTime="+y.get(i).getResponseTime()+" cost="+y.get(i).getCost());
        }
        for(int i=0; i<indexes.length; i++){
            x.get(indexes[i]).setCost(y.get(i).getCost());
            x.get(indexes[i]).setIndex(y.get(i).getIndex());
            x.get(indexes[i]).setResponseTime(y.get(i).getResponseTime());
        }
        System.out.println("x después de ordenar:");
        for(int i=0; i<x.size(); i++){
            System.out.println("x["+i+"]: index="+x.get(i).getIndex()+" responseTime="+x.get(i).getResponseTime()+" cost="+x.get(i).getCost());
        }
    }
    
    private void sortSiblingsByResponseTime(ArrayList<PaymentNetwork> x, int[] indexes){
        System.out.println("x antes de ordenar:");
        for(int i=0; i<x.size(); i++){
            System.out.println("x["+i+"]: index="+x.get(i).getIndex()+" responseTime="+x.get(i).getResponseTime()+" cost="+x.get(i).getCost());
        }
        ArrayList<PaymentNetwork> y = new ArrayList<PaymentNetwork>();
        for(int i=0; i<indexes.length; i++){
            PaymentNetwork w = new PaymentNetwork();
            w.setCost(x.get(indexes[i]).getCost());
            w.setIndex(x.get(indexes[i]).getIndex());
            w.setResponseTime(x.get(indexes[i]).getResponseTime());
            y.add(w);
        }
        System.out.println("y antes de ordenar:");
        for(int i=0; i<y.size(); i++){
            System.out.println("y["+i+"]: index="+y.get(i).getIndex()+" responseTime="+y.get(i).getResponseTime()+" cost="+y.get(i).getCost());
        }
        for(int i=0; i<(y.size()-1); i++){
            for(int j=0; j<(y.size()-1-i); j++){
                if( y.get(j).getResponseTime()>y.get(j+1).getResponseTime() ){
                    swap(y.get(j), y.get(j+1));
                }
            }
        }
        System.out.println("y después de ordenar:");
        for(int i=0; i<y.size(); i++){
            System.out.println("y["+i+"]: index="+y.get(i).getIndex()+" responseTime="+y.get(i).getResponseTime()+" cost="+y.get(i).getCost());
        }
        for(int i=0; i<indexes.length; i++){
            x.get(indexes[i]).setCost(y.get(i).getCost());
            x.get(indexes[i]).setIndex(y.get(i).getIndex());
            x.get(indexes[i]).setResponseTime(y.get(i).getResponseTime());
        }
        System.out.println("x después de ordenar:");
        for(int i=0; i<x.size(); i++){
            System.out.println("x["+i+"]: index="+x.get(i).getIndex()+" responseTime="+x.get(i).getResponseTime()+" cost="+x.get(i).getCost());
        }
    }
}
