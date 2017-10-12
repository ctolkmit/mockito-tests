package com.ennit.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;

public class MockAnnotationTest {

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    public static @interface TestAnnotation {
        // no arguments
    }

    @TestAnnotation
    public interface SomeInterface {
        // no methods
    }

    @TestAnnotation
    public static class SomeClass {
        // no members
    }

    public static class SomeSubClass extends SomeClass {
        // no members
    }

    @Test
    public void classMock() throws Exception {
        final SomeClass someClassMock = mock(SomeClass.class);
        final SomeClass someClassImpl = new SomeClass();
        assertEquals(someClassImpl.getClass().getAnnotation(TestAnnotation.class),
                someClassMock.getClass().getAnnotation(TestAnnotation.class));
    }

    @Test
    public void subClassMock() throws Exception {
        final SomeSubClass someClassMock = mock(SomeSubClass.class);
        final SomeSubClass someClassImpl = new SomeSubClass();
        assertEquals(someClassImpl.getClass().getAnnotation(TestAnnotation.class),
                someClassMock.getClass().getAnnotation(TestAnnotation.class));
    }

    @Test
    public void interfaceMock() throws Exception {
        final SomeInterface someInterfaceMock = mock(SomeInterface.class);
        final SomeInterface someInterfaceImpl = new SomeInterface() {
            // nothing to implement
        };
        assertEquals(someInterfaceImpl.getClass().getAnnotation(TestAnnotation.class),
                someInterfaceMock.getClass().getAnnotation(TestAnnotation.class));
    }


}
