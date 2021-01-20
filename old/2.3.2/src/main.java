public class main {
    public static void main(String[] args){

    int vasyaAge = 10;
    int mashaAge = 20;
    int sashaAge = 15;

    int min = vasyaAge;
    int middle = mashaAge;
    int max = sashaAge;
    int middle1 = middle;
    if(min > max) {
        min = max;
        max = vasyaAge;
    }
    if(middle < min) {
        middle = min;
        min = middle1;
    }
    if (middle > max) {
        middle = max;
        max = middle1;
    }
    System.out.println("max = " + max + " middle = " + middle + " min = " + min);
    }
}
