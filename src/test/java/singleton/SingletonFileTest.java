package singleton;

import org.junit.Assert;
import org.junit.Test;

import static singleton.SingletonFile.*;


public class SingletonFileTest{
    @Test
    public void testSingletonInstance() {
        SingletonFile instance1 = getInstance();
        SingletonFile instance2 = getInstance();
        Assert.assertSame(instance1, instance2);
    }

}
