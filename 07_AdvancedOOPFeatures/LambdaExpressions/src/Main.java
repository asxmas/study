import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) throws ParseException {
        ArrayList<Employee> staff = loadStaffFromFile();

//        Collections.sort(staff, ((o1, o2) -> {
//            o1.getSalary().compareTo(o2.getSalary());
//            if(o1.getSalary().equals(o2.getSalary())) {
//                return o1.getName().compareTo(o2.getName());
//            }
//            return o1.getSalary().compareTo(o2.getSalary());
//        }));
//        for(Employee employee : staff){
//            System.out.println(employee);
//        }
        ArrayList<Employee> staff2017 = new ArrayList<>();

        SimpleDateFormat smpldate = new SimpleDateFormat("dd.MM.yyyy");
        Date controlDate = smpldate.parse("01.01.2017");


        staff.stream().filter(e -> e.getWorkStart().after(controlDate))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}