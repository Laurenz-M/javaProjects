import java.util.Arrays;


public class deverationTranslation {

    static String[] operands = {"+", "-", "*", "/","^"};
    static String[] variableLetters = {"a", "b", "c", "d", "e","f", "g", "h", "i", "j", "k","l","m", "n", "o", "p", "q", "r","s", "t", "u","v", "w","x", "y", "z"};
        
    public static void main(String[] args){

        
        String originalEquation = "x^4-3x^-2+7";
        int gradeOfDeveration = 1;

        validateOperandRules(originalEquation);
        originalEquation = formatFirstEquationSymbol(originalEquation);
        //System.out.println(originalEquation);
        int equationPartsCount = countEquationsParts(originalEquation);
        String[] equationParts = splitEquationIntoParts(originalEquation, equationPartsCount);
        equationParts = formatAllEquationParts(equationParts);

        for (int deverationGradeIndex = 0; deverationGradeIndex < gradeOfDeveration; deverationGradeIndex++) {
            
            equationParts = deverateEquationPart(equationParts);
        }
        for(int i = 0; i < equationParts.length; i++) {
            System.out.println(equationParts[i]);
        }

        System.out.println("=====DONE=====");
    }

    static boolean compareTwoStrings(String string1, String string2){

        char[] string1Array = string1.toCharArray();
        char[] string2Array = string2.toCharArray();

        if(string1.length() != string2.length()){
            return false;
        }

        for(int i = 0;i < string1.length();i++){

            if(string1Array[i] != string2Array[i]){
                return false;
            }
        }
        return true;
    }

    static String compressArrayToString(String[] stringArray){

        String returnString = "";
    
        for(int i = 0;i < stringArray.length;i++){
            
            returnString = returnString + stringArray[i];
        }
        return returnString;
    }
    
    static boolean isDidget(String inputString){

        if(compareTwoStrings(inputString,"0") || compareTwoStrings(inputString,"1") || compareTwoStrings(inputString,"2") || compareTwoStrings(inputString,"3") || compareTwoStrings(inputString,"4") || compareTwoStrings(inputString,"5") || compareTwoStrings(inputString,"6") || compareTwoStrings(inputString,"7") || compareTwoStrings(inputString,"8") || compareTwoStrings(inputString,"9")){
            return true;
        }
        return false;
    }

    static boolean arrayIncludesValueOfArray(String[] valueArray,String[] isIncludedArray){

            for (int i = 0; i < isIncludedArray.length; i++) {
               
                if(0 <= Arrays.binarySearch(valueArray,isIncludedArray[i])){
                    return true;
                }
                
            }

        return false;
    }

    static String formatFirstEquationSymbol(String equation) {

        String[] splitEquationArray = equation.split("");
    
        if(includesFromStringArray(splitEquationArray[0],operands) && !includesFromStringArray(splitEquationArray[1],operands)){
    
            return equation;
        }
        else if(!includesFromStringArray(splitEquationArray[0],operands)){
    
            //splitEquationArray.unshift('+') //adds '+' to first position of array
            //String[] returnSplitEquationArray = new String[splitEquationArray.length+1];
            splitEquationArray = insertAt(splitEquationArray,0,"+");
            /*for(int i = 1;i < returnSplitEquationArray.length;i++){   //COULD BE OUT OF BOUNDS!
                returnSplitEquationArray[i] = splitEquationArray[i];
            }*/
            //returnSplitEquationArray[0] = "+";
            return compressArrayToString(splitEquationArray);
        }
        return equation;
    }

