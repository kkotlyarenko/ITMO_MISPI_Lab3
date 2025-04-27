package org.kkotlyarenko.weblab3.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class PointBeanTest {
    private PointBean bean;
    private Method isWithinArea;

    @BeforeEach
    void setUp() throws Exception {
        bean = new PointBean();
        isWithinArea = PointBean.class.getDeclaredMethod("isWithinArea", double.class, double.class, double.class);
        isWithinArea.setAccessible(true);
    }

    @Test
    void testIsWithinArea_Q2() throws Exception {
        // Quadrant II: x<=0, y>=0
        assertTrue((Boolean) isWithinArea.invoke(bean, -1.0, 1.0, 2.0));
        assertFalse((Boolean) isWithinArea.invoke(bean, -3.0, 3.0, 2.0));
    }

    @Test
    void testIsWithinArea_Q4() throws Exception {
        // Quadrant IV: x>=0, y<=0, circle r^2/4
        double r = 4.0;
        assertTrue((Boolean) isWithinArea.invoke(bean, 1.0, -1.0, r));
        assertFalse((Boolean) isWithinArea.invoke(bean, 2.0, -1.0, r));
    }

    @Test
    void testIsWithinArea_Q3() throws Exception {
        // Quadrant III: x<=0, y<=0, triangle
        double r = 3.0;
        assertTrue((Boolean) isWithinArea.invoke(bean, -1.0, -1.0, r));
        assertFalse((Boolean) isWithinArea.invoke(bean, -4.0, -1.0, r));
    }

    @Test
    void testSetInvalidRadius() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> bean.setRadius(0));
        assertEquals("Radius should be more than 0", ex.getMessage());
    }
}
