package com.javarush.task.task34.task3404;


import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)",0);

    }

    public void recursion(final String expression, int countOperation) {
        String expr=expression;
        String temp=null;

        //блок 0. Обработка строки при первом вхождении
        if (countOperation==0){
            expr = expression.replaceAll(" ","");
            countOperation=count(expr);
            if(expr.charAt(0)=='-'){expr='0'+expr;}
            expr=expr.replaceAll("\\(-","(0-");
        }
        // блок 1. Проверка на окончание работы(является ли строка числом)
        if(isNum(expr)){
            NumberFormat nf = new DecimalFormat("#.##");
            Double d = Double.parseDouble(expr);
            System.out.println(String.format("%s %d", nf.format(d),countOperation));
            return;
        }
        // блок 2. если строка - не число
        else if(expr.contains("(")){        //проверяем на наличие "(" и ищем "самую глубокую"
            int n = expr.lastIndexOf("(");
            temp = expr.substring(n+1, expr.indexOf(")",n));       //выражение внутри скобок
            String trig = "trig";
            if(n>2){trig=expr.substring(n-3,n);}        //выделили возможную тригонометрию
            if(isNum(temp)){                            // проверка на число
                double t=Double.parseDouble(temp);
                switch (trig){                              // переключатель на тригонометрию и обычный оператор
                    case "sin":
                        t=roundDouble(Math.sin(t*Math.PI/180),4);
                        //countOperation++;
                        expr=trigStr(expr,n,t);
                        break;
                    case "cos":
                        t=roundDouble(Math.cos(t*Math.PI/180),4);
                        //countOperation++;
                        expr=trigStr(expr,n,t);
                        break;
                    case "tan":
                        t=roundDouble(Math.tan(t*Math.PI/180),4);
                        //countOperation++;
                        expr=trigStr(expr,n,t);
                        break;
                    default:                    // формируем строку, убрав скобки
                        temp = expr.substring(0,n).concat(Double.toString(t));
                        expr=temp.concat(expr.substring(expr.indexOf(')',n)+1));
                }
            }
            else {                //внутри скобок математическое выражение, работаем с ним
                temp = mathStr(temp);
                expr=expr.substring(0,n+1).concat(temp).concat(expr.substring(expr.indexOf(")",n)));      //сформировали строку с модифицированным выражением в скобках
                //countOperation++;

            }
        }
        else {                          //скобок нет, работаем с матем.выражением
            expr=mathStr(expr);
            //countOperation++;
        }

        recursion(expr,countOperation);
    }
    // вспомогательные методы
    public boolean isNum(String str){
        if(str==null)return false;
        return str.matches("^[-+]?\\d+(\\.\\d+)?");};   //проверка на число

    public double roundDouble(Double d,int r){      //округление до заданной цифры после запятой
        int dec = (int)Math.pow(10,r);
        Double x = (double)Math.round(d*dec);
        return x/dec;}

    public String trigStr(String expr,int n,double t){      //формирование новой строки для случая геометрии
        String result = "";
        if(n>3){result=expr.substring(0,n-3);}
        result = result.concat(Double.toString(t));
        if(expr.indexOf(")",n)<expr.length()-1){
            result=result.concat(expr.substring(expr.indexOf(")",n)+1));}
        return result;}

    public String mathStr(String temp){
        String result=null;
        String sign;
        double[] num={0,0,0,0};
        int st=0;
        int fin=0;
        if(temp.contains("^")){ sign = "^";}
        else if(temp.contains("*")){
            if(temp.substring(0,temp.indexOf("*")).contains("/")){sign="/";}
            else{sign="*";}
        }
        else if(temp.contains("/")){sign="/";}
        else if (temp.contains("+")){
            if(!temp.substring(1,temp.indexOf("+")).contains("-")){sign="+";}
            else{sign="-";}}
        else {sign="-";}
        switch (sign){
            case "^":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(Math.pow(num[0],num[1]),4));
                break;
            case "/":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(num[0]/num[1],4));
                break;
            case "*":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(num[0]*num[1],4));
                break;
            case "+":
                num=operandNum(temp,temp.indexOf(sign));
                result=String.valueOf(roundDouble(num[0]+num[1],4));
                break;
            case "-":
                if(temp.indexOf(sign)!=0){
                    num=operandNum(temp,temp.indexOf(sign));}
                else {num=operandNum(temp,temp.indexOf(sign,1));}
                result=String.valueOf(roundDouble(num[0]-num[1],4));
                break;
        }
        st=(int)num[2];
        fin=(int)num[3];
        if(st!=0){result=temp.substring(0,st).concat(result);}
        if(fin !=(temp.length()-1)){result=result.concat(temp.substring(fin+1));}
        return result;}

    public int count(String expr){
        int result=0;
        String[] signt = {"sin","cos","tan"};
        for (int i = 0; i <signt.length ; i++) {
            if(expr.contains(signt[i])){String []f = expr.split(signt[i]);
                result+=(f.length-1);}
        }
        char [] sign = {'^','*','/','+','-'};
        char[] str = expr.toCharArray();
        for (int i = 0; i <sign.length; i++) {
            for (int j = 0; j < str.length; j++) {
                if(str[j]==sign[i]){
                    result++;
                }
            }
        }
        return result;
    }
    public double[] operandNum(String temp,int signPosition){
        double[] numOperation =new double[4];
        String num1Str = "";
        String num2Str = "";
        int st = signPosition-1;
        int fin = signPosition+1;
        while (true){
            if((st>-1)&&(Character.isDigit(temp.charAt(st))||(temp.charAt(st)=='.'))){
                num1Str=String.valueOf(temp.charAt(st)).concat(num1Str);
                st--;
            }
            else break;
        }
        st++;
        if((st !=0)&&(temp.charAt(st-1)=='-')){
            if((st==1)||((!Character.isDigit(temp.charAt(st-2)))&&(temp.charAt(st-2)!='(')&&(temp.charAt(st-2)!=')'))){
                num1Str="-".concat(num1Str);
                st--;}}
        while (fin<temp.length()){
            if((temp.charAt(fin)=='-')&&(num2Str.length()==0)){
                num2Str="-";
                fin++;
            }
            if(Character.isDigit(temp.charAt(fin))||(temp.charAt(fin)=='.')){
                num2Str=num2Str.concat(String.valueOf(temp.charAt(fin)));
                fin++;
            }
            else break;
        }
        fin--;
        numOperation[0] = Double.parseDouble(num1Str);
        numOperation[1] = Double.parseDouble(num2Str);
        numOperation[2] = st;
        numOperation[3] = fin;

        return numOperation;
    }


    public Solution() {
        //don't delete
    }
}
