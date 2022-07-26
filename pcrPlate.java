import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//input genotype in all caps followed by the start and end toe numbers 
//example:     DNMT3A 32 34 DNMT3A 43 47 FGD5 56 78
// currently works for HSF1, DNMT3A, FGD5, MX1CRE, NRAS, TGM2
// automatically adds two sets of controls if the samples won't fit on the 24 well row of the gel

class pcrPlate {

    public static void main(String[] args) {
        String[][] plate = createPlate();
        HashMap<String, String[]> result = createToeMap(args);
        String[][] last = updatePlate(plate, result);
        printPlate(last);
    }

    // takes the input and creates a hashmap of toe #'s for each genotype
    static HashMap<String, String[]> createToeMap(String[] args) {
        HashMap<String, String[]> map = new HashMap<>();
        for (int index = 0; index < args.length; index += 3) {
            String genotype = args[index];
            String[] toes = countNumbers(args[index + 1], args[index + 2]);
            if (map.containsKey(genotype)) {
                String[] combined = combineStringArray(map.get(genotype), toes);
                map.put(genotype, combined);
            } else {
                map.put(genotype, toes);
            }
        }
        return map;
    }

    // takes two numbers as strings and returns an string array of all those numbers
    static String[] countNumbers(String start, String end) {
        List<String> toeList = new ArrayList<String>();
        int endInt = Integer.parseInt(end);
        for (int startInt = Integer.parseInt(start); startInt < endInt + 1; startInt += 1) {
            toeList.add(Integer.toString(startInt));
        }
        String[] toeNumbers = toeList.toArray(new String[toeList.size()]);
        return toeNumbers;
    }

    // combines two string arrays
    static String[] combineStringArray(String[] one, String[] two) {
        String[] result = new String[one.length + two.length];
        int indexResult = 0;
        for (String s : one) {
            result[indexResult] = s;
            indexResult += 1;
        }
        for (String s : two) {
            result[indexResult] = s;
            indexResult += 1;
        }
        return result;
    }

    // creates and prints an empty pcr plate template
    static String[][] createPlate() {
        String[][] plate = new String[8][12];
        return plate;
    }

    // updates the values in the empty pcr plate with the hashmap of toes, adding
    // controls, returning a new line if it is a new genotype
    static String[][] updatePlate(String[][] plate, HashMap<String, String[]> map) {
        int row = 0;
        int column = 0;
        for (String genotype : map.keySet()) {
            String name = genotype.toString();
            String[] toes = map.get(genotype);
            String[] toesAndControls = addControls(name, toes);
            for (String sample : toesAndControls) {
                plate[row][column] = sample;
                column += 1;
                if (column == 12) {
                    row += 1;
                    column = 0;
                }
            }
            if (column != 0){
                row += 1;
                column = 0;
            }
        }
        return plate;
    }

    // updates the plate without making a new line for each genotype
    static String[][] updatePlate2(String[][] plate, HashMap<String, String[]> map) {
        int row = 0;
        int column = 0;
        for (String genotype : map.keySet()) {
            String name = genotype.toString();
            String[] toes = map.get(genotype);
            String[] toesAndControls = addControls(name, toes);
            for (String sample : toesAndControls) {
                plate[row][column] = sample;
                column += 1;
                if (column == 12) {
                    row += 1;
                    column = 0;
                }
            }
        }
        return plate;
    }

    // takes the genotype and the array of toes and returns a new array with toe
    // numbers and controls
    static String[] addControls(String genotype, String[] toes) {
        if (genotype.equals("HSF1") && toes.length <= 20) {
            String[] edited = new String[toes.length + 3];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = "H fl/fl";
            edited[toes.length + 1] = "H fl/+";
            edited[toes.length + 2] = "H20";
            return edited;
        } else if (genotype.equals("NRAS") && toes.length <= 20) {
            String[] edited = new String[toes.length + 3];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = "N fl/fl";
            edited[toes.length + 1] = "N fl/+";
            edited[toes.length + 2] = "H20";
            return edited;
        } else if (genotype.equals("TGM2") && toes.length <= 20) {
            String[] edited = new String[toes.length + 3];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = "T fl/fl";
            edited[toes.length + 1] = "T fl/+";
            edited[toes.length + 2] = "H20";
            return edited;
        } else if (genotype.equals("HSF1") && toes.length > 20) {
            String[] edited = new String[toes.length + 6];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = "H fl/fl";
            edited[toes.length + 1] = "H fl/fl";
            edited[toes.length + 2] = "H fl/+";
            edited[toes.length + 3] = "H fl/+";
            edited[toes.length + 4] = "H2O";
            edited[toes.length + 5] = "H2O";
            return edited;
        } else if (genotype.equals("NRAS") && toes.length > 20) {
            String[] edited = new String[toes.length + 6];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = "N fl/fl";
            edited[toes.length + 1] = "N fl/fl";
            edited[toes.length + 2] = "N fl/+";
            edited[toes.length + 3] = "N fl/+";
            edited[toes.length + 4] = "H2O";
            edited[toes.length + 5] = "H2O";
            return edited;
        } else if (genotype.equals("TGM2") && toes.length > 20) {
            String[] edited = new String[toes.length + 6];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = "T fl/fl";
            edited[toes.length + 1] = "T fl/fl";
            edited[toes.length + 2] = "T fl/+";
            edited[toes.length + 3] = "T fl/+";
            edited[toes.length + 4] = "H2O";
            edited[toes.length + 5] = "H2O";
            return edited;
        } else if (!genotype.equals("HSF1") && toes.length <= 21) {
            String[] edited = new String[toes.length + 2];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = genotype + "+";
            edited[toes.length + 1] = "H20";
            return edited;
        } else {
            String[] edited = new String[toes.length + 4];
            int index = 0;
            for (String toe : toes) {
                edited[index] = toe;
                index += 1;
            }
            edited[toes.length] = genotype + "+";
            edited[toes.length + 1] = genotype + "+";
            edited[toes.length + 2] = "H20";
            edited[toes.length + 3] = "H20";
            return edited;
        }
    }

    // prints the plate
    static void printPlate(String[][] plate) {
        String result = "\n";
        for (String[] row : plate) {
            for (int index = 0; index < row.length; index += 1) {

                result = result + "\t" + row[index];
            }
            result = result + "\n" + "\n";
        }
        System.out.println(result);
    }

}
