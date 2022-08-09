

public class consoleTableTranslation {
    

        public static void main(String[] args){

            String[] tableValue = {"null","eins","zwei","drei","vier", "fuenf", "sechs","sieben","acht", "neun","zehn","elf","zwoelf","zweihundertvierzig"};
            table(tableValue);
        }
    
        static void table(String[] tableValue){

            String tableStructure = "";
            
            tableStructure += endPieces(tableValue);
            for(int i=0; i<tableValue.length;i++){

                tableStructure += '\n' + filledRow(tableValue,i);
                tableStructure += '\n' + emptyRow(tableValue);
            }
                        
            System.out.println(tableStructure);
        }

        static int longestElement(String[]tableValue){

            int longestElement = 0;
            for(int i = 0; i < tableValue.length; i++){

                if(tableValue[i].length() > longestElement){ 
                    longestElement = tableValue[i].length();
                }
            }

            return longestElement;
        }

        static String emptyRow(String[] tableValue){

            String rowDelimiter = "_";
            String emptyRow = "|";
            //emptyRow += "  ";
            //emptyRow += "--";
            emptyRow += rowDelimiter + rowDelimiter;
            for(int i = 0; i < maxIndexSpace(tableValue); i++){
                //emptyRow += " ";
                //emptyRow += "-";
                emptyRow += rowDelimiter;
            }
            //emptyRow += "  ";
            //emptyRow += "--";
            emptyRow += rowDelimiter + rowDelimiter;
            emptyRow += "|";
            //emptyRow += "  ";
            //emptyRow += "--";
            emptyRow += rowDelimiter + rowDelimiter;
            
            for(int i = 0; i < longestElement(tableValue); i++){
                //emptyRow += " ";
                //emptyRow += "-";
                emptyRow += rowDelimiter;
            }
            //emptyRow += "  ";
            //emptyRow += "--";
            emptyRow += rowDelimiter + rowDelimiter;
            emptyRow += "|";
            return emptyRow;
        }

        static int maxIndexSpace(String[] tableValue){

            String maxIndex = Integer.toString(tableValue.length);
            String[] splitMaxIndex = maxIndex.split("");
            return splitMaxIndex.length;
        }

        static String endPieces(String[] tableValue){

            String endPiece = "__"; //add left index "roof"
            for(int i=0; i < maxIndexSpace(tableValue); i++){

                endPiece += "_"; //adds as many center "roofs" as the number has didgits
            }

            endPiece += "__"; //adds right index "roof"
            endPiece += "___"; //add space for mid vertical
            endPiece += "__"; //adds pace left of current index of tableValue
            
            for (int j = 0; j < longestElement(tableValue); j++) {
                endPiece += "_";  //adds as many center "roofs" as the value has didgits
            }
            
            endPiece += "__"; //adds pace right of current index of tableValue

            return endPiece;
        }

        static String filledRow(String[] tableValue, int arrayIndex){

            String filledRow = "|";
            filledRow += "  ";
            filledRow += arrayIndex;
            for(int i = 0; i < maxIndexSpace(tableValue)-getNumberDidgets(arrayIndex); i++){
                filledRow += " ";
            }
            filledRow += "  ";
            filledRow += "|";
            filledRow += "  ";
            
            filledRow += tableValue[arrayIndex];
            for(int i = 0; i < 2 + longestElement(tableValue)-tableValue[arrayIndex].length(); i++){
                filledRow += " ";
            }
            //filledRow += "  ";
            filledRow += "|";
            return filledRow;
        }
        
        static int getNumberDidgets(int number){
            
            return Integer.toString(number).length();
        }
    }
    
    
    
    