    static String[] splitEquationIntoParts(String originalEquation,int equationPartsCount){

        String[] splitEquation = originalEquation.split("");
        String[] equationParts = {};
        int leftOffAt = 0;
    
        for (int index = 0; index < equationPartsCount; index++) {
        
            String[] pushArray = {};
            //pushArray.push(splitEquation[leftOffAt]) //pushes first element to array because it is always an operand
            pushArray = pushToArray(pushArray,splitEquation[leftOffAt]);
    
            for (int i = leftOffAt+1; i < splitEquation.length; i++) {
                
                if(includesFromStringArray(splitEquation[i],operands)){
    
                    if(!compareTwoStrings(splitEquation[i],"^") && !compareTwoStrings(splitEquation[i-1],"^")){
                        //console.log("Big if", splitEquation[i-1])
                        leftOffAt = i;
                        break;
                    }
                    else if(compareTwoStrings(splitEquation[i],"^")){
    
                        //pushArray.push(splitEquation[i])
                        pushArray = pushToArray(pushArray,splitEquation[i]);
                    }
                    else if((includesFromStringArray(splitEquation[i],operands) && !compareTwoStrings(splitEquation[i],"^") && compareTwoStrings(splitEquation[i-1],"^"))){
                        //pushArray.push(splitEquation[i])
                        pushArray = pushToArray(pushArray,splitEquation[i]);
                    }
                }
                else if(!includesFromStringArray(splitEquation[i],operands)){
    
                    //pushArray.push(splitEquation[i])
                    pushArray = pushToArray(pushArray,splitEquation[i]);
                }
                else{
    
                    leftOffAt = i;
                    break;
                }
            }//end inner loop
    
            //equationParts.push(compressArrayToString(pushArray))
            equationParts = pushToArray(equationParts,compressArrayToString(pushArray));
        }
    
        return equationParts;
    }

    static void validateOperandRules(String equation){

        String[] splitEquationArray = equation.split("");
    
        for (int i = 1; i < splitEquationArray.length; i++) {
            
            if(includesFromStringArray(splitEquationArray[i],operands) && includesFromStringArray(splitEquationArray[i-1],operands) && !compareTwoStrings(splitEquationArray[i-1],"^")){
    
                //throw "Rechenzeichen dürfen nicht direkt aufeinander folgen!";
            }
            
        }
    }

    static int countEquationsParts(String equation){

        int equationPartsCount = 0;
        String[] splitEquationArray = equation.split("");
        for(int i = 0; i < splitEquationArray.length; i++){
            //System.out.println(Arrays.binarySearch(operands,splitEquationArray[i]));
            if(includesFromStringArray(splitEquationArray[i],operands)){
                //System.out.println(equation);
                //System.out.println(!compareTwoStrings(splitEquationArray[i],"^"));

                if(!compareTwoStrings(splitEquationArray[i],"^")){

                    equationPartsCount++;
                }
            }
        }

        return equationPartsCount;
    }

    static String formatEquationPart(String equationPart){

        String[] splitEquationPartArray = equationPart.split("");
        
        for (int index = 1; index < splitEquationPartArray.length; index++) {
            
            if(includesFromStringArray(splitEquationPartArray[index-1],operands) && includesFromStringArray(splitEquationPartArray[index],variableLetters)){
                //splitEquationPartArray.splice(index, 0, '1')
                splitEquationPartArray = insertAt(splitEquationPartArray,index,"1");
            }
            if(includesFromStringArray(splitEquationPartArray[index-1],variableLetters) && includesFromStringArray(splitEquationPartArray[index],variableLetters)){
                //splitEquationPartArray.splice(index, 0, '1')
                splitEquationPartArray = insertAt(splitEquationPartArray,index,"1");

            }
        }
        //console.log("formatEquationPart: ",compressArrayToString(splitEquationPartArray))
        return compressArrayToString(splitEquationPartArray);
    }

    static boolean includesFromStringArray(String inputString,String[] stringArray){

        String[] splitEquationArray = inputString.split("");
    
        for(int i = 0; i < splitEquationArray.length;i++){
    
            for (int index = 0; index < stringArray.length; index++) {
                
                if(compareTwoStrings(splitEquationArray[i],stringArray[index])){
                    return true;
                }
                
            }
        }
        return false;
    }

    static String addMissingOperands(String equationPart){

        String[] splitEquationPartArray = equationPart.split("");

        for (int index = 1; index < splitEquationPartArray.length; index++) {
    
            if(includesFromStringArray(splitEquationPartArray[index-1],variableLetters) && isDidget(splitEquationPartArray[index])){
                //splitEquationPartArray.splice(index, 0, '*')
                splitEquationPartArray = insertAt(splitEquationPartArray,index,"1");

            }
        }
        return compressArrayToString(splitEquationPartArray);
    }

    static String[] formatAllEquationParts(String[] equationPartArray){

        String[] returnArray = {};
        String addedMultiplierPrefix = "";
    
        for (int index = 0; index < equationPartArray.length; index++) {
            
            addedMultiplierPrefix = formatEquationPart(equationPartArray[index]);
            //returnArray.push(addMissingOperands(addedMultiplierPrefix))
            returnArray = pushToArray(returnArray,addMissingOperands(addedMultiplierPrefix));
            
        }
        //console.table(returnArray)
        return returnArray;
    }

