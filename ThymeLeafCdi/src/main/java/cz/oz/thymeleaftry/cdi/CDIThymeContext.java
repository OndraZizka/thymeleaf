
package cz.oz.thymeleaftry.cdi;

import java.util.Locale;
import javax.enterprise.inject.spi.BeanManager;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.VariablesMap;

/**
 *
 *  @author Ondrej Zizka
 */
public class CDIThymeContext implements IContext {
    
    private final LazyVariablesMap<String, Object> lazyMap;

    private Locale locale = null;


    public CDIThymeContext( BeanManager bm ) {
        WeldVariablesResolver res = new WeldVariablesResolver( bm );
        this.lazyMap = new LazyVariablesMap<String, Object>( res );
    }

    public VariablesMap<String, Object> getVariables() {
        return this.lazyMap;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale( Locale locale ) {
        this.locale = locale;
    }

    public void addContextExecutionInfo( String templateName ) {
        
    }

}// class
