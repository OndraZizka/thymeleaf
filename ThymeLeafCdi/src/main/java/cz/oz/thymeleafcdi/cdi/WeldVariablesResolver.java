
package cz.oz.thymeleafcdi.cdi;

import cz.oz.thymeleafcdi.TestBean;
import javax.enterprise.inject.spi.BeanManager;

/**
 *  Resolves variables from Weld's beanmanager.
 *
 *  @author Ondrej Zizka
 */
public class WeldVariablesResolver implements IVariablesResolver<String, Object> {

    BeanManager bm;

    public WeldVariablesResolver( BeanManager bm ) {
        this.bm = bm;
    }

    public Object resolve( String key ) {
        //bm.getELResolver().
        return bm.getBeans( key ).iterator().next();
        // TODO
        //Class cls = Class.forName( key );
        //Object bean = weld.instance().select(cls).get();
    }

}
