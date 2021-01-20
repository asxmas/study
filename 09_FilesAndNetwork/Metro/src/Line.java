public class Line implements Comparable<Line>{
    private String lineNumber;
    private String lineName;

    public Line(String lineNumber, String lineName){
        this.lineName = lineName;
        this.lineNumber = lineNumber;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getLineName() {
        return lineName;
    }

    @Override
    public int compareTo(Line line) {
        if(this.lineNumber.equals(line.lineNumber)) return 0;
        if(Integer.parseInt(this.lineNumber.replaceAll("[^0-9]", "")) >=  Integer.parseInt(line.lineNumber.replaceAll("[^0-9]", ""))) return 1;
        return -1;

    }
}
