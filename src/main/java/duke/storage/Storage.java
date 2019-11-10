package duke.storage;

import duke.exception.DukeException;
import duke.ingredient.Ingredient;
import duke.ingredient.IngredientsList;
import duke.list.GenericList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage used to load {@link Ingredient}s from a text {@link File} and store {@link Ingredient}s in it.
 */
public abstract class Storage<T> {

    protected String filePath;
    protected Path path;
    protected List<String> contentSoFar;
    protected GenericList<T> entries;

    /**
     * The constructor method for Storage.
     *
     * @param fp used to specify the location of the file in the hard disc.
     */
    public Storage(String fp) {
        filePath = fp;
        path = Paths.get(fp);
    }

    /**
     * Load tasks from file.
     *
     * @return an {@link ArrayList} of {@link Ingredient}s read from the text file indicated by the {@link Path}.
     */
    public GenericList<T> load() throws DukeException {
        try {
            //to get the data from the hard disk until now
            contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        } catch (IOException e) {
            try {
                File file = new File(filePath);
                file.createNewFile();
                contentSoFar = new ArrayList<>();
            } catch (IOException ex) {
                throw new DukeException("Could not create file in the specified directory.");
            }
        }
        entries = generate();
        return entries;
    }

    public GenericList<T> getEntries() { return entries; }

    /**
     * Part of the load method, taken out.
     * Generates tasks based on contentSoFar.
     */
    public abstract GenericList<T> generate() throws DukeException;

    /**
     * Returns the {@link Path} that holds the directory used for I/O.
     *
     * @return Path specifies the directory of the text {@link File} used for writing or reading
     */
    public Path getPath() {
        return path;
    }

    public String toString() {
        return filePath;
    }

    /**
     * Updates the content in the text file, by changing the specific {@link Ingredient} indicated by the taskNb.
     *
     * @param taskNb Positive integer indicating the number of the {@link Ingredient} in the {@link IngredientsList} to be updated
     * @throws DukeException if the taskNb is invalid or there was an I/O Exception
     */
    public void changeContent(int taskNb) throws DukeException {
        if (taskNb < 0) {
            throw new DukeException("The task number should be positive, task number entered was: " + taskNb);
        }
        try {
            contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            contentSoFar.set(taskNb, ((Printable) entries.getEntry(taskNb)).printInFile()); // changing the file content
            Files.write(path, contentSoFar, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error while updating the file");
        }
    }

    public void update() throws DukeException {
        try {
            contentSoFar = new ArrayList<String>(entries.size());
            for (int i = 0; i < entries.size(); i++)
                contentSoFar.add(i, ((Printable) entries.getEntry(i)).printInFile()); // changing the file content
            Files.write(path, contentSoFar, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error while updating the file");
        }
    }

    public void removeFromFile(int index) throws IOException {
        contentSoFar = new ArrayList<>(Files.readAllLines(getPath(), StandardCharsets.UTF_8));
        contentSoFar.remove(index); // changing the file content
        //System.out.println("removing index"+index);
        Files.write(getPath(), contentSoFar, StandardCharsets.UTF_8);
    }

    /**
     * Used to add a {@link Ingredient} by writing to {@link File}.
     *
     * @param task {@link Ingredient} to be written
     * @throws IOException whatever that is
     */
    public void addInFile(String task) throws IOException {
        contentSoFar = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        contentSoFar.add(task);
        Files.write(path, contentSoFar, StandardCharsets.UTF_8);
    }

    /**
     * Used to clear all info in the storage file.
     */
    public void clearInfoForFile() throws DukeException {
        File file = new File(filePath);
        try {
            if (!file.exists()) { file.createNewFile(); }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) { throw new DukeException("Error when clearing the list"); }
    }
}
