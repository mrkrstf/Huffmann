/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando1;



public class Main {
    public static String s="azabbrakadabraa";
    
    public static void main(String[] args)
    {
        Process p=new Process(s);
        p.tree();
        p.makeDictionary();
        p.encode();
        p.decode();
    }
}
