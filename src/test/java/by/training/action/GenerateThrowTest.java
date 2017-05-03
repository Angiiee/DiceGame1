//package by.training.action;
//
//import org.junit.Test;
//
///**
// * Created by angelina on 16.04.2017.
// */
//public class GenerateThrowTest {
//
//    @Test
//    public void generateTest() throws Exception {
//        int k = 5;
//        Stream<Integer> str = GenerateThrow.generate(k).boxed();
//        Supplier <Stream<Integer>> streamSupplier = () -> str;
//        ArrayList<Integer> list;
//        IntStream stream = GenerateThrow.generate(5);
//        stream
//                .filter(o -> (o==2||o==5)).count()
//                .();
//        long count = streamSupplier.get().filter(o -> (o==2)||(o==5)).count();
//        List<Integer> list = streamSupplier.get().collect(Collectors.toList());
//        System.out.println(streamSupplier.get().forEach(););
//        streamSupplier.get().forEach(o -> System.out.print(o));

//        streamSupplier.get().forEach(o -> System.out.print(o));
//        System.out.println(streamSupplier.get().toArray());
//        Supplier<Stream<String>> streamSupplier =
//                () -> Stream.of("d2", "a2", "b1", "b3", "c")
//                        .filter(s -> s.startsWith("a"));
//
//        streamSupplier.get().anyMatch(s -> true);   // ok
//        streamSupplier.get().noneMatch(s -> true);  // ok
//    }
//}