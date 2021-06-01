package lab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final String BLACK = "черный";
    private static final String STRING_TO_FORMAT = "В продаже фены цвета: %s.";



    public static void main(String[] args) {
        HairDryer[] hairDryers = new HairDryer[]{
            new HairDryer("Rowenta GL4300", "Rowenta", "красный", 699, 3.7),
            new HairDryer("Vitek VT-2530", "Vitek", "синий", 880, 4.9),
            new HairDryer("Polaris PHD 2256TDi", "Polaris", "черный", 1200, 4.8),
            new HairDryer("Redmond RF-534", "Redmond", "серый", 1450, 5),
            new HairDryer("Rowenta CV3320", "Rowenta", "белый", 1799, 4.8),
            new HairDryer("Braun HD130", "Braun", "черный", 1699, 4.9),
            new HairDryer("Philips HP8233/00", "Philips", "фиолетовый", 2199, 4.7),
            new HairDryer("Redmond AC9096", "Redmond", "красный", 2999, 4.9),
            new HairDryer("Braun HD585", "Braun", "серебристый", 3599, 4.1),
            new HairDryer("Braun HD780 Solo", "Braun", "черный", 7999, 4.6)
        };

        //Сортировка фенов по наименованию
        List<HairDryer> hairDryersSortedByName = Arrays.stream(hairDryers)
                .sorted(Comparator.comparing(HairDryer::getName))
                .collect(Collectors.toList());
        System.out.println(hairDryersSortedByName);


        //Сортировка фенов по цене от дорогих к дешёвым
        List<HairDryer> hairDryersSortedByPrice = Arrays.stream(hairDryers)
            .sorted(Comparator.comparingInt(HairDryer::getPrice).reversed())
            .collect(Collectors.toList());

            System.out.println(hairDryersSortedByPrice);


        //Фен с наибольшей оценкой покупателей
        HairDryer hairDryerWithHighestRating = Arrays.stream(hairDryers)
            .max(Comparator.comparingDouble(HairDryer::getRating))
            .orElse(null);

        System.out.println(hairDryerWithHighestRating);



        //Создайте коллекцию фенов с ценой между 1 и 4 тыс. руб. (метод filter() в Stream API.)
        List<HairDryer> hairDryersFilteredByPriceRange = Arrays.stream(hairDryers)
            .filter(hairDryer -> hairDryer.getPrice() > 1000 && hairDryer.getPrice() < 4000)
            .collect(Collectors.toList());

        System.out.println(hairDryersFilteredByPriceRange);

        //5.	Рассчитайте среднюю оценку покупателей, используя числовой стрим ( метод map() в Stream API
        double averageRating = Arrays.stream(hairDryers)
            .mapToDouble(HairDryer::getRating)
            .average()
            .orElse(0);

        System.out.println(averageRating);

        //6.	Рассчитайте количество фенов не черного цвета (
        long numberOfNonBlackHairDryers = Arrays.stream(hairDryers)
            .filter(hairDryer -> hairDryer.getColour().equals(BLACK))
            .count();

        System.out.println(numberOfNonBlackHairDryers);

        //7.	Проверьте, у всех ли фенов оценка покупателей больше 4,5 (терминальный метод allMatch)
        boolean isRatingGoodEnough = Arrays.stream(hairDryers)
            .allMatch(hairDryer -> hairDryer.getRating() > 4.5);

        System.out.println(numberOfNonBlackHairDryers);

        //8.	Проверьте, есть ли фен стоимостью более 5 тыс. руб. (терминальный метод allMatch)
        boolean isExpensiveEnough = Arrays.stream(hairDryers)
            .anyMatch(hairDryer -> hairDryer.getPrice() > 5000);

        System.out.println(isExpensiveEnough);

        //9.	Найдите самый дешёвый фен, используя minBy() (порождение значения с помощью коллектора)
        HairDryer cheapestHairDryer = Arrays.stream(hairDryers)
            .collect(Collectors.minBy(Comparator.comparingInt(HairDryer::getPrice))).orElse(null);

        System.out.println(cheapestHairDryer);

        //10.	Разделите все фены на 2 коллекции: с оценкой больше 4,5 и меньше 4,5.
        Map<Boolean, List<HairDryer>> hairDryersPartitionedByRating = Arrays.stream(hairDryers)
            .collect(Collectors.partitioningBy(hairDryer -> hairDryer.getRating() > 4.5));
        List<HairDryer> hairDryersWithHighRating = hairDryersPartitionedByRating.get(true);
        List<HairDryer> hairDryersWithLowRating = hairDryersPartitionedByRating.get(false);

        System.out.println(hairDryersWithHighRating);
        System.out.println(hairDryersWithLowRating);

        //11.	Сгруппируйте все фены по маркам.
        Map<String, List<HairDryer>> hairDryersGroupedByBrand = Arrays.stream(hairDryers)
            .collect(Collectors.groupingBy(HairDryer::getBrand));

        System.out.println(hairDryersGroupedByBrand);

        //12.	Для каждой марки посчитайте количество фенов и среднюю цену.
        Map<String, CollectedInfo> collectedInfoByBrand = Arrays.stream(hairDryers)
            .collect(Collectors.groupingBy(HairDryer::getBrand, Collectors.collectingAndThen(Collectors.toList(), list ->
                new CollectedInfo(list.size(), list.stream()
                    .mapToInt(HairDryer::getPrice)
                    .average()
                    .orElse(0)))));
        System.out.println(collectedInfoByBrand);

        //13.	Сформируйте строку «В продаже фены …, …, …. цвета.»
        List<String> availableColours = Arrays.stream(hairDryers)
            .map(HairDryer::getColour)
            .distinct()
            .collect(Collectors.toList());
        System.out.println(availableColours);

        String formattedString = String.format(STRING_TO_FORMAT, String.join(", ", availableColours));
    }

    private static class CollectedInfo {

        private int quantity;
        private double averagePrice;

        public CollectedInfo(int quantity, double averagePrice) {
            this.quantity = quantity;
            this.averagePrice = averagePrice;
        }

        @Override
        public String toString() {
            return "CollectedInfo{" +
                "quantity=" + quantity +
                ", averagePrice=" + averagePrice +
                '}';
        }
    }
}
