public class Dado {
    public static int rolar(int lados){
        return (int)(Math.random() * lados) + 1;
    }
}
