/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Process {
    public ArrayList<Huffmann> weightedData= new ArrayList<>();
    public ArrayList<Huffmann> nodes = new ArrayList<>();
    public HashMap<Character,String> dictionary = new HashMap<>();
    public String s;
    public String data="";
    public String encoded;
    public String decoded;
    
    public Process(String s)
    {
        this.s=s;
        int counter=0;
        Huffmann h;
        for(char c : s.toCharArray())
        {
            for(char c2 : s.toCharArray())
            {
                if(c == c2)
                {
                    counter++;
                }
            }
            h=new Huffmann(counter,c);
            
            if(!(weightedData.contains(h)))
            {
                weightedData.add(h);
                data+=c;
            }
            counter=0;
        }
        Collections.sort(weightedData);
        System.out.println("Gyakorisági tábla: ");
        for(Huffmann h2: weightedData)
        {
            System.out.println("["+h2.letter+", "+h2.amount+"]");
        }
        System.out.println("-------------------------------------------");
    }
    
    public void tree()
    {
        Huffmann h;
        for(int i=0; i<weightedData.size();i+=0)
        {
            weightedData.add(new Huffmann(0,'?'));
            weightedData.get(weightedData.size()-1).left=weightedData.get(i+1);
            weightedData.get(weightedData.size()-1).right=weightedData.get(i);
            weightedData.get(weightedData.size()-1).amount=weightedData.get(i).amount+weightedData.get(i+1).amount;
            weightedData.get(i).parent=weightedData.get(weightedData.size()-1);
            weightedData.get(i+1).parent=weightedData.get(weightedData.size()-1);
            nodes.add(weightedData.get(i));
            weightedData.remove(i);
            nodes.add(weightedData.get(i));
            weightedData.remove(i);
            Collections.sort(weightedData);
        }
        nodes.remove(nodes.size()-1);
        h=nodes.get(nodes.size()-1).left;
        nodes.get(nodes.size()-1).left=nodes.get(nodes.size()-1).right;
        nodes.get(nodes.size()-1).right=h;
        nodes.get(nodes.size()-1).parent=null;
    }
    
    public void makeDictionary()
    {
        String code;
        String code2="";
        for(int i=nodes.size()-1;i>0;i--)
        {
            if(nodes.get(i).left != null || nodes.get(i).right != null)
            {
                if(nodes.get(i).left.amount>nodes.get(i).right.amount){nodes.get(i).leftEdge=1;nodes.get(i).rightEdge=0;}
                else{nodes.get(i).leftEdge=0;nodes.get(i).rightEdge=1;}
            }
        }
        for(char c: data.toCharArray())
        {
            for(int i=0;i<nodes.size();i++)
            {
                if(nodes.get(i).letter==c)
                {
                    code=toRoot(nodes.get(i));
                    for(int j=code.length()-1;j>=0;j--)
                    {
                        code2+=code.charAt(j);
                    }
                    dictionary.put(c,code2);
                    code2="";
                }
            }
        }
        System.out.println("A Fa alapján készített szótár:");
        System.out.println(dictionary.toString());
        System.out.println("-------------------------------------------");
       
    }
    
    public void encode()
    {
        encoded="";
        for(char c : s.toCharArray())
        {
            encoded+=dictionary.get(c);
        }
        System.out.print("Kódolt szöveg: ");
        System.out.println(encoded);
        System.out.println("-------------------------------------------");
    }
    public void decode()
    {
        decoded="";
        String helper="";
        for(char c :encoded.toCharArray())
        {
            helper+=c;
            for(char c2 : dictionary.keySet())
            {
                if(dictionary.get(c2).equals(helper))
                {
                    decoded+=c2;
                    helper="";
                }
            }
        }
        System.out.print("Visszafejtett kód: ");
        System.out.println(decoded);
        System.out.println("-------------------------------------------");
    }
    
    public String toRoot(Huffmann h)
    {
        String code="";
        Huffmann h2=h.parent;
        while(h2!=null)
        {
            if(h2.left.equals(h))
            {
                code+=h2.leftEdge;
            }else
            {
                code+=h2.rightEdge;
            }
            h=h2;
            h2=h.parent;
        }
        return code;
    }
}
