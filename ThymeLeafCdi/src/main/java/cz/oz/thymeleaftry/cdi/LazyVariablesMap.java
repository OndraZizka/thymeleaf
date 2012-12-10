
package cz.oz.thymeleaftry.cdi;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.thymeleaf.context.VariablesMap;

/**
 *  Lazy lookup map which gets the value when get(K key) is called.
 *  This is an ugly hack until ThymeLeaf supports lazy lookup.
 * 
 *  @see  https://github.com/thymeleaf/thymeleaf/issues/101
 *  @author Ondrej Zizka
 */
public class LazyVariablesMap<K,V> extends VariablesMap<K, V> {

    IVariablesResolver<K, V> resolver;

    public LazyVariablesMap( IVariablesResolver<K, V> resolver ) {
        this.resolver = resolver;
    }


    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V get( Object key ) {
        try {
            return this.resolver.resolve( (K)key );
        } catch (ClassCastException ex ){
            throw ex;
        }
    }

    @Override
    public boolean containsKey( Object key ) {
        return get( key ) != null;
    }

    @Override public V put( K key, V value ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll( Map<? extends K, ? extends V> m ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove( Object key ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue( Object value ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals( Object o ) {
        if( o == this ) return true;
        if ( ! (o instanceof LazyVariablesMap) )  return false;
        LazyVariablesMap o2 = (LazyVariablesMap) o;
        return this.resolver.equals( o2.resolver );
    }

    @Override
    public int hashCode() {
        return this.resolver.hashCode();
    }

}// class
