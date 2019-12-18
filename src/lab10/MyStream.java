package lab10;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.*;

public class MyStream<T>{
	private LinkedList<T> list;
	
	static <T> MyStream<T> empty(){
		return new MyStream<T>();
	}	
	
	static <T> MyStream<T> of(T t){
		return new MyStream<T>(t);
	}
	
	@SuppressWarnings("unchecked")
	static <T> MyStream<T> of(T... values){
		return new MyStream<T>(values);
	}
	
	static <T> MyStream<T> stream(Collection<? extends T> c){
		return new MyStream<T>(c);
	}
	
	private MyStream(Collection<? extends T> c){
		list = new LinkedList<T>();	
		for(T element : c) {
			list.add(element);
		}
	}
	
	private MyStream(T t){
		list = new LinkedList<T>();
		list.add(t);
	}
	
	@SuppressWarnings("unchecked")
	private MyStream(T... values){
		list = new LinkedList<T>();
		for(T element : values) {
			list.add(element);
		}
	}
	
	private MyStream(){
		list = new LinkedList<T>();
	}
	
	MyStream<T> filter(Predicate<? super T> predicate){
		LinkedList<T> tmp = new LinkedList<T>(); 
		for(T element : list) {
			if(predicate.test(element)) {
				tmp.add(element);
			}
		}
		
 		return new MyStream<T>(tmp);
 	}
	
	<R> MyStream<R> map(Function<? super T, ? extends R> mapper){
		LinkedList<R> tmp = new LinkedList<R>();
		for(T element : list) {
			tmp.add(mapper.apply(element));
		}
		
 		return new MyStream<R>(tmp);
	}
	
	long count() {
		return list.size();
	}
	
	void forEach(Consumer<? super T> action) {
		for(T element : list) {
			action.accept(element);
		}
	}
	
	T reduce(T identity, BinaryOperator<T> accumulator) {
		T result = identity;
		for (T element : list) {
			result = accumulator.apply(result, element);
		}
		
		return result;
	}
	
	Optional<T> reduce(BinaryOperator<T> accumulator){
		 boolean isNotEmpty = false;
		 T result = null; 
		 for (T element : list) {
			 if (!isNotEmpty) {
				 isNotEmpty = true;
				 result = element;
				 } else {
					 result = accumulator.apply(result, element);
				 }
			 }
		 
		 return isNotEmpty ? Optional.of(result) : Optional.empty();
	}
	
	<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, 
			BinaryOperator<U> combiner) {
		U result = identity;
		
		for (T element : list) {
			result = accumulator.apply(result, element);
		}
		
		return result;
	}
	
	<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator,
            BiConsumer<R, R> combiner) {
		R result = supplier.get();
		
	    for (T element : list) {
	    	accumulator.accept(result, element);
	    }
	    
	    return result;
	}
	
	Optional<T> min(Comparator<? super T> comparator){
		if (list.size() > 0) {
			Collections.sort(list, comparator);
			return Optional.of(list.getFirst());
		} else {
			return Optional.empty();
		}
	}
	
	Optional<T> max(Comparator<? super T> comparator){
		if (list.size() > 0) {
			Collections.sort(list, comparator);
			return Optional.of(list.getLast());
		} else {
			return Optional.empty();
		}
	}
	
	MyStream<T> sorted(Comparator<? super T> comparator){
		Collections.sort(list, comparator);
		return new MyStream<T>(list);
	}
	
	MyStream<T> peek(Consumer<? super T> action){
		for(T element : list) {
			action.accept(element);
		}
		
		return new MyStream<T>(list);
	}
	
	MyStream<T> limit(long maxSize){
		LinkedList<T> tmp = new LinkedList<T>();	 
		for(T element : list) {
			if(maxSize > 0) {
				tmp.add(element);
			} else 
				break;
			maxSize--;
		}
		
 		return new MyStream<T>(tmp);
	}
	
	MyStream<T> skip(long n){
		LinkedList<T> tmp = new LinkedList<T>();	 
		for(T element : list) {
			if(n <= 0) {
				tmp.add(element);
			}
			n--;
		}
		
 		return new MyStream<T>(tmp);
	}
	
	Object[] toArray() {
		return list.toArray();
	}
}
