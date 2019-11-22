import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main
{


    public static void main(String[] args) throws IOException {
        for (;;)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите путь папки для копирования");
            String input = scanner.nextLine();
            System.out.println("Введите путь папки куда копировать");
            String output = scanner.nextLine();
            try {
                File folder = new File(input);
                File copyFolder = new File(output);

                if (folder.exists() && copyFolder.exists())
                {
                    copyFolder.mkdir();
                    openFolder(folder, copyFolder);
                }
                else
                {
                    throw new FileNotFoundException("Неверно введен путь к файлу");
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }



    public static void openFolder(File folder, File copyFolder) throws IOException {
        File[] files = folder.listFiles();

        for(File file : files){
            if(file.isFile())
            {
                try {
                    Files.write(Paths.get(copyFolder.getPath() + "/" + file.getName()), Files.readAllLines(file.toPath()));
                }
                catch (Exception x)
                {
                    Files.write(Paths.get(copyFolder.getPath() + "/" + file.getName()), Files.readAllBytes(file.toPath()));
                }
            }
            else if (file.isDirectory())
            {
                File copy = new File(copyFolder.getPath() + "/" + file.getName());
                copy.mkdir();
                openFolder(file, copy);
            }
        }

    }

}