    static String[] deverateEquationPart(String[] equationPartsArray){
    
        String[] returnArray = {};
    
        //System.out.println("deverationTable: ",equationPartsArray);
        for (int index = 0; index < equationPartsArray.length; index++) {
            
    
            String[] equationPartSplitArray = equationPartsArray[index].split("");
    
            //console.log(equationPartsArray.length)
            //for (let i = 0; i < equationPartSplitArray.length; i++) {
    
                if(!arrayIncludesValueOfArray(equationPartSplitArray,variableLetters)){//!variableLetters.includes(equationPartSplitArray)){// && !equationPartSplitArray.includes('^')){
                    
                    //Gleichung ist eine Konstante
                    //returnArray.push(equationPartSplitArray[0] + '0')
                    returnArray = pushToArray(returnArray,equationPartSplitArray[0] + "0");
                }
                else if(arrayIncludesValueOfArray(equationPartSplitArray,variableLetters) && !includesFromStringArray("^",equationPartSplitArray)){
    
                    //Gleichung hat nur ein einfaches X
                    equationPartSplitArray[equationPartSplitArray.length-1] = "";
                    //returnArray.push(compressArrayToString(equationPartSplitArray))
                    returnArray = pushToArray(returnArray,compressArrayToString(equationPartSplitArray));
                }
                else{
                    
                    //Gleichung hat eine Potenz
                    String returnDeveration = "";
                    //System.out.println("in loop" + equationPartSplitArray[equationPartSplitArray.indexOf('^')+1]);
                    //System.out.println(equationPartSplitArray);
    
                    String power = "";
                    for (int index2 = compressArrayToString(equationPartSplitArray).indexOf("^")+1; index2 < equationPartSplitArray.length; index2++) {
    
                        power += equationPartSplitArray[index2];
                        
                    }
                    //System.out.println("power " + power);
    
                    String variableMultiplier = "" + equationPartSplitArray[0];
                    String variablePart = "";
    
                    for (int index2 = 1; index2 < equationPartSplitArray.length; index2++) {
                        
                        if(includesFromStringArray(equationPartSplitArray[index2],variableLetters)){// || equationPartSplitArray[index] === '^'){
                            
                            variablePart = equationPartSplitArray[index2];
                            break;
                        }
                        variableMultiplier = variableMultiplier + equationPartSplitArray[index2];
                        
                    }
                    //console.log("variableMultiplier ", variableMultiplier)
                    int powerInt = Integer.parseInt(power);
                    int variableMultiplierInt = Integer.parseInt(variableMultiplier);

                    if(powerInt-1 == 1){
                        returnDeveration = ""+ variableMultiplierInt*powerInt + "" + variablePart;
                        
                    }
                    else if(powerInt-1 == 0 ){
                        //returnDeveration = `${variableMultiplier*powerInt}`;
                        returnDeveration = "" + variableMultiplierInt * powerInt;
                    }
                    else if(powerInt == 0){
                        //console.log('gets run')
                        returnDeveration = variableMultiplier;
                    }
                    else{
                        returnDeveration = "" + variableMultiplierInt*powerInt + ""+ variablePart + "^" + (powerInt-1);
    
                    }
    
                    //console.log("returnDeveration ", returnDeveration)
                    //returnArray.push(returnDeveration)
                    returnArray = pushToArray(returnArray,returnDeveration);
                }
            //}
        }
        return returnArray;
    }

    static String[] pushToArray(String[] originalArray, String value){

        String[] returnArray = new String[originalArray.length+1];
        for(int i = 0; i < originalArray.length; i++){
            returnArray[i] = originalArray[i];
        }
        returnArray[returnArray.length-1] = value;
        return returnArray;
    }

    static String[] insertAt(String[] originalArray,int insertIndex,String value){
    
        String[] returnArray = new String[originalArray.length+1];
        //returnArray.length);
        
        for(int i = 0; i < returnArray.length;i++){

            if(i == insertIndex){
                returnArray[i] = value;
            }
            else if(i < insertIndex){
                returnArray[i] = originalArray[i];
            }
            else if(i > insertIndex){
                returnArray[i] = originalArray[i-1];
            }
            
        }
        return returnArray;
    }

}