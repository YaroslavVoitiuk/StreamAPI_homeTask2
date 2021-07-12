import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final int POPULATION = 150;
    private static final int AGE = 90;

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> secondNames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> people = new ArrayList<>();
        for (int i = 0; i < POPULATION; i++){
            people.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    secondNames.get(new Random().nextInt(secondNames.size())),
                    new Random().nextInt(AGE),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        printBorder();
        Long count = people.stream()
                .filter( person -> person.getAge() < 18)
                .count();
        System.out.println("В этом городе " + count + " несовершеннолетних людей");

        printBorder();
        System.out.println("Список призовников:");
        people.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27 && person.getSex() == Sex.MAN)
                .map(person -> person.getSecondName()).collect(Collectors.toList())
                .forEach(System.out::println);
        printBorder();

        System.out.println("Список потенциально работоспособных людей с высшим образованием: ");
        people.stream()
                .filter(person -> person.getEducation() == Education.HIGHER && person.getAge() > 18 && (person.getSex()
                        == Sex.MAN && person.getAge() < 65 || person.getSex() == Sex.WOMAN && person.getAge() < 60))
                .sorted(Comparator.comparing(person -> person.getSecondName()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        printBorder();

    }

    public static void printBorder(){
        System.out.println("---------------------------------");
    }
}