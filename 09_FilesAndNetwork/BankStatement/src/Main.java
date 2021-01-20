import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private static String operationsFile = "data/movementList.csv";
    private static String dateFormat = "dd.MM.yyyy";
    private static String mccfile = "data/MCCcodes.csv";

    public static void main(String[] args) throws IOException {
        TreeMap<String, String> mccCodesTable = new TreeMap<>();
        List<String> mccCodesFile = Files.readAllLines(Paths.get(mccfile));
        for(String code : mccCodesFile) {
            String[] fragments = code.split(";");
            if (fragments.length != 3) {
                System.out.println("Wrong line: " + code);
                continue;
            }
            mccCodesTable.put(fragments[0], fragments[2] + " - " + fragments[1]);
        }

        ArrayList<Operation> operations = loadOperationsFromFile();
        System.out.println("Общий приход: " + operations.stream().mapToDouble(operation -> operation.getIncome()).sum());
        System.out.println("Общий расход: " + operations.stream().mapToDouble(operation -> operation.getExpense()).sum());

        Map<String, Double> operationsByType = operations.stream().collect(Collectors.groupingBy(Operation::getOperationType, Collectors.summingDouble(Operation::getExpense)));
        for(Map.Entry<String,Double> item : operationsByType.entrySet()){
            System.out.println(mccCodesTable.get(item.getKey()) + ": " + item.getValue() + " RUR");
        }
    }

    private static ArrayList<Operation> loadOperationsFromFile()
    {
        ArrayList<Operation> operations = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(operationsFile));
            for(int i=1; i < lines.size(); i++)
            {
                lines.set(i, removeQuotesCommasSpaces(lines.get(i)));

                String[] fragments = lines.get(i).split(",");
                if(fragments.length != 8) {
                    System.out.println("Wrong line: " + lines.get(i));
                    continue;
                }
                operations.add(new Operation(
                        fragments[0], fragments[1], fragments[2],
                        (new SimpleDateFormat(dateFormat)).parse(fragments[3]),
                        fragments[4], fragments[5],
                        Double.parseDouble(fragments[6]),Double.parseDouble(fragments[7])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return operations;
    }

    private static boolean isQuotes (String line){
        Pattern pattern = Pattern.compile("\"[0-9]+,[0-9]+\"");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

    private static String removeQuotesCommasSpaces (String line){
        String textWithoutQuotes = line.replaceAll("^.+?\"", "")
                .replaceAll("\"", "")
                .replaceAll(",", "\\.");
        return line.replaceAll("\".+\"",textWithoutQuotes);
    }
}
