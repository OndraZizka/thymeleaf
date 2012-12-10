
package cz.oz.thymeleafcdi.cdi;

/**
 *
 *  @author Ondrej Zizka
 */
public interface IVariablesResolver<K, V> {

    public V resolve(K key);

}
