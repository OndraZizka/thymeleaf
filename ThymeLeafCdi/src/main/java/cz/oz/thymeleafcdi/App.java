package cz.oz.thymeleafcdi;

import cz.oz.thymeleafcdi.cdi.CDIThymeContext;
import cz.oz.thymeleafcdi.cdi.WeldVariablesResolver;
import java.io.PrintWriter;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 *  ThymeLeaf Hello world.
 * 
 *  @author Ondrej Zizka.
 */
public class App 
{
    public static void main( String[] args )
    {
        // CDI (Weld)

        WeldContainer weld = new Weld().initialize();

        WeldVariablesResolver res = new WeldVariablesResolver( weld.getBeanManager() );
        TestBean bean = weld.instance().select(TestBean.class).get();
        weld.event().select( ContainerInitialized.class ).fire( new ContainerInitialized() );


        // ThymeLeaf

        TemplateEngine tEng= new TemplateEngine();
        ClassLoaderTemplateResolver tResolver = new ClassLoaderTemplateResolver();
        // Load templates from this package.
        tResolver.setPrefix( App.class.getPackage().getName().replace(".", "/") + "/" );
        tResolver.setSuffix( ".html" );
        tEng.addTemplateResolver( tResolver );

        Context ctx = new Context();
        ctx.setVariable("header.text", "Hello World");

        // Won't work - createEvalutionRoot() calls Map.putAll() calls Map.size().
        IContext ctx2 = new CDIThymeContext( weld.getBeanManager() );

        tEng.process("Home", ctx2, new PrintWriter( System.out ));
        System.out.flush();
        String foo = tEng.process("Home", ctx2);

        System.out.println( foo );
    }
}
