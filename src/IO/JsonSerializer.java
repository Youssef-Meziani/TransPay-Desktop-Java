package IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.lang.reflect.Field;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonSerializer {

    // for json files
    public static <T> void serialize(ArrayList<T> list, String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            Gson gson = new Gson();
            gson.toJson(list, fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> ArrayList<T> deserialize(String filename, Class<T> type) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayList<T> list = null;
        try (FileReader fr = new FileReader(file)) {
            Gson gson = new Gson();
            list = gson.fromJson(fr, TypeToken.getParameterized(ArrayList.class, type).getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list == null) {
            return new ArrayList<T>();
        }
        return list;
    }

    // add objet
    public static <T> void add(T object, String filename){
        File file = new File("src/Data/" + filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (Exception e) {}
        }
        ArrayList<T> list = deserialize("src/Data/" + filename, (Class<T>) object.getClass());
        list.add(object);
        serialize(list, "src/Data/" + filename);
    }

    // modify object
    public static <T> void modify(T oldObject, T newObject, String filename) {
        ArrayList<T> list = deserialize("src/Data/" + filename, (Class<T>) oldObject.getClass());
        int index = list.indexOf(oldObject);
        if (index != -1) {
            list.set(index, newObject);
            serialize(list, "src/Data/" + filename);
        }
    }

    // remove objet
    public static <T> void remove(T object, String filename) {
        ArrayList<T> list = deserialize("src/Data/" + filename, (Class<T>) object.getClass());
        list.remove(object);
        serialize(list, "src/Data/" + filename);
    }

    public static <T> T getObject(ArrayList<T> objects, String attributeName, Object attributeValue) {
        for (T object : objects) {
            try {
                // Parcours la hiérarchie de classes pour trouver l'attribut
                Class<?> currentClass = object.getClass();
                while (currentClass != null) {
                    try {
                        Field field = currentClass.getDeclaredField(attributeName);
                        field.setAccessible(true);
                        Object value = field.get(object);

                        // Vérifie si la valeur de l'attribut correspond à la valeur de recherche
                        if (value.equals(attributeValue)) {
                            return object;
                        }
                    } catch (NoSuchFieldException e) {
                    }
                    // L'attribut n'a pas été trouvé dans cette classe, passe à la classe parente
                    currentClass = currentClass.getSuperclass();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}