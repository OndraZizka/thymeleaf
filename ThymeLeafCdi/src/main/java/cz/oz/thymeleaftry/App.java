package cz.oz.thymeleaftry;

import java.io.PrintWriter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
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
        TemplateEngine tEng= new TemplateEngine();
        ClassLoaderTemplateResolver tResolver = new ClassLoaderTemplateResolver();
        // Load templates from this package.
        tResolver.setPrefix( App.class.getPackage().getName().replace(".", "/") + "/" );
        tResolver.setSuffix( ".html" );
        tEng.addTemplateResolver( tResolver );

        Context ctx = new Context();
        ctx.setVariable("header.text", "Hello World");

        tEng.process("Home", ctx, new PrintWriter( System.out ));
        System.out.flush();
        String foo = tEng.process("Home", ctx);

        System.out.println( foo );
    }
}
