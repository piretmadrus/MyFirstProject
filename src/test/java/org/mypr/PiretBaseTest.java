package org.mypr;

import java.lang.reflect.Field;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author Yary Ribero (yary.ribero@ignite.ee)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
public abstract class PiretBaseTest {
  static{
    System.setProperty("spring.profiles.active","test");
  }

  public static Field getField(String name, Object instance) throws NoSuchFieldException {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("Parameter name must have a value");
    }

    if (instance == null) {
      throw new IllegalArgumentException("The object instance must be non null");
    }

    Field f = instance.getClass().getDeclaredField(name);
    f.setAccessible(true);
    return f;
  }

  public static <T> T getFieldValue(String name, Object instance) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("Parameter name must have a value");
    }

    if (instance == null) {
      throw new IllegalArgumentException("The object instance must be non null");
    }

    Field f = instance.getClass().getDeclaredField(name);
    f.setAccessible(true);
    return (T) f.get(instance);
  }

  public static <T> void setFieldValue(String name, Object instance, T value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("Parameter name must have a value");
    }

    if (instance == null) {
      throw new IllegalArgumentException("The object instance must be non null");
    }

    Field f = instance.getClass().getDeclaredField(name);
    f.setAccessible(true);
    f.set(instance, value);
  }
}