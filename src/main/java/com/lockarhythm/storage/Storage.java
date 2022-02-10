package com.lockarhythm.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lockarhythm.tasks.Task;
import com.lockarhythm.tasks.TaskDeserializer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Storage handles all concerns relating to persisting tasks to disk.
 */
public class Storage<T> {
  private String filePath;

  private ArrayList<T> list;

  private Gson gson;

  /**
   * Storage constructor takes in filePath as the destination path to write bytes to.
   *
   * @param filePath a valid location path to persist the tasks to.
   */
  public Storage(String filePath) {
    this.filePath = filePath;

    gson =
        new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskDeserializer("_type"))
            .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
            .registerTypeAdapter(LocalDate.class, new GsonLocalDate())
            .setPrettyPrinting()
            .create();
  }

  /**
   * registerList is meant to allow top-level Application to pass a reference a the Task List so that Storage can save a reference.
   *
   * @param list an array list of tasks to be persisted.
   */
  public void registerList(ArrayList<T> list) {
    this.list = list;
  }

  /**
   * load reads the array list of tasks from disk. In order to handle persisting of abstract classes like Task with GSON, we need to pass its class in as parameter.
   *
   * @param type to pass the abstract type that is expected.
   * @return an array list of tasks from disk.
   */
  public ArrayList<T> load(Class<T> type) {
    String content;
    try {
      content = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);

      Type typeOfT = TypeToken.getParameterized(ArrayList.class, type).getType();
      ArrayList<T> deserialized =
          gson.fromJson(content, new TypeToken<ArrayList<Task>>() {}.getType());
      if (deserialized == null) {
        return new ArrayList<T>();
      }
      return deserialized;
    } catch (IOException e) {
      return new ArrayList<T>();
    }
  }

  /**
   * overwrite writes the current array list to disk.
   */
  public void overwrite() throws IOException {
    FileOutputStream fo = new FileOutputStream(filePath);
    String js = gson.toJson(list);
    fo.write(js.getBytes());
  }
}
