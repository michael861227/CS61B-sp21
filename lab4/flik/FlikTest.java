package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void isSameNumberTest(){
        Flik f = new Flik();
        assertTrue(f.isSameNumber(128, 128));
        assertTrue(f.isSameNumber(10, 128));
    }

    @Test
    public void SteveTest(){
        Flik f = new Flik();

        int i = 0;
        for (int j = 0; i < 500; ++i, ++j){
            assertTrue(Integer.toString(i) + " is not as same as " + Integer.toString(j)
            , f.isSameNumber(i, j));
        }
    }
}
