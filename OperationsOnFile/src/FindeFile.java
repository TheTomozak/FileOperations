import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindeFile {


    public void searchFileInVolume(String volume, String startWithString, String containsStringChain, String endsWithString) {


        String volumePath = volume + ":\\";
        File folder = new File(volumePath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            try {
                throw new BadVolumePathExceptions();

            } catch (BadVolumePathExceptions exc) {
                exc.printStackTrace();
                System.exit(1);
            }
        }


        for (File listOfFile : listOfFiles) {

            if (listOfFile.isDirectory() && !(listOfFile.getAbsolutePath().equals(volumePath + "\\System Volume Information"))) {

                try (Stream<Path> walk = Files.walk(Paths.get(listOfFile.getAbsolutePath()))) {

                    List<String> result = walk.filter(p -> p.getFileName().toString().startsWith(startWithString)).filter(g -> g.getFileName().toString().endsWith(endsWithString))
                            .filter(k -> k.getFileName().toString().contains(containsStringChain))
                            .map(Path::toString).collect(Collectors.toList());
                    result.forEach(System.out::println);

                } catch (IOException e) {

                }

            }

        }

    }


    public void searchFileInAllSystem(String startWithString, String containsStringChain, String endsWithString) {


        File[] paths = File.listRoots();
        for (File path : paths) {
            if (!path.getAbsolutePath().equals("C:\\")) {
                String volumePath = path.toString();
                File folder = new File(volumePath);

                File[] listOfFiles = folder.listFiles();

                if (listOfFiles == null) {
                    try {
                        throw new BadVolumePathExceptions();

                    } catch (BadVolumePathExceptions exc) {
                        exc.printStackTrace();
                        System.exit(1);
                    }
                }
                for (File listOfFile : listOfFiles) {

                    if (listOfFile.isDirectory() && !(listOfFile.getAbsolutePath().equals(volumePath + "System Volume Information"))) {

                        try (Stream<Path> walk = Files.walk(Paths.get(listOfFile.getAbsolutePath()))) {

                            List<String> result = walk.filter(p -> p.getFileName().toString().startsWith(startWithString)).filter(g -> g.getFileName().toString().endsWith(endsWithString))
                                    .filter(k -> k.getFileName().toString().contains(containsStringChain))
                                    .map(Path::toString).collect(Collectors.toList());
                            result.forEach(System.out::println);

                        } catch (IOException e) {

                        }

                    }

                }
            }

        }

    }


}
