package frbs;

/**
 * Created by jcozar on 27/01/16.
 */

import nrc.fuzzy.FuzzyValue;
import nrc.fuzzy.FuzzyVariable;
import nrc.fuzzy.InvalidLinguisticExpressionException;
import nrc.fuzzy.MamdaniMinMaxMinRuleExecutor;

import java.util.ArrayList;

public class Rulebase {

    private ArrayList<FuzzyRule> rulesForValveTemperature;
    private ArrayList<FuzzyRule> rulesForValvePressure;
    private MamdaniMinMaxMinRuleExecutor executor;
    private Database db;

    public ArrayList<FuzzyRule> getRulesForValveTemperature(){ return rulesForValveTemperature;}
    public ArrayList<FuzzyRule> getRulesForValvePressure(){ return rulesForValvePressure;}

    public void addRuleForValveTemperature(String tempLabel, String presLabel, String valveLabel){
        FuzzyVariable fuzzyTemp = db.getVariable(Database.TEMPERATURE);
        FuzzyVariable fuzzyPres = db.getVariable(Database.PRESSURE);
        FuzzyVariable fuzzyValveTemp = db.getVariable(Database.VALVE_TEMPERATURE);
        FuzzyRule r = new FuzzyRule(executor);
        try {
            if(tempLabel!=null)
                r.addAntecedent(new FuzzyValue(fuzzyTemp, tempLabel));
            if(presLabel!=null)
                r.addAntecedent(new FuzzyValue(fuzzyPres, presLabel));
            r.addConclusion(new FuzzyValue(fuzzyValveTemp, valveLabel));
        }catch (Exception e) {
            System.out.println("Error al crear la regla: "+e);
            return;
        }
        rulesForValveTemperature.add(r);
    }
    public void addRuleForValvePressure(String tempLabel, String presLabel, String valveLabel){
        FuzzyVariable fuzzyTemp = db.getVariable(Database.TEMPERATURE);
        FuzzyVariable fuzzyPres = db.getVariable(Database.PRESSURE);
        FuzzyVariable fuzzyValvePres= db.getVariable(Database.VALVE_PRESSURE);
        FuzzyRule r = new FuzzyRule(executor);
        try {
            if(tempLabel!=null)
                r.addAntecedent(new FuzzyValue(fuzzyTemp, tempLabel));
            if(presLabel!=null)
                r.addAntecedent(new FuzzyValue(fuzzyPres, presLabel));
            r.addConclusion(new FuzzyValue(fuzzyValvePres, valveLabel));
        }catch (Exception e) {
            System.out.println("Error al crear la regla: "+e);
            return;
        }
        rulesForValvePressure.add(r);
    }

    public Rulebase(Database db){
        //Create the executor for the Fuzzy Rules to be created.
        executor = new MamdaniMinMaxMinRuleExecutor();
        //Create the fuzzy rules over the database
        rulesForValveTemperature = new ArrayList<>();
        rulesForValvePressure = new ArrayList<>();
        this.db = db;

        /****************************************/
        /** COMPLETAR CÓDIGO CON BASE DE REGLAS**/
        /****************************************/
        //Reglas para temperatura
        
        //Reglas simples
        addRuleForValveTemperature("Low",null,"VeryHigh");
        addRuleForValveTemperature("Medium",null,"Medium");
        addRuleForValveTemperature("High",null,"VeryLow");
        
        //Reglas compuestas
        //Casos extremos
//        addRuleForValveTemperature("Low","Low","VeryHigh");
//        addRuleForValveTemperature("High","High","VeryLow");
//        //Regulación mutua
//        addRuleForValveTemperature("Low","High","Medium");
//        addRuleForValveTemperature("High","Low","Medium");
//        //Prevención
//        addRuleForValveTemperature("Medium","Low","High");
//        addRuleForValveTemperature("Medium","High","Low");
        
        //Reglas para presión
        
        //Reglas simples
        addRuleForValvePressure(null,"Low","VeryHigh");
        addRuleForValvePressure(null,"Medium","Medium");
        addRuleForValvePressure(null,"High","VeryLow");
        
        //Reglas compuestas
        //Casos extremos
//        addRuleForValvePressure("Low","Low","VeryHigh");
//        addRuleForValvePressure("High","High","VeryLow");
//        //Regulación mutua
//        addRuleForValvePressure("Low","High","Medium");
//        addRuleForValvePressure("High","Low","Medium");
//        //Prevención
//        addRuleForValvePressure("Low","Medium","High");
//        addRuleForValvePressure("High","Medium","Low");
    }
}
