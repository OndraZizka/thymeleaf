
package cz.oz.thymeleafcdi;

import javax.inject.Singleton;

/**
 *  CDI test bean.
 *  @author Ondrej Zizka
 */
@Singleton
public class TestBean {

    private String value = "Initial value.";

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

}
