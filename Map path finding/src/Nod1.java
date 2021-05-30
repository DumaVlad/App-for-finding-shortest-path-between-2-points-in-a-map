public class Nod1 {
    private final int coordx;
    private final int coordy;
    private final int id;

    Nod1() {
        coordx = 0;
        coordy = 0;
        id = -1;
    }

    Nod1(int x, int y, int auxId) {
        coordx = x;
        coordy = y;
        id = auxId;

    }

    int getCoordx() {
        return coordx;
    }

    int getCoordy() {
        return coordy;
    }

    int getId(){
        return  id;
    }
}
