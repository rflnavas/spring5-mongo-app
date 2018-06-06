package guru.springframework.utils;

import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A small utility class for collections and streams to be utilized throughout this app.
 *
 * @author privado
 */
public class CollectionUtils {
	
	/**
	 * 
	 */
	private CollectionUtils() {
		
	}
	
	/**
	 * Provides a way to convert a stream from an Iterable
	 *
	 * @param <T> the generic type
	 * @param source the source
	 * @param parallel the parallel
	 * @return the stream
	 */
	public static <T> Stream<T> toStream(Iterable<T> source, boolean parallel){
		return StreamSupport.stream(source.spliterator(), parallel);
	}
	
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
}
