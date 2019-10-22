package signpost;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void fourByFourTestOne() {
        Puzzle p = new Puzzle(4,
                "1:Rt Dn DL    Dn\n" +
                "  DR DR DL    UL\n" +
                "  Up Rt DL    UL\n" +
                "  UR Up Rt 16:F");
        p.solve();
        assertEquals(p.getSolution(), "0,0 -> 0,1 -> 1,1 -> 2,2 -> 3,1 -> 2,1 -> 2,3 -> 1,2 -> " +
                "3,0 -> 0,3 -> 1,3 -> 0,2 -> 2,0 -> 1,0 -> 3,2 -> 3,3");
    }
    @Test
    public void fourByFourTestTwo() {
        Puzzle p = new Puzzle(4,
                "1:DR Dn DL    Dn\n" +
                "  DR Lt UR    Lt\n" +
                "  UR UR Dn    Lt\n" +
                "  Rt Lt UR 16:F");
        p.solve();
        assertEquals("0,0 -> 2,2 -> 3,2 -> 2,3 -> 2,0 -> 0,2 -> 1,1 -> 1,0 -> " +
                "2,1 -> 0,3 -> 1,3 -> 1,2 -> 0,1 -> 3,1 -> 3,0 -> 3,3", p.getSolution());
    }

    @Test
    public void threeByThreeTest() {
        Puzzle p = new Puzzle(3,
                "1:Rt DL   Dn\n" +
                "  Rt UR   DL\n" +
                "  Rt Lt 9:F");
        p.solve();
        assertEquals(p.getSolution(), "0,0 -> 0,1 -> 1,0 -> 1,1 -> 0,2 -> 1,2 -> 2,1 -> 2,0 -> 2,2");
    }
}
