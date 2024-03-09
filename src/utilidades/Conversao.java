/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Locale;

/**
 *
 * @author 181700028
 * @since 06/03/2024
 * @version 1.0
 */
public class Conversao {
    
    //esse metodo é responsavel por substituir a virgula por ponto,
    //caso o usuario digite errado.
    public static String converterVirgulaPonto(String c){
        return c.replaceAll(",", ".");
    }//fecha o metodo converterVirgulaPonto
    
    
    
    //coloca todas as Strings em caixa alta
    public static String converterTudoMai(String c){
        return c.toUpperCase();
    }//fecha metodo converterTudoMai
    
    //coloca todas as Strings em caixa baixa
    public static String converterTudoMin(String c){
        return c.toLowerCase();
    }//fecha metodo converterTudoMin
    
    /*
    metodo que verifica se o usuario digitou apenas
    letras maiusculas ou minusculas;
    no minimo 6 e no maximo 50 caracteres
    */
    public static boolean verificarTexto(String c){
        
        return c.matches("^[a-zA-záÁéÉíÍóÓúÚçÇãÃõÕ ]{6,50}$");
    }//fecha metodo verificarTexto
    
    
    /*
    metodo que verifica se o usuario digitou apenas
    numero de 0 a 9 com no minimo 6 e no maximo 8
    digitos*/
    public static boolean verificarNumero(String c){
        
        return c.matches("^[0-9]{6,8}$");
    }//fecha metodo verificarTexto
    public static boolean verificarNumeroCom(String c){
        
        return c.matches("^[0-9]{6,8}$");
    }//fecha metodo verificarTexto
    public static boolean verificarNumeroComVi(String c){
        
        return c.matches("^[0.01-9.99]{6,8}$");
    }//fecha metodo verificarTexto

    
    
}//fecha classe Conversao
