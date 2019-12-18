package lab10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<User> list = new ArrayList<>();
		list.add(new User("John Smith", "(055)155-57-80", "johnSmith@gmail.com", 3));
		list.add(new User("Jane Doe", "(083)888-82-89", "unknown@ukr.net", 23));
		list.add(new User("Mary Sue", "(022)222-33-31", "ms@gmail.com", 12));
		list.add(new User("Harry Potter", "(032)324-24-21", "magician@mail.ru", 11));
		list.add(new User("Gary Oldman", "(047)782-82-89", "mrbad@gmail.com", 55));
		list.add(new User("Jane Ostin", "(022)020-33-31", "jo@ukr.net", 34));
		list.add(new User("Bran Stark", "(036)888-24-21", "raven.man@gmail.com", 8));
		
		list.stream().forEach(System.out::println);
		
		System.out.println("---------------");
		
		Stream<User> stream = list.stream();
		ArrayList<User> gmailLovers = stream
				.filter(x -> x.email.contains("@gmail.com"))
				.sorted((x, y) ->  x.name.compareTo(y.name))
				.peek(System.out::println)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		System.out.println("---------------");
		
		System.out.println("Amount of gmail lovers: " + gmailLovers.size());
		
		
		System.out.println("---------------");
		
		LinkedList<String> a = new LinkedList<String>();
		a.add("bbbb");
		a.add("aaaaa");
		a.add("cc");
		a.add("ddd");
		
		MyStream<String> test = MyStream.stream(a);
		test.filter(x -> x.length() > 2)
			.limit(2).map(x -> x + "11")
			.forEach(x -> System.out.println(x));
		
		System.out.println("---------------");
		
		MyStream<User> myStream = MyStream.stream(list);
		
		MyStream.stream(list).forEach(System.out::println);
		
		System.out.println("---------------");
		
		ArrayList<User> gmail = myStream
				.filter(x -> x.email.contains("@gmail.com"))
				.sorted((x, y) ->  x.name.compareTo(y.name))
				.peek(System.out::println)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		System.out.println("---------------");
		
		System.out.println("Amount of gmail lovers: " + gmail.size());
		
		
		System.out.println("---------------");
		
		MyStream.empty().forEach(System.out::println);
		
		System.out.println("---------------");
		
		Optional<String> str = MyStream.of("a", "b", "c", "d", "e")
									   .skip(2)
									   .reduce((x,y) -> x + y);
		if (str.isPresent()) 
			System.out.println(str.get());
	}

}

