package com.zk.utils;


public class StringSplit {


    public  static  Integer[] getInt(String salary){
        Integer[] integers=new Integer[2];

        String[] split = salary.split("-");
        String minSalary=split[0];
        String maxSalary=split[1].substring(0,split[1].length()-3);
        if(salary.contains("千/月")){

            integers[0]= (int)(Double.parseDouble(minSalary)*1000);
            integers[1]= (int)(Double.parseDouble(maxSalary)*1000);

        }else if (salary.contains("万/月")){
            integers[0]= (int)(Double.parseDouble(minSalary)*10000);
            integers[1]= (int)(Double.parseDouble(maxSalary)*10000);
        }else if(salary.contains("万/年")){
            integers[0]= (int)(Double.parseDouble(minSalary)/12*10000);
            integers[1]= (int)(Double.parseDouble(maxSalary)/12*10000);
        }

        return integers;
    }

}
