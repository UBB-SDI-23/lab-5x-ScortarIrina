package info.scortar.irina.dogsdatabase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGen {

    private static final String COMMA = ",";
    private static final String DEFAULT_SEPARATOR = COMMA;
    private static final String DOUBLE_QUOTES = "\"";
    private static final String EMBEDDED_DOUBLE_QUOTES = "\"\"";
    private static final String NEW_LINE_UNIX = "\n";
    private static final String NEW_LINE_WINDOWS = "\r\n";

//    public static void main(String[] args) throws IOException {
//
//        String[] fNames = {"Alin", "Ioana", "Andrei", "Irina", "Paul", "Simona", "Alex", "Cristina", "Maria", "David",
//                "Marius", "Mircea", "Paula", "George", "Mihai", "Bogdan", "Adela", "Laura", "Sonia", "Sergiu"};
//        String[] lNames = {"Popescu", "Ionescu", "Georgescu", "Constantinescu", "Andreescu", "Stefanescu", "Petrescu",
//                "Dumitrescu", "Iliescu", "Marinescu"};
//        String[] genders = {"Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Female", "Male",
//                "Male", "Male", "Female", "Male", "Male", "Female", "Female", "Female", "Female", "Male"};
//        String[] jobs = {"Engineer", "Teacher", "Medic", "Psychologist", "Mechanic", "Gardener", "Singer", "Architect",
//                "Lawyer", "Carpenter", "Bartender", "Firefighter", "Electrician"};
//        String[] names = {"Patrocle", "Benji", "Nino", "Adolf", "Ivan", "Fifi", "Lulu", "Lola", "Lizzie", "Coco",
//                "Loki", "Max", "Rex", "Marissa", "Cosmo", "Bailey", "Luna", "Charlie", "Lady", "Coco"};
//        String[] breeds = {"Terrier", "Shih-Tzu", "Beagle", "Mix-breed", "Labrador", "Boxer", "Yorkshire", "Poodle",
//                "Chihuahua", "Bulldog", "Dachshund", "Rottweiler", "Samoyed", "Dalmatian"};
//        String[] specialties = {"Neurology", "Rheumatology", "Gynecology", "General", "Pharmacology", "Dentistry",
//                "Dermatology", "Emergency", "Oncology", "Cardiology", "Nutrition", "Ophthalmology", "Pathology"};
//        String[] cities = {"Cluj-Napoca", "London", "Belfast", "New-York", "Bucharest", "Paris", "Utrecht", "Bern"};
//        String[] countries = {"Romania", "USA", "France", "Ireland", "UK", "Netherlands", "Switzerland"};
//        String[] universities = {"University 1", "University 2", "University 3", "University 4", "University 5"};
//
//        DataGen writer = new DataGen();
//
//
//        // ~~~~~~~~~~~~~~~ GENERATE OWNERS ~~~~~~~~~~~~~~~
//
//        File f = new File("/tmp/owners.csv");
//
//        if (!f.exists()) {
//            f.createNewFile();
//        } else {
//            f.delete();
//            f.createNewFile();
//        }
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
//            // id, fName, lName, gender, job, age
//            for (int i = 0; i < 1000000; i++) {
//                int idx = getRandomNumberUsingNextInt(0, fNames.length);
//
//                String record = writer.convertToCsvFormat(new String[]{String.valueOf(i),
//                        fNames[idx] + "_" + i,
//                        lNames[getRandomNumberUsingNextInt(0, lNames.length)],
//                        genders[idx],
//                        jobs[getRandomNumberUsingNextInt(0, jobs.length)],
//                        String.valueOf(getRandomNumberUsingNextInt(18, 80))
//                });
//
//                bw.write(record);
//                bw.newLine();
//            }
//        }
//
//        // ~~~~~~~~~~~~~~~ GENERATE DOGS ~~~~~~~~~~~~~~~
//
//        f = new File("/tmp/dogs.csv");
//
//        if (!f.exists()) {
//            f.createNewFile();
//        } else {
//            f.delete();
//            f.createNewFile();
//        }
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
//            // id, name, breed, age, weight, owner_id
//            for (int i = 0; i < 1000000; i++) {
//                int idx = getRandomNumberUsingNextInt(0, fNames.length);
//
//                String record = writer.convertToCsvFormat(new String[]{String.valueOf(i),                                       // id
//                        names[getRandomNumberUsingNextInt(0, names.length)] + "_" + i,      // name
//                        breeds[getRandomNumberUsingNextInt(0, breeds.length)],              // breed
//                        String.valueOf(getRandomNumberUsingNextInt(1, 21)),                 // age
//                        String.valueOf(getRandomNumberUsingNextInt(1, 51)),                 // weight
//                        String.valueOf(getRandomNumberUsingNextInt(0, 1000000))             // owner_id
//                });
//
//                bw.write(record);
//                bw.newLine();
//            }
//        }
//
//
//        // ~~~~~~~~~~~~~~~ GENERATE VETS ~~~~~~~~~~~~~~~
//
//        f = new File("/tmp/vets.csv");
//
//        if (!f.exists()) {
//            f.createNewFile();
//        } else {
//            f.delete();
//            f.createNewFile();
//        }
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
//            // id, fName, lName, age, salary, specialty, years of experience
//            for (int i = 0; i < 1000000; i++) {
//                int idx = getRandomNumberUsingNextInt(0, fNames.length);
//
//                String record = writer.convertToCsvFormat(new String[]{String.valueOf(i),                                       // id
//                        fNames[getRandomNumberUsingNextInt(0, fNames.length)] + "_" + i,    // first name
//                        lNames[getRandomNumberUsingNextInt(0, lNames.length)],              // last name
//                        String.valueOf(getRandomNumberUsingNextInt(25, 80)),                // age
//                        String.valueOf(getRandomNumberUsingNextInt(1000, 10000)),           // salary
//                        specialties[getRandomNumberUsingNextInt(0, specialties.length)],    // specialty
//                        String.valueOf(getRandomNumberUsingNextInt(1, 50))                  // years of experience
//                });
//
//                bw.write(record);
//                bw.newLine();
//            }
//        }
//
//
//        // ~~~~~~~~~~~~~~~ GENERATE LICENSES ~~~~~~~~~~~~~~~
//
//        f = new File("/tmp/licenses.csv");
//
//        if (!f.exists()) {
//            f.createNewFile();
//        } else {
//            f.delete();
//            f.createNewFile();
//        }
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
//
//            ArrayList<Integer> vIds = new ArrayList<Integer>();
//            for (int i = 0; i < 1000000; i++) {
//                vIds.add(i);
//            }
//            Collections.shuffle(vIds);
//
//            // id, city, country, serial number, university, v_id
//            for (int i = 0; i < 1000000; i++) {
//                int idx = getRandomNumberUsingNextInt(0, fNames.length);
//
//                String record = writer.convertToCsvFormat(new String[]{String.valueOf(i),                                           // id
//                        cities[getRandomNumberUsingNextInt(0, cities.length)],                  // city
//                        countries[getRandomNumberUsingNextInt(0, countries.length)],            // country
//                        generateSerial(),                                                       // serial number
//                        universities[getRandomNumberUsingNextInt(0, universities.length)],      // university
//                        String.valueOf(vIds.get(i))                                             // vet_id
//                });
//
//                bw.write(record);
//                bw.newLine();
//            }
//        }
//
//
//        // ~~~~~~~~~~~~~~~ GENERATE APPOINTMENTS ~~~~~~~~~~~~~~~
//        f = new File("/tmp/appointments.csv");
//
//        if (!f.exists()) {
//            f.createNewFile();
//        } else {
//            f.delete();
//            f.createNewFile();
//        }
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
//
//            long aDay = TimeUnit.DAYS.toMillis(1);
//            long now = new Date().getTime();
//            Date twoYearsAgo = new Date(now - aDay * 365 * 10);
//            Date tenDaysFromNow = new Date(now + aDay * 10);
//
//            // id, date, price, dId, vId
//            for (int i = 0; i < 10000000; i++) {
//                int idx = getRandomNumberUsingNextInt(0, fNames.length);
//
//                String record = writer.convertToCsvFormat(new String[]{String.valueOf(i),       // id
//                        generateRandomDateBetween(twoYearsAgo, tenDaysFromNow).toString(),      // date
//                        String.valueOf(getRandomNumberUsingNextInt(100, 1000)),                 // price
//                        String.valueOf(getRandomNumberUsingNextInt(0, 1000000)),                // dog_id
//                        String.valueOf(getRandomNumberUsingNextInt(0, 1000000)),                // vet_id
//                        generateRandomDescription()
//                });
//
//                bw.write(record);
//                bw.newLine();
//            }
//        }
//    }

    public String convertToCsvFormat(final String[] line) {
        return convertToCsvFormat(line, DEFAULT_SEPARATOR);
    }

    public String convertToCsvFormat(final String[] line, final String separator) {
        return convertToCsvFormat(line, separator, false);
    }

    // if quote = true, all fields are enclosed in double quotes
    public String convertToCsvFormat(final String[] line, final String separator, final boolean quote) {
        return Stream.of(line)                              // convert String[] to stream
                .map(l -> formatCsvField(l, quote))         // format CSV field
                .collect(Collectors.joining(separator));    // join with a separator
    }

    // put your extra login here
    private String formatCsvField(final String field, final boolean quote) {
        String result = field;

        if (result.contains(COMMA) || result.contains(DOUBLE_QUOTES) || result.contains(NEW_LINE_UNIX) ||
                result.contains(NEW_LINE_WINDOWS)) {
            // if field contains double quotes, replace it with two double quotes \"\"
            result = result.replace(DOUBLE_QUOTES, EMBEDDED_DOUBLE_QUOTES);

            // must wrap by or enclosed with double quotes
            result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;
        } else {
            // should all fields enclosed in double quotes
            if (quote) {
                result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;
            }
        }

        return result;
    }

    // a standard FileWriter, CSV is a normal text file
    private void writeToCsvFile(List<String[]> list, File file) throws IOException {
        List<String> collect = list.stream()
                .map(this::convertToCsvFormat)
                .toList();

        // CSV is a normal text file, need a writer
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : collect) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static String generateSerial() {
        String result = new SecureRandom().ints(0, 36).
                mapToObj(i -> Integer.toString(i, 36))
                .map(String::toUpperCase)
                .distinct()
                .limit(16)
                .collect(Collectors.joining())
                .replaceAll("([A-Z0-9]{4})", "$1-")
                .substring(0, 19);

        return result;
    }

    public static Date generateRandomDateBetween(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    public static String generateRandomDescription() {
        String[] conjunction = {"and", "or", "but", "because", "to", "for", "from", "against"};
        String[] nouns = {"Jane", "Richard Nixon", "Miss America", "man", "woman", "fish", "elephant", "unicorn",
                "rabbit", "bread", "meat", "knee", "playground", "backpack", "paracetamol", "JLo", "Shakira"};
        String[] numerals = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        String[] adjectives = {"big", "tiny", "pretty", "bald", "red", "smart", "fat", "dull", "fancy", "gigantic",
                "nervous", "petty", "cruel"};
        String[] verbs = {"wants", "runs", "jumps", "talks", "sleeps", "loves", "hates", "sees", "knows", "looks for", "finds",
                "eats", "drinks", "cries", "swims"};
        String[] destinations = {"trees", "dogs", "apples", "mermaids", "sharks", "palm trees", "computers"};

        return "the" + adjectives[getRandomNumberUsingNextInt(0, adjectives.length)] +
                nouns[getRandomNumberUsingNextInt(0, nouns.length)] +
                verbs[getRandomNumberUsingNextInt(0, verbs.length)] +
                conjunction[getRandomNumberUsingNextInt(0, conjunction.length)] +
                numerals[getRandomNumberUsingNextInt(0, numerals.length)] +
                destinations[getRandomNumberUsingNextInt(0, destinations.length)];
    }
}
